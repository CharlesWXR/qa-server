package edu.njnu.qaserver.utils;

import io.minio.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Slf4j
@Component
public class MinIOUtil {
	@Autowired
	private MinioClient minioClient;

	@Value("${minio.bucket}")
	private String bucketName;

	public String uploadFile(InputStream is, String filename, String contentType) {
		try {
			boolean exist = minioClient.bucketExists(
					BucketExistsArgs.builder()
							.bucket(bucketName)
							.build());
			if (!exist) {
				minioClient.makeBucket(MakeBucketArgs
						.builder()
						.bucket(bucketName)
						.build());
			}
			minioClient.putObject(PutObjectArgs.builder()
					.bucket(bucketName)
					.object(filename)
					.stream(is, is.available(), -1)
					.contentType(contentType)
					.build());
			is.close();
			log.info("MinIO: {} in {} upload success", filename, bucketName);
			return getFileUrl(filename);
		} catch (Exception e) {
			log.error("MinIO: {} in {} upload fail: {}", filename, bucketName, e.getMessage());
			return null;
		}
	}

	public InputStream downloadFile(String filename) {
		try {
			InputStream is = minioClient.getObject(
					GetObjectArgs.builder()
							.bucket(bucketName)
							.object(filename)
							.build());
			return is;
		} catch (Exception e) {
			log.error("MinIO: {} in {} downloaded fail: {}", filename, bucketName, e.getMessage());
			return null;
		}
	}

	public String getFileUrl(String filename) {
		if (filename == null)
			return null;
		try {
			return minioClient.getPresignedObjectUrl(
					GetPresignedObjectUrlArgs.builder()
							.method(Method.GET)
							.bucket(bucketName)
							.object(filename)
							.build());
		} catch (Exception e) {
			log.error("MinIO: {} in {} generate url fail: {}", filename, bucketName, e.getMessage());
			return null;
		}
	}
}
