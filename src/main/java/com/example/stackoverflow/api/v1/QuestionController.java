package com.example.stackoverflow.api.v1;

import com.example.stackoverflow.dto.QuestionForm;
import com.example.stackoverflow.service.QuestionService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/question/v1")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody QuestionForm form) {
        return ResponseEntity.ok(questionService.add(form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(questionService.delete(id));
    }

    @GetMapping("/find/all")
    public ResponseEntity<?> findAllByCategory(Long categoryId, Pageable pageable) {
        return ResponseEntity.ok(questionService.findAllByCategory(categoryId, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(questionService.findOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody QuestionForm form) {
        return ResponseEntity.ok(questionService.update(id, form));
    }
}
