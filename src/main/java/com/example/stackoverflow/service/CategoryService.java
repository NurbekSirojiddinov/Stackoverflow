package com.example.stackoverflow.service;

import com.example.stackoverflow.dto.CategoryDto;
import org.springframework.lang.Nullable;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll(@Nullable Long parentId);

    CategoryDto findOne(Long id);

    CategoryDto add(final CategoryDto form);

    CategoryDto update(Long id, final CategoryDto form);

    CategoryDto delete(Long id);
}
