package com.demo.miniodemo;

import com.demo.miniodemo.dto.BucketInfo;
import com.demo.miniodemo.dto.ObjectInfo;
import io.minio.errors.MinioException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;


public interface FileStorageService {

    List<BucketInfo> getAllBuckets() throws MinioException;

    String uploadFile(String bucketName, MultipartFile file) throws MinioException;

    String uploadMultipleFiles(String bucketName, MultipartFile[] files) throws MinioException;

    List<String> searchFiles(String bucketName, String filename, String fileType) throws MinioException;

    void downloadFile(String bucketName, String fileName, HttpServletResponse response) throws MinioException;

    InputStream getFileInfo(String bucketName, String fileName) throws MinioException;

    String deleteFile(String bucketName, String fileName) throws MinioException;

    String deleteBucket(String bucketName) throws MinioException;

    String createDirectory(String bucketName, String folderName) throws MinioException;

    String restoreFile(String bucketName, String fileName) throws MinioException;

    String getFileURL(String bucketName, String fileName) throws MinioException;

    String createBucket(String bucketName) throws MinioException;
}