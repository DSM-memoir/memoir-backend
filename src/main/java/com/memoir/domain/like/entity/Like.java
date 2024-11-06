package com.memoir.domain.like.entity;

import com.memoir.domain.memoir.entity.Memoir;
import com.memoir.domain.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "like_table")
@Getter
@Builder
@IdClass(LikeId.class)
@AllArgsConstructor
@NoArgsConstructor
public class Like {

    @Id
    @ManyToOne(optional = false, targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Id
    @ManyToOne(optional = false, targetEntity = Memoir.class)
    @JoinColumn(name = "memoir_id", referencedColumnName = "id")
    private Memoir memoir;
}

