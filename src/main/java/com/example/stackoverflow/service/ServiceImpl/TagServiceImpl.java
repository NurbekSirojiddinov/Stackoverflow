package com.example.stackoverflow.service.ServiceImpl;

import com.example.stackoverflow.dto.TagDto;
import com.example.stackoverflow.entity.Tag;
import com.example.stackoverflow.repository.TagRepository;
import com.example.stackoverflow.service.TagService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }


    @Override
    public Page<TagDto> findALl(Pageable pageable) {
        return tagRepository.findAllByDeletedFalse(pageable)
                .map(TagDto::fromTag);
    }

    @Override
    public Page<TagDto> search(String searchTerm, Pageable pageable) {
        return tagRepository.findAllByTittleContainsAndDeletedFalse(searchTerm, pageable)
                .map(TagDto::fromTag);
    }

    @Override
    public TagDto add(TagDto form) {
        final Tag tag = new Tag();
        tag.setTittle(form.getTitle());
        tag.setCreatedDate(Instant.now());
        tag.setLastModifiedDate(Instant.now());
        return TagDto.fromTag(tagRepository.save(tag));
    }

    @Override
    public TagDto update(Long id, TagDto form) {
        final Tag tag = tagRepository.getById(id);
        tag.setTittle(form.getTitle());
        tag.setLastModifiedDate(Instant.now());
        return null;
    }

    @Override
    public TagDto delete(Long id) {
        final Tag tag = tagRepository.getById(id);
        tag.setDeleted(true);
        return TagDto.fromTag(tagRepository.save(tag));
    }
}
