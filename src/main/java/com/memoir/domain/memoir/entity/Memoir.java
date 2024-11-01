package com.memoir.domain.memoir.entity;

import com.memoir.domain.user.entity.User;
import jakarta.persistence.*;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Memoir {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(unique = true, nullable = false, columnDefinition = "BINARY(16)")
  private UUID id;

  @Column(nullable = false)
  private String title;

  @ManyToOne(cascade = CascadeType.ALL, optional = false, targetEntity = User.class)
  @JoinColumn(name = "author", referencedColumnName = "id")
  private String author;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private String feels;

  @Column(nullable = false)
  private String imageUrl;
}