package com.example.stackoverflow.api.v1;

import com.example.stackoverflow.dto.AddNewUserRequest;
import com.example.stackoverflow.dto.UserResponse;
import com.example.stackoverflow.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
@RestController
@RequestMapping("/api/user/v1")
public class UserV1Controller {
   private final UserService userService;

    public UserV1Controller(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/add",
    consumes = MediaType.ALL_VALUE)
    public UserResponse addUser(@RequestBody final AddNewUserRequest request) {
         return userService.addUser(request);
    }
}