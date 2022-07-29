package com.example.stackoverflow.entity;

import lombok.Getter;
import lombok.Setter;
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

    @Column(nullable = false)
    @CreatedBy
    private Instant createdBy;

    @Column(nullable = false)
    @LastModifiedDate
    private Instant lastModifiedDate;

    @Column(nullable = false)
    @LastModifiedBy
    private Instant lastModifiedBy;

    @ManyToOne
    private Question question;
}
