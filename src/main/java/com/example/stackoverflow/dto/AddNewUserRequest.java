package com.example.stackoverflow.dto;

import com.example.stackoverflow.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddNewUserRequest implements Serializable {
    private String username;
    private String name;
    private String password;
    private String email;
    private Role role;

    @Override
    public String toString() {
        return "AddNewUserRequest{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
