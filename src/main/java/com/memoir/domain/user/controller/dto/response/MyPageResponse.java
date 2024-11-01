package com.memoir.domain.user.controller.dto.response;

import com.memoir.domain.memoir.entity.Memoir;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class MyPageResponse {
    private String nickname;
    private List<MemoirResponse> memoires;

    public static MyPageResponse of(String nickname, List<Memoir> memoires) {
        return MyPageResponse.builder()
                .nickname(nickname)
                .memoires(
                        memoires.stream().map(memoir ->
                                MemoirResponse.builder()
                                        .id(memoir.getId())
                                        .title(memoir.getTitle())
                                        .author(memoir.getAuthor().getNickname())
                                        .content(memoir.getContent())
                                        .postDate(memoir.getPostDate())
                                        .imageUrl(memoir.getImageUrl())
                                        .build()
                        ).toList()
                )
                .build();
    }
}

@Builder
@Getter @Setter
class MemoirResponse {
    private UUID id;
    private String title;
    private String author;
    private String content;
    private LocalDate postDate;
    private String imageUrl;
}