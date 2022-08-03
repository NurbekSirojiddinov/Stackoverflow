package com.example.stackoverflow.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL )
public class QuestionDto extends QuestionListItemDto{

    private Boolean canVote;
}
