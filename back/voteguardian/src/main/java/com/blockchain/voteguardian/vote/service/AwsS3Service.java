package com.blockchain.voteguardian.vote.service;

import org.springframework.web.multipart.MultipartFile;

public interface AwsS3Service {
    String uploadFile(MultipartFile multipartFile);
    void deleteFile(String fileName);
    String createFileName(String fileName);
    String getFileExtension(String fileName);
}
