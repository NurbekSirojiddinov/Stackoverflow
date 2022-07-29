package com.example.stackoverflow.api.v1;

import com.example.stackoverflow.dto.CategoryDto;
import com.example.stackoverflow.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category/v1")
public class CategoryV1Controller {
    private final CategoryService service;

    public CategoryV1Controller(CategoryService service) {
        this.service = service;
    }

    @GetMapping("/find/all")
    public ResponseEntity<?> findAllByParent(@RequestParam(required = false) Long id) {
        return ResponseEntity.ok(service.findAll(id));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findOneById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'MODERATOR')")
    public ResponseEntity<?> add(@RequestBody CategoryDto request) {
        return ResponseEntity.ok(service.add(request));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'MODERATOR')")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CategoryDto partialUpdate) {
        return ResponseEntity.ok(service.update(id, partialUpdate));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
