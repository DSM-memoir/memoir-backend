package com.memoir.domain.memoir.entity;

import jakarta.persistence.*;

import java.util.UUID;
import lombok.Getter;

@Entity
@Getter
public class Memoir {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(unique = true, nullable = false, columnDefinition = "BINARY(16)")
  private UUID id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String author;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private String feels;

  @Column(nullable = false)
  private String imageUrl;
}