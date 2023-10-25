package com.demo.miniodemo;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class MinioDemoApplication {

	@Value("${minio.endpoint}")
	private String minioEndpoint;

	@Value("${minio.accessKey}")
	private String minioAccessKey;

	@Value("${minio.secretKey}")
	private String minioSecretKey;

	public static void main(String[] args) {
		SpringApplication.run(MinioDemoApplication.class, args);
	}

	@Bean
	public MinioClient minioClient() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
		MinioClient minioClient = MinioClient.builder()
				.endpoint(minioEndpoint)
				.credentials(minioAccessKey, minioSecretKey)
				.build();

		// Create bucket 'test' if it doesn't exist
		String bucketName = "test";
		boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
		if (!found) {
			minioClient.makeBucket(
					MakeBucketArgs.builder()
							.bucket(bucketName)
							.build()
			);
		} else {
			System.out.println("Bucket " + bucketName + " already exists.");
		}

		return minioClient;
	}

}
