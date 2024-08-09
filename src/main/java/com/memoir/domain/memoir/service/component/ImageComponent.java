package com.memoir.domain.memoir.service.component;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class ImageComponent {
  public String storeFile(MultipartFile multipartFile) throws IOException {
    if (multipartFile.isEmpty()) {
      return null;
    }

    // 오리지널 파일 이름 알아내서
    String originalFilename = multipartFile.getOriginalFilename();

    // 고유 값으로 파일 이름 생성하고
    String storeFilename = createStoreFilename(originalFilename);

    // 이걸 multipartFile 에서 File 형식으로 transfer
    multipartFile.transferTo(new File(createPath(storeFilename)));

    // 그걸 가지고 Entity 생성
    return storeFilename;
  }

  public String createPath(String storeFilename) {
    // local에 저장할 file 위치 + 이미지 타입 + stroeFilename
    return "/Users/ljyo2o9/Documents/WebProgram_Project/webPro/web_pro/public/server_image/" + storeFilename;
  }

  private String createStoreFilename(String originalFilename) {
    String uuid = UUID.randomUUID().toString(); // 고유값 만들기
    String ext = extractExt(originalFilename); // originalFilename에 확장자를 제외한 이름 추출

    return uuid + ext;
  }

  // 확장자 알아내서 그 전까지 파일 이름 알아내기
  private String extractExt(String originalFilename) {
    int idx = originalFilename.lastIndexOf(".");
    return originalFilename.substring(idx);
  }
}
