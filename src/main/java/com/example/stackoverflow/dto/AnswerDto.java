package com.example.stackoverflow.dto;

import com.example.stackoverflow.entity.Answer;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnswerDto implements Serializable {

    private Long id;

    private String content;

    private Long voteCount;

    private Instant createdDate;

    private String author;

    public static AnswerDto fromAnswer(final Answer answer) {
        final AnswerDto dto = new AnswerDto();
        dto.setId(answer.getId());
        dto.setContent(answer.getContent());
        dto.setVoteCount(answer.getVoteCount());
        dto.setCreatedDate(answer.getCreatedDate());
        dto.setAuthor(answer.getCreatedBy());
        return dto;
    }

}
