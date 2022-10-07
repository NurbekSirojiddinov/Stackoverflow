package com.example.stackoverflow.service;

import com.example.stackoverflow.dto.QuestionDto;
import com.example.stackoverflow.dto.QuestionForm;
import com.example.stackoverflow.dto.QuestionListItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionService {

    Page<QuestionListItemDto> findAllByCategory(Long categoryId, Pageable pageable);

    QuestionDto findOne(Long id);

    QuestionListItemDto add(QuestionForm form);

    QuestionListItemDto update(Long id, QuestionForm form);

    QuestionListItemDto delete(Long id);

}
