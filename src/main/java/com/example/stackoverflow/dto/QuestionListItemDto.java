package com.example.stackoverflow.dto;

import com.example.stackoverflow.entity.Question;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionListItemDto {

    private Long id;

    private String title;

    private String description;

    Set<TagDto> tags;

    private Long viewCount;

    private Long voteCount;

    private Instant createdDate;

    private UserDto author;

    public static QuestionListItemDto fromQuestion(final Question question, final UserDto user) {
        final QuestionListItemDto questionDto = new QuestionListItemDto();
        questionDto.setId(question.getId());
        questionDto.setTitle(question.getTittle());
        questionDto.setDescription(question.getDescription());
        questionDto.setTags(question.getTags().stream().map(TagDto::fromTag).collect(Collectors.toSet()));
        questionDto.setVoteCount(question.getVoteCount());
        questionDto.setViewCount(question.getViewCount());
        questionDto.setCreatedDate(question.getCreatedDate());
        questionDto.setAuthor(user);

        return questionDto;
    }
}
