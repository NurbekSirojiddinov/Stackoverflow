package com.example.stackoverflow.entity;

import com.example.stackoverflow.dto.UserResponse;
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

@Entity(name = "my_user")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @CreatedDate
    private Instant createdDate;

    @ColumnDefault("false")
    private boolean confirmed = false;

    @ColumnDefault("false")
    private boolean deleted = false;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    @LastModifiedDate
    private Instant lastModifiedDate;
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    public UserResponse toPojo() {
        UserResponse response = new UserResponse();
        response.setCreatedDate(createdDate);
        response.setName(name);
        response.setUsername(username);
        response.setRole(role);
        response.setLastModifiedDate(lastModifiedDate);

        return response;
    }
}
