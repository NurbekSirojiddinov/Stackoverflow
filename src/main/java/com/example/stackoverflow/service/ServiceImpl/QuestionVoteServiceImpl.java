package com.example.stackoverflow.service.ServiceImpl;

import com.example.stackoverflow.dto.QuestionVoteDto;
import com.example.stackoverflow.dto.VoteType;
import com.example.stackoverflow.entity.Question;
import com.example.stackoverflow.entity.QuestionVote;
import com.example.stackoverflow.entity.UserEntity;
import com.example.stackoverflow.repository.QuestionVoteRepository;
import com.example.stackoverflow.repository.UserRepository;
import com.example.stackoverflow.service.QuestionVoteService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.example.stackoverflow.dto.VoteType.DOWN;
import static com.example.stackoverflow.dto.VoteType.UP;

@Service
public class QuestionVoteServiceImpl implements QuestionVoteService {

    private final QuestionVoteRepository questionVoteRepository;
    private final UserRepository userRepository;
    private final EntityManager entityManager;

    public QuestionVoteServiceImpl(QuestionVoteRepository questionVoteRepository, UserRepository userRepository, EntityManager entityManager) {
        this.questionVoteRepository = questionVoteRepository;
        this.userRepository = userRepository;
        this.entityManager = entityManager;
    }

    @Override
    public QuestionVoteDto upVote(Long questionId, String username) {
        return createVote(questionId, username, UP);
    }

    @Override
    public QuestionVoteDto downVote(Long questionId, String username) {
       return createVote(questionId, username, DOWN);
    }

    @Override
    public QuestionVoteDto revertVote(Long questionId, String username) {
        var optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            var optionalQuestionVote = questionVoteRepository
                    .findByQuestion_IdAndCreatedByAndDeletedFalse(questionId, optionalUser.get());
            if (optionalQuestionVote.isPresent()) {
               final QuestionVote vote = optionalQuestionVote.get();
               vote.setDeleted(true);
               reCalculateQuestionVoteCount(questionId);
               return QuestionVoteDto.fromQuestionVote(questionVoteRepository.save(vote));
            }
            throw new IllegalStateException("This vote can't be reverted!");
        }
        throw new NoSuchElementException("Such user is not found!");
    }

    private void reCalculateQuestionVoteCount(final Long questionId) {
    }

    private QuestionVoteDto createVote(final Long questionId, final String username, final VoteType type) {
        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            Optional<QuestionVote> optionalQuestionVote = questionVoteRepository
                    .findByQuestion_IdAndCreatedByAndDeletedFalse(questionId, optionalUser.get());
            if (optionalQuestionVote.isEmpty()) {
                final QuestionVote vote = new QuestionVote();
                vote.setQuestion(entityManager.getReference(Question.class, questionId));
                vote.setType(type);
                reCalculateQuestionVoteCount(questionId);
                return QuestionVoteDto.fromQuestionVote(questionVoteRepository.save(vote));
            }
            throw new IllegalStateException("User already voted for this question!");
        }
        throw new NoSuchElementException("Such user is not found!");
    }
}
