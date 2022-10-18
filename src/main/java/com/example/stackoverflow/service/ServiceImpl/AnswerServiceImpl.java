package com.example.stackoverflow.service.ServiceImpl;

import com.example.stackoverflow.dto.AnswerDto;
import com.example.stackoverflow.dto.AnswerForm;
import com.example.stackoverflow.entity.Answer;
import com.example.stackoverflow.entity.Question;
import com.example.stackoverflow.repository.AnswerRepository;
import com.example.stackoverflow.service.AnswerService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final EntityManager entityManager;

    public AnswerServiceImpl(AnswerRepository answerRepository, EntityManager entityManager) {
        this.answerRepository = answerRepository;
        this.entityManager = entityManager;
    }

    @Override
    public AnswerDto add(AnswerForm form) {
        return save(new Answer(), form);
    }

    @Override
    public AnswerDto delete(Long answerId) {
        final Answer answer = answerRepository.getById(answerId);
        answer.setDeleted(true);
        return AnswerDto.fromAnswer(answerRepository.save(answer));
    }

    @Override
    public AnswerDto update(Long answerId, AnswerForm form) {
        return save(answerRepository.getById(answerId), form);
    }

    private AnswerDto save(final Answer answer, final AnswerForm form) {
        answer.setQuestion(entityManager.getReference(Question.class, form.getQuestionId()));
        answer.setContent(form.getContent());
        return AnswerDto.fromAnswer(answerRepository.save(answer));
    }
}
