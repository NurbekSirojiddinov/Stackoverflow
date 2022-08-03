package com.example.stackoverflow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionVoteDto {

    private Long id;

    private Long questionId;

    private VoteType type;
}
