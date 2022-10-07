package com.example.stackoverflow.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionForm {

    private Long categoryId;

    @Size(min = 5)
    private String tittle;

    @Size(min = 15)
    private String description;

    private Set<TagDto> tags;
}
