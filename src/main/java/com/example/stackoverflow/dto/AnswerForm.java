package com.example.stackoverflow.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class AnswerForm {

    private Long questionId;

    @Size(min = 10)
    private String content;
}
