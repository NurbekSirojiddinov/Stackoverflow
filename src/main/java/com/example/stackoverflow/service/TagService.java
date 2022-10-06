package com.example.stackoverflow.service;

import com.example.stackoverflow.dto.TagDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface TagService {

    Page<TagDto> findAll(Pageable pageable);

    Page<TagDto> search(final String searchTerm, Pageable pageable);

    TagDto add(final TagDto form);

    TagDto update(Long id, final TagDto form);

    TagDto delete(final Long id);

}
