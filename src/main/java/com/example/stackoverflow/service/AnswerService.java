package com.example.stackoverflow.service;

import com.example.stackoverflow.dto.AnswerDto;
import com.example.stackoverflow.dto.AnswerForm;

public interface AnswerService {

    AnswerDto add(AnswerForm form);

    AnswerDto delete(Long answerId);

    AnswerDto update(Long answerId, AnswerForm form);
}
