package com.example.stackoverflow.dto;

import com.example.stackoverflow.entity.Tag;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagDto {

    private Long id;

    private String title;

    public static TagDto fromTag(final Tag tag) {
        final TagDto dto = new TagDto();
        dto.setId(tag.getId());
        dto.setTitle(tag.getTittle());

        return dto;
    }
}
