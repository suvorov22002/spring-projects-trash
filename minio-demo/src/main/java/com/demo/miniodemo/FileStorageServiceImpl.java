package com.demo.miniodemo;

import com.demo.miniodemo.dto.BucketInfo;
import com.demo.miniodemo.dto.ObjectInfo;
import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.http.Method;
import io.minio.messages.Item;
import io.minio.messages.RestoreRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.StreamSupport;


@RequiredArgsConstructor
@Service
public class FileStorageServiceImpl implements FileStorageService {
    final private MinioClient minioClient;


    @Override
    public List<BucketInfo> getAllBuckets() throws MinioException {
        try {
            return minioClient.listBuckets().stream()
                    .map(bucket -> new BucketInfo(bucket.name(), bucket.creationDate()))
                    .toList();
        } catch (Exception e) {
            throw new MinioException(e.getMessage());
        }
    }

    @Override
    public String deleteBucket(String bucketName) throws MinioException {
        try {
            minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
            return "Bucket deleted successfully.";
        } catch (Exception e) {
            throw new MinioException(e.getMessage());
        }
    }

    @Override
    public String createDirectory(String bucketName, String folderName) throws MinioException {
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(folderName + "/")
                            .stream(new ByteArrayInputStream(new byte[] {}), 0, -1)
                            .build()
            );
            return "Directory created successfully";
        } catch (Exception e) {
            throw new MinioException(e.getMessage());
        }
    }

    @Override
    public String restoreFile(String bucketName, String fileName) throws MinioException {
        try {
            minioClient.restoreObject(
                    RestoreObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .request(new RestoreRequest(null, null, null, null, null, null))
                            .build()
            );
            return "File restored successfully";
        } catch (Exception e) {
            throw new MinioException(e.getMessage());
        }
    }

    @Override
    public String getFileURL(String bucketName, String fileName) throws MinioException {
        try {
            Map<String, String> reqParams = new HashMap<>();
            reqParams.put("response-content-type", "application/json"); // optional
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucketName)
                            .object(fileName)
                            .expiry(2, TimeUnit.HOURS)
                            .extraQueryParams(reqParams)
                            .build()
            );
        } catch (Exception e) {
            throw new MinioException(e.getMessage());
        }
    }

    @Override
    public String createBucket(String bucketName) throws MinioException {
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                return "Bucket " + bucketName + " created successfully.";
            }

            return "Bucket " + bucketName + " already exists.";
        } catch (Exception e) {
            throw new MinioException(e.getMessage());
        }
    }

    @Override
    public String uploadFile(String bucketName, MultipartFile file) throws MinioException {
        try {
            // Normalize file name
//            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(file.getOriginalFilename())
                            .stream(file.getInputStream(), -1, 10485760)
                            .contentType(file.getContentType())
                            .build()
            );

            return "File uploaded successfully.";
        } catch (Exception e) {
            throw new MinioException(e.getMessage());
        }
    }

    @Override
    public String uploadMultipleFiles(String bucketName, MultipartFile[] files) throws MinioException {
        try {
            List<SnowballObject> objects = new ArrayList<>();
            for (MultipartFile file: files) {
                objects.add(new SnowballObject(file.getName(), file.getInputStream(), 0l, null));
            }

            minioClient.uploadSnowballObjects(
                    UploadSnowballObjectsArgs.builder()
                            .bucket(bucketName)
                            .objects(objects)
                            .build()
            );
            return "Files uploaded successfully.";
        } catch (Exception e) {
            throw new MinioException(e.getMessage());
        }
    }

    @Override
    public List<String> searchFiles(String bucketName, String filename, String fileType) throws MinioException {
        try {
            List<String> fileNames = new ArrayList<>();

            // Check if the bucket exists
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!bucketExists) {
                throw new MinioException("Bucket does not exist: " + bucketName);
            }

            // Construct the prefix for the filename search
            String prefix = filename != null ? filename : "";

            // Construct the suffix for the file type search
            String suffix = fileType != null ? "." + fileType.toLowerCase() : "";

            // List objects with prefix and suffix filters
            Iterable<Result<Item>> results = minioClient.listObjects(
                    ListObjectsArgs.builder()
                            .bucket(bucketName)
                            .prefix(prefix)
                            .recursive(true)
                            .build()
            );
            for (Result<Item> result : results) {
                String objectName = result.get().objectName();
                if (objectName.toLowerCase().endsWith(suffix)) {
                    fileNames.add(objectName);
                }
            }

            return fileNames;
        } catch (Exception e) {
            throw new MinioException(e.getMessage());
        }
    }

    @Override
    public void downloadFile(String bucketName, String fileName, HttpServletResponse response) throws MinioException {
        try {
            minioClient.downloadObject(
                    DownloadObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .filename("output-name")
                            .build()
            );
        } catch (Exception e) {
            throw new MinioException(e.getMessage());
        }
    }

    @Override
    public InputStream getFileInfo(String bucketName, String fileName) throws MinioException {
        try {
            InputStream stream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .build()
            );
            return stream;
        } catch (Exception e) {
            throw new MinioException(e.getMessage());
        }
    }

    @Override
    public String deleteFile(String bucketName, String fileName) throws MinioException {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .build()
            );
            return "File deleted successfully.";
        } catch (Exception e) {
            throw new MinioException(e.getMessage());
        }
    }

}

