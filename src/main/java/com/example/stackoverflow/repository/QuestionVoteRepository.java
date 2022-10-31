package com.example.stackoverflow.repository;

import com.example.stackoverflow.dto.VoteType;
import com.example.stackoverflow.entity.Question;
import com.example.stackoverflow.entity.QuestionVote;
import com.example.stackoverflow.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionVoteRepository extends JpaRepository<QuestionVote, UserEntity> {

    Optional<QuestionVote> findByQuestion_IdAndCreatedByAndDeletedFalse(Long questionId, String username);

    long countByQuestionAndTypeAndDeletedFalse(Question question, VoteType voteType);
}
