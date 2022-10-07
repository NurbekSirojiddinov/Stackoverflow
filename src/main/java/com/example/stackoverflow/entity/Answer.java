package com.example.stackoverflow.entity;

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
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity createdBy;

    @Column(nullable = false)
    @CreatedDate
    private Instant createdDate;

    @Column(columnDefinition = "text")
    private String content;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean deleted = false;

    @Column(nullable = false)
    @LastModifiedDate
    private Instant lastModifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @LastModifiedBy
    private UserEntity lastModifiedBy;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Question question;

    @Column(nullable = false)
    private Long voteCount = 0L;
}
