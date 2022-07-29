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
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @CreatedDate
    private Instant createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @CreatedBy
    private UserEntity createdBy;

    @Column(nullable = false)
    @LastModifiedDate
    private Instant lastModifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @LastModifiedBy
    private UserEntity lastModifiedBy;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean deleted = false;

    @Column(unique = true)
    private String tittle;
}
