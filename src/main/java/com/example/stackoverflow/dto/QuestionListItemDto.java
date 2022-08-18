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

    private String authorUsername;

}
