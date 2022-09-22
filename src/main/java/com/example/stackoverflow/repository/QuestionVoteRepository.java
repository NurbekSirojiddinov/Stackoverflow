package com.example.stackoverflow.repository;

import com.example.stackoverflow.entity.QuestionVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionVoteRepository extends JpaRepository<QuestionVote, Long> {

    Optional<QuestionVote> findByQuestion_IdAndCreatedByAndDeletedFalse(Long questionId, String username);

}
