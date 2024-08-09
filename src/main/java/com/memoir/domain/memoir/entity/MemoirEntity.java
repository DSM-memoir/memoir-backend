package com.memoir.domain.memoir.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
public class MemoirEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "image")
  private String imagePath;

  @Column(name = "contents")
  private String contents;

  @Column(name = "feeling")
  private String feeling;

  @JsonFormat(pattern = "yyyy-mm-dd")
  @CreationTimestamp
  private LocalDateTime createAt;

  protected MemoirEntity() {
  }

  @Builder
  public MemoirEntity(String title, String imagePath, String contents, String feeling) {
    this.title = title;
    this.imagePath = imagePath;
    this.contents = contents;
    this.feeling = feeling;
  }
}
