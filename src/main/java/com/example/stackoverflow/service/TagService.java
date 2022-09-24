package com.example.stackoverflow.service;

import com.example.stackoverflow.dto.TagDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TagService {

    Page<TagDto> findALl(Pageable pageable);

    Page<TagDto> search(final String searchTerm, Pageable pageable);

    TagDto add(final TagDto form);

    TagDto update(final Long id,final TagDto form);

    TagDto delete(final Long id);
}
