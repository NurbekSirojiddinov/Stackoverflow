package com.example.stackoverflow.dto;

import com.example.stackoverflow.entity.QuestionVote;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionVoteDto {

    private Long id;

    private Long questionId;

    private VoteType type;

    public static QuestionVoteDto fromQuestionVote(final QuestionVote vote) {
        QuestionVoteDto dto = new QuestionVoteDto();
        dto.setId(vote.getId());
        dto.setQuestionId(vote.getQuestion().getId());
        dto.setType(vote.getType());
        return  dto;
    }
}
