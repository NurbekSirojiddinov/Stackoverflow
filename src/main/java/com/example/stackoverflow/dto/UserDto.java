package com.example.stackoverflow.dto;

import com.example.stackoverflow.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String userName;

    public static UserDto fromUser(final UserEntity user) {
        final UserDto dto = new UserDto();
        dto.setUserName(user.getName());

        return dto;
    }

}
