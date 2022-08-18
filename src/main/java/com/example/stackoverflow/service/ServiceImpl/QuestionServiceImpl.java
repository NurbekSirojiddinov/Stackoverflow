package com.example.stackoverflow.service.ServiceImpl;

import com.example.stackoverflow.dto.QuestionDto;
import com.example.stackoverflow.dto.QuestionForm;
import com.example.stackoverflow.dto.QuestionListItemDto;
import com.example.stackoverflow.entity.Answer;
import com.example.stackoverflow.entity.Question;
import com.example.stackoverflow.repository.AnswerRepository;
import com.example.stackoverflow.repository.QuestionRepository;
import com.example.stackoverflow.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
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
    public QuestionListItemDto add(QuestionForm questionDto) {
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
