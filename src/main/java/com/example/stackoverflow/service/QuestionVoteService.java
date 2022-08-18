package com.example.stackoverflow.service;

import com.example.stackoverflow.dto.QuestionVoteDto;

public interface QuestionVoteService {

    QuestionVoteDto upVote(final Long questionId);

    QuestionVoteDto downVote(final Long questionId);

    QuestionVoteDto revertVote(final Long questionId);
}
