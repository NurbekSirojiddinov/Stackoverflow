package com.example.stackoverflow.dto;

import com.example.stackoverflow.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
public class UserResponse implements Serializable {
    private String username;
    private String name;
    private Role role;
    private Instant createdDate;
    private Instant lastModifiedDate;

    @Override
    public String toString() {
        return "UserResponse{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
