package com.example.stackoverflow.dto;

import com.example.stackoverflow.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class UserDtoToAdmin {
    Long id;
    String username;
    String email;
    String name;
    Role role;
    Instant createdDate;

    @Override
    public String toString() {
        return "UserDtoToAdmin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", createdDate=" + createdDate +
                '}';
    }
}
