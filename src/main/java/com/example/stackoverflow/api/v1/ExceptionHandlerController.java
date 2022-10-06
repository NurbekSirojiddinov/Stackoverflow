package com.example.stackoverflow.api.v1;

import com.example.stackoverflow.dto.ErrorDto;
import com.example.stackoverflow.exception.CategoryNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidExceptionHandler() {
        return ResponseEntity.badRequest()
                .body(ErrorDto.fromCustomException(3, "MethodArgumentNotValidException", "Validation failed!"));
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<?> categoryNotFoundExceptionHandler(CategoryNotFoundException exception) {
        return ResponseEntity.badRequest()
                .body(ErrorDto.fromCustomException(5, "INVALID_CATEGORY_ID",exception.getLocalizedMessage()));
    }

}
