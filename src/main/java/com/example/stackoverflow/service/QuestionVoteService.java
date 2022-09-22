package com.example.stackoverflow.service;

import com.example.stackoverflow.dto.QuestionVoteDto;

public interface QuestionVoteService {

    QuestionVoteDto upVote(final Long questionId, final String username);

    QuestionVoteDto downVote(final Long questionId, final String username);

    QuestionVoteDto revertVote(final Long questionId, final String username);
}
