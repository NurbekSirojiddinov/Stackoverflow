package com.example.stackoverflow.dto;

import com.example.stackoverflow.entity.Answer;
import com.example.stackoverflow.entity.Question;
import com.example.stackoverflow.entity.QuestionVote;
import com.example.stackoverflow.repository.QuestionRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionDto extends QuestionListItemDto {

    private Boolean canVote;

    public static class Builder {

        private final Question question;

        private List<Answer> answers;

        private QuestionVote vote;

        public Builder(final Question question) {
            this.question=question;
        }

        public Builder vote(final QuestionVote vote) {
            this.vote = vote;
            return this;
        }

        public Builder answers(final List<Answer> answers) {
            this.answers=answers;
            return this;
        }

        public QuestionDto build() {
            QuestionDto questionDto = new QuestionDto();
            questionDto.setId(question.getId());
            questionDto.setTitle(question.getTittle());
            questionDto.setDescription(question.getDescription());
            questionDto.setTags(question.getTags().stream().map(TagDto::fromTag).collect(Collectors.toSet()));
            questionDto.setVoteCount(question.getVoteCount());
            questionDto.setViewCount(question.getViewCount());
            questionDto.setCreatedDate(question.getCreatedDate());
            if (question.getCreatedBy() != null) {
                questionDto.setAuthor(UserDto.fromUser(question.getCreatedBy()));
            }

            if (this.vote != null) {
                // TODO: 18/08/22 set votes
            }

            if (this.answers != null) {
                // TODO: 18/08/22 set answers
            }

            return questionDto;
        }

    }
}
