package com.memoir.global.storage;
import io.awspring.cloud.s3.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {
  @Value("${spring.cloud.aws.s3.bucket}")
  private String bucketName;

  private final S3Client s3Client;
  private final S3Properties s3Properties;

  public String uploadFile(MultipartFile image) throws IOException {
    if(image.isEmpty()) {
      throw new BadRequestException("Fuck!! Give me File beach");
    }

    String fileName = createFileName(image.getOriginalFilename());

    try {
      PutObjectRequest putObjectRequest = PutObjectRequest.builder()
              .bucket(bucketName)
              .contentType(image.getContentType())
              .contentLength(image.getSize())
              .key(fileName)
              .build();
      RequestBody requestBody = RequestBody.fromBytes(image.getBytes());
      s3Client.putObject(putObjectRequest, requestBody);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    GetUrlRequest getUrlRequest = GetUrlRequest.builder()
            .bucket(bucketName)
            .key(fileName)
            .build();

    return s3Client.utilities().getUrl(getUrlRequest).toString();
  }

  private String createFileName(String fileName){
    return UUID.randomUUID().toString().concat(getFileExtension(fileName));
  }

  private String getFileExtension(String fileName){
    try{
      return fileName.substring(fileName.lastIndexOf("."));
    } catch (StringIndexOutOfBoundsException e){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일" + fileName + ") 입니다.");
    }
  }
}
