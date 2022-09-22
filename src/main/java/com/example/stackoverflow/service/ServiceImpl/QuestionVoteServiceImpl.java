package com.example.stackoverflow.service.ServiceImpl;

import com.example.stackoverflow.dto.QuestionVoteDto;
import com.example.stackoverflow.repository.QuestionVoteRepository;
import com.example.stackoverflow.repository.UserRepository;
import com.example.stackoverflow.service.QuestionVoteService;
import org.springframework.stereotype.Service;

@Service
public class QuestionVoteServiceImpl implements QuestionVoteService {

    private final QuestionVoteRepository questionVoteRepository;
    private final UserRepository userRepository;

    public QuestionVoteServiceImpl(QuestionVoteRepository questionVoteRepository, UserRepository userRepository) {
        this.questionVoteRepository = questionVoteRepository;
        this.userRepository = userRepository;
    }

    @Override
    public QuestionVoteDto upVote(Long questionId, String username) {
        return null;
    }

    @Override
    public QuestionVoteDto downVote(Long questionId, String username) {
        return null;
    }

    @Override
    public QuestionVoteDto revertVote(Long questionId, String username) {
        return null;
    }
}
