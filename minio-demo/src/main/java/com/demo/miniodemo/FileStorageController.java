package com.demo.miniodemo;

import com.demo.miniodemo.dto.BucketInfo;
import com.demo.miniodemo.dto.ObjectInfo;
import io.minio.errors.MinioException;
import io.minio.messages.Bucket;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.simpleframework.xml.Path;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/files")
public class FileStorageController {
    private final FileStorageService fileStorageService;


    @ExceptionHandler(MinioException.class)
    public ResponseEntity<String> handleMinioException(MinioException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

    @GetMapping("/buckets")
    public ResponseEntity<List<BucketInfo>> getAllBuckets() throws MinioException {
        return ResponseEntity.ok(fileStorageService.getAllBuckets());
    }

    @PostMapping("/buckets/{bucketName}")
    public ResponseEntity<String> createBucket(@PathVariable String bucketName) throws MinioException {
        return ResponseEntity.ok(fileStorageService.createBucket(bucketName));
    }

    @DeleteMapping("/buckets/{bucketName}")
    public ResponseEntity<String> deleteBucket(@PathVariable String bucketName) throws MinioException {
        return ResponseEntity.ok(fileStorageService.deleteBucket(bucketName));
    }

    @PostMapping("/buckets/{bucketName}/create-folder/{folderName}")
    public ResponseEntity<String> createDirectory(@PathVariable String bucketName, @PathVariable String folderName) throws MinioException {
        return ResponseEntity.ok(fileStorageService.createDirectory(bucketName, folderName));
    }

    @PostMapping("/{bucketName}/upload")
    public ResponseEntity<String> uploadFile(@PathVariable String bucketName, @RequestBody MultipartFile file) throws MinioException {
        String message = fileStorageService.uploadFile(bucketName, file);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/{bucketName}/upload-multiple")
    public ResponseEntity<String> uploadMultipleFiles(@PathVariable String bucketName, @RequestBody MultipartFile[] files) throws MinioException {
        return ResponseEntity.ok(fileStorageService.uploadMultipleFiles(bucketName, files));
    }

    @GetMapping("/{bucketName}/search")
    public ResponseEntity<List<String>> searchFiles(
            @PathVariable String bucketName,
            @RequestParam(name = "filename", required = false) String filename,
            @RequestParam(name = "filetype", required = false) String filetype
    ) throws MinioException {
        return ResponseEntity.ok(fileStorageService.searchFiles(bucketName, filename, filetype));
    }

//    @GetMapping("/{bucketName}/{fileName}/download")
//    public void downloadFile(@PathVariable String bucketName, @PathVariable String fileName, HttpServletResponse response) throws MinioException {
//        fileStorageService.downloadFile(bucketName, fileName, response);
//    }

//    @GetMapping("/{bucketName}/{fileName}")
//    public ResponseEntity<InputStream> getFileInfo(@PathVariable String bucketName, @PathVariable String fileName) throws MinioException {
//        return ResponseEntity.ok(fileStorageService.getFileInfo(bucketName, fileName));
//    }

    @GetMapping("/{bucketName}/{fileName}/url")
    public ResponseEntity<String> getFileURL(@PathVariable String bucketName, @PathVariable String fileName) throws MinioException {
        return ResponseEntity.ok(fileStorageService.getFileURL(bucketName, fileName));
    }

    @DeleteMapping("/{bucketName}/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String bucketName, @PathVariable String fileName) throws MinioException {
        return ResponseEntity.ok(fileStorageService.deleteFile(bucketName, fileName));
    }

    @PostMapping("/{bucketName}/{fileName}/restore")
    public ResponseEntity<String> restoreFile(@PathVariable String bucketName, @PathVariable String fileName) throws MinioException {
        return ResponseEntity.ok(fileStorageService.restoreFile(bucketName, fileName));
    }

}
