package com.example.stackoverflow.service.ServiceImpl;

import com.example.stackoverflow.dto.QuestionDto;
import com.example.stackoverflow.dto.QuestionForm;
import com.example.stackoverflow.dto.QuestionListItemDto;
import com.example.stackoverflow.entity.Answer;
import com.example.stackoverflow.entity.Category;
import com.example.stackoverflow.entity.Question;
import com.example.stackoverflow.entity.Tag;
import com.example.stackoverflow.repository.AnswerRepository;
import com.example.stackoverflow.repository.QuestionRepository;
import com.example.stackoverflow.service.QuestionService;
import com.example.stackoverflow.service.TagService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final AnswerRepository answerRepository;
    private final EntityManager entityManager;
    private final QuestionRepository questionRepository;
    private final TagService tagService;

    public QuestionServiceImpl(QuestionRepository questionRepository, AnswerRepository answerRepository, EntityManager entityManager, TagService tagService) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.entityManager = entityManager;
        this.tagService = tagService;
    }

    @Override
    public Page<QuestionListItemDto> findAllByCategory(Long categoryId, Pageable pageable) {
        return questionRepository.findAllByCategory_Id(categoryId, pageable)
                .map(question -> new QuestionDto.Builder(question).build());
    }

    @Override
    public QuestionDto findOne(Long id) {
        final Optional<Question> question = questionRepository.findById(id);
        if(question.isPresent()) {
        question.get().setViewCount(question.get().getViewCount()+1);
        questionRepository.save(question.get());
        final List<Answer> answers = answerRepository.findAllByQuestionAndDeletedFalse(question.get());
        return new QuestionDto.Builder(question.get()).answers(answers).build();}
        return null;
    }

    @Override
    public QuestionListItemDto add(QuestionForm form) {
        Assert.notNull(form.getCategoryId(), "CategoryId cannot be null");
        return save(new Question(), form);
    }

    @Override
    public QuestionListItemDto update(Long id, QuestionForm form) {
        Assert.notNull(id, "Id cannot be null");
        return save(questionRepository.getById(id), form);
    }

    @Override
    public QuestionListItemDto delete(Long id) {
        Assert.notNull(id, "id cannot be null");
        final Question question = questionRepository.getById(id);
        question.setDeleted(true);
        return new QuestionDto.Builder(questionRepository.save(question)).build();
    }

    private QuestionListItemDto save(final Question question, final QuestionForm form) {
        question.setCategory(entityManager.getReference(Category.class, form.getCategoryId()));
        question.setTittle(form.getTittle());
        question.setDescription(form.getDescription());
        question.setCreatedDate(Instant.now());
        question.setLastModifiedDate(Instant.now());
        if (form.getTags() != null) {
            question.setTags(form.getTags().stream().map(dto -> {
                if (dto.getId() == null) {
                    dto = tagService.add(dto);
                }
                return entityManager.getReference(Tag.class, dto.getId());
            }).collect(Collectors.toSet()));
        }
        return new QuestionDto.Builder(questionRepository.save(question)).build();
    }
}
