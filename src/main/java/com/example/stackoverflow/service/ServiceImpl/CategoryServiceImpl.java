package com.example.stackoverflow.service.ServiceImpl;

import com.example.stackoverflow.dto.CategoryDto;
import com.example.stackoverflow.entity.Category;
import com.example.stackoverflow.exception.CategoryNotFoundException;
import com.example.stackoverflow.repository.CategoryRepository;
import com.example.stackoverflow.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.stackoverflow.dto.CategoryDto.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final EntityManager entityManager;

    public CategoryServiceImpl(CategoryRepository categoryRepository, EntityManager entityManager) {
        this.categoryRepository = categoryRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<CategoryDto> findAll(Long parentId) {
        return categoryRepository.findAllByParent_IdAndDeletedFalse(parentId)
                .stream().map(CategoryDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto findOne(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty() || optionalCategory.get().isDeleted()) {
            throw new CategoryNotFoundException(id);
        }
        return toDto(optionalCategory.get());
    }

    @Override
    public CategoryDto add(CategoryDto form) {
        final Category category = fillCategory(new Category(), form);
        category.setCreatedDate(Instant.now());
        category.setLastModifiedDate(Instant.now());
        return toDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto update(Long id, CategoryDto form) {
        return toDto(categoryRepository.save(fillCategory(categoryRepository.getById(id), form)));
    }

    @Override
    public CategoryDto delete(Long id) {
        final Category category = categoryRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NoSuchElementException(String.format("Category with [%s] not found", id));
                });
        category.setDeleted(true);
        categoryRepository.save(category);
        return toDto(category);
    }

    private Category fillCategory(Category category, CategoryDto form) {
        category.setDescription(form.getDescription());
        category.setIconCode(form.getIcon());
        category.setTittle(form.getTitle());
        if (form.getParentId() != null) {
            category.setParent(entityManager.getReference(Category.class, form.getParentId()));
        }
        return category;
    }
}
