package com.example.stackoverflow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDto {

    private int code;

    private String error;

    private String description;

    public static ErrorDto fromException(Exception exception) {
        final ErrorDto dto = new ErrorDto();
        dto.setCode(1);
        dto.error = "ERROR";
        dto.description = exception.getLocalizedMessage();
        return dto;
    }

    public static ErrorDto fromCustomException(int code, String error, String description) {
        final ErrorDto dto = new ErrorDto();
        dto.setCode(code);
        dto.setError(error);
        dto.setDescription(description);
        return dto;
    }
}
