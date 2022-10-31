package com.example.stackoverflow.entity;

import com.example.stackoverflow.dto.VoteType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class QuestionVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @CreatedDate
    private Instant createdDate;

    @CreatedBy
    private String createdBy;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean deleted = false;

    @Column(nullable = false)
    @LastModifiedDate
    private Instant lastModifiedDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @ManyToOne
    private Question question;

    @Enumerated(EnumType.STRING)
    private VoteType type;
}
