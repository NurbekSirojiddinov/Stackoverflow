package com.example.stackoverflow.repository;

import com.example.stackoverflow.entity.Answer;
import com.example.stackoverflow.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAllByQuestion(Question question);

}
