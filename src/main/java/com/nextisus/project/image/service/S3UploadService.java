package com.nextisus.project.image.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3UploadService {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // MultipartFile을 S3에 업로드하고, URL을 반환하는 메소드
    public String upload(MultipartFile multipartFile, String dirName) throws IOException {
        File convFile = new File(multipartFile.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(multipartFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("오류: MultipartFile -> File 변환 실패", e);
        }
        String fileName =  dirName + "/" + convFile.getName() + UUID.randomUUID();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(convFile.length());
        try {
            amazonS3.putObject(bucket, fileName, convFile);
        } catch (Exception e) {
            log.error("파일을 S3에 업로드하는 중 오류 발생: {}", e.getMessage());
            throw new RuntimeException("파일 업로드 실패", e);
        }

        String url = amazonS3.getUrl(bucket, fileName).toString();
        //업로드 후 임시 파일 삭제
        convFile.delete();
        return url;
    }

    // S3에서 파일을 다운로드하고 InputStream 으로 반환하는 메소드
    public InputStream getFileAsInputStream(String fileUrl) {
        String bucketName = extractBucketName(fileUrl);
        String key = extractKey(fileUrl);

        S3Object s3object = amazonS3.getObject(bucketName, key);
        return s3object.getObjectContent();
    }

    // 파일 URL 에서 버킷 이름을 추출하는 헬퍼 메소드
    private String extractBucketName(String fileUrl) {
        String[] parts = fileUrl.split("/");
        return parts[2].split("\\.")[0];
    }

    // 파일 URL 에서 키를 추출하는 헬퍼 메소드
    private String extractKey(String fileUrl) {
        String[] parts = fileUrl.split("/", 4);
        return parts[3];
    }
}
