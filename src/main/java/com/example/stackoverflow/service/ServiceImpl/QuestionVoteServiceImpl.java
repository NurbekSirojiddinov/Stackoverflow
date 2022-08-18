package com.example.stackoverflow.service.ServiceImpl;

import com.example.stackoverflow.dto.QuestionVoteDto;
import com.example.stackoverflow.service.QuestionVoteService;
import org.springframework.stereotype.Service;

@Service
public class QuestionVoteServiceImpl implements QuestionVoteService {
    @Override
    public QuestionVoteDto upVote(Long questionId) {
        return null;
    }

    @Override
    public QuestionVoteDto downVote(Long questionId) {
        return null;
    }

    @Override
    public QuestionVoteDto revertVote(Long questionId) {
        return null;
    }
}
