package edu.njnu.qaserver.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinIOClientConfig {
	@Value("${minio.access-key}")
	private String accessKey;

	@Value("${minio.secret-key}")
	private String secretKey;

	@Value("${minio.server}")
	private String endPoint;

	@Bean
	public MinioClient minioClient() {
		return MinioClient.builder()
				.endpoint(endPoint)
				.credentials(accessKey, secretKey)
				.build();
	}
}
