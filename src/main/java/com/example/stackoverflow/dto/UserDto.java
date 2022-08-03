package com.example.stackoverflow.dto;

import com.example.stackoverflow.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long id;

    private String name;

    public static UserDto fromUser(final UserEntity user) {
        final UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());

        return dto;
    }

}
