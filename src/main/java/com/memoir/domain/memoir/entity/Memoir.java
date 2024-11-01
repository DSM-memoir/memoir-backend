package com.memoir.domain.memoir.entity;

import com.memoir.domain.user.entity.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

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

  @ManyToOne(optional = false, targetEntity = User.class)
  @JoinColumn(name = "author", referencedColumnName = "id")
  private User author;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private String feels;

  @CreationTimestamp
  @Column(nullable = false)
  private LocalDate postDate;

  @Column(nullable = false)
  private String imageUrl;

  @Column(nullable = false)
  private Boolean published;

  @Column(nullable = false)
  private Integer likes;
}