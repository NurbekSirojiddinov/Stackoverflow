package com.example.stackoverflow.api.v1;

import com.example.stackoverflow.dto.TagDto;
import com.example.stackoverflow.service.TagService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tag/v1")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/find/all")
    public ResponseEntity<?> findAll(Pageable pageable) {
        return ResponseEntity.ok(tagService.findAll(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(name = "q") final String searchTerm, Pageable pageable) {
        return ResponseEntity.ok(tagService.search(searchTerm, pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Validated @RequestBody final TagDto form) {
        return ResponseEntity.ok(tagService.add(form));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Validated @RequestBody final  TagDto form, @PathVariable Long id) {
        return ResponseEntity.ok(tagService.update(id, form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(tagService.delete(id));
    }
}
