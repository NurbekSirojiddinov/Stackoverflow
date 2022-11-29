package com.example.stackoverflow.service.ServiceImpl;

public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String formatted) {
        super(formatted);
    }
}
