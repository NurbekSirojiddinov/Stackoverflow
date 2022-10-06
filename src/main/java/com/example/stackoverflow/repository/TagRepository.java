package com.example.stackoverflow.repository;

import com.example.stackoverflow.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    Page<Tag> findAllByTittleContainsAndDeletedFalse(final String searchTerm, Pageable pageable);

    Page<Tag> findAllByDeletedFalse(Pageable pageable);

}
