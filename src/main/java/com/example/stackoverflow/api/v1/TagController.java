package com.example.stackoverflow.api.v1;

import com.example.stackoverflow.dto.TagDto;
import com.example.stackoverflow.service.TagService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tag")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable) {
        return ResponseEntity.ok(tagService.findALl(pageable));
    }

    @GetMapping("/ search")
    public ResponseEntity<?> search(@RequestParam(name = "q") final String searchTerm, Pageable pageable) {
        return ResponseEntity.ok(tagService.search(searchTerm, pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody TagDto form) {
        return ResponseEntity.ok(tagService.add(form));
    }
}
