package com.example.stackoverflow.service;

import com.example.stackoverflow.dto.AddNewUserRequest;
import com.example.stackoverflow.dto.UserResponse;

public interface UserService {
    UserResponse addUser(final AddNewUserRequest request);

    UserResponse getUser(final String username);
}
