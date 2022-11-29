package com.example.stackoverflow.service;

import com.example.stackoverflow.dto.AddNewUserRequest;
import com.example.stackoverflow.dto.UserDtoToAdmin;
import com.example.stackoverflow.dto.UserResponse;
import com.example.stackoverflow.entity.UserEntity;
import com.example.stackoverflow.service.ServiceImpl.UserNotFoundException;

import java.util.List;

public interface UserService {
    UserResponse addUser(final AddNewUserRequest request);

    UserResponse getUser(final String username);

    List<UserDtoToAdmin> findAllUsers();

    UserEntity findById(final Long id) throws UserNotFoundException;

    void delete(Long id) throws UserNotFoundException;

}
