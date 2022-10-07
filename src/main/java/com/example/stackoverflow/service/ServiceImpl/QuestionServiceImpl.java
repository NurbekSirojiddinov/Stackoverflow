package com.example.stackoverflow.service.ServiceImpl;

import com.example.stackoverflow.dto.QuestionDto;
import com.example.stackoverflow.dto.QuestionForm;
import com.example.stackoverflow.dto.QuestionListItemDto;
import com.example.stackoverflow.entity.Answer;
import com.example.stackoverflow.entity.Category;
import com.example.stackoverflow.entity.Question;
import com.example.stackoverflow.repository.AnswerRepository;
import com.example.stackoverflow.repository.QuestionRepository;
import com.example.stackoverflow.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final AnswerRepository answerRepository;
    private final EntityManager entityManager;
    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, AnswerRepository answerRepository, EntityManager entityManager) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.entityManager = entityManager;
    }

    @Override
    public Page<QuestionListItemDto> findAllByCategory(Long categoryId, Pageable pageable) {
        return questionRepository.findAllByCategory_Id(categoryId, pageable)
                .map(question -> new QuestionDto.Builder(question).build());
    }

    @Override
    public QuestionDto findOne(Long id) {
        final Question question = questionRepository.getById(id);
        final List<Answer> answers = answerRepository.findAllByQuestion(question);
        return new QuestionDto.Builder(question).answers(answers).build();
    }

    @Override
    public QuestionListItemDto add(QuestionForm form) {
        final Question question = new Question();
        question.setCategory(entityManager.getReference(Category.class, form.getCategoryId()));
        question.setTittle(form.getTittle());
        question.setDescription(form.getDescription());
        question.setCreatedDate(Instant.now());
        question.setLastModifiedDate(Instant.now());
        return null;
    }

    @Override
    public QuestionListItemDto update(Long id, QuestionForm form) {
        return null;
    }

    @Override
    public QuestionListItemDto delete(Long id) {
        final Question question = questionRepository.getById(id);
        question.setDeleted(true);
        return new QuestionDto.Builder(questionRepository.save(question)).build();
    }
}
