package com.example.stackoverflow.exception;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(Long id) {
        super(String.format("Category not found with ID: %d", id));
    }
}
