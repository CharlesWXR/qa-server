package edu.njnu.qaserver.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface OssService {
    String upload(String url);
    String upload(MultipartFile file);
    boolean needUpload(String imageUrl);
}