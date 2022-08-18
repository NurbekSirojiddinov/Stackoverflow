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
import java.util.Set;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @CreatedDate
    private Instant createdDate;

    @Column(nullable = false)
    @CreatedBy
    private String createdBy;

    @Column(nullable = false)
    @LastModifiedDate
    private Instant lastModifiedDate;

    @Column(nullable = false)
    @LastModifiedBy
    private String lastModifiedBy;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Category category;

    private String tittle;

    @Column(columnDefinition = "text")
    private String description;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean deleted = false;

    private Long viewCount;

    private Long voteCount;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private Set<Tag> tags;
}
