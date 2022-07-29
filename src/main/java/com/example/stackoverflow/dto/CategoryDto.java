package com.example.stackoverflow.dto;

import com.example.stackoverflow.entity.Category;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDto {

    private Long id;

    private String title;

    private String description;

    private Long parentId;

    private String icon;

    public static CategoryDto fromCategory(final Category category) {
        final CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setDescription(category.getDescription());
        categoryDto.setTitle(category.getTittle());
        if (category.getParent() != null) {
            categoryDto.setParentId(category.getParent().getId());
        }
        categoryDto.setIcon(category.getIconCode());
        return categoryDto;
    }
}
