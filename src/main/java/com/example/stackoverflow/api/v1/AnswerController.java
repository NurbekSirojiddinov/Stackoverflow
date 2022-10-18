package com.example.stackoverflow.api.v1;

import com.example.stackoverflow.dto.AnswerForm;
import com.example.stackoverflow.service.AnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/answer/v1")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody AnswerForm form) {
        return ResponseEntity.ok(answerService.add(form));
    }

    @DeleteMapping("/{answerId}")
    public ResponseEntity<?> delete(@PathVariable Long answerId) {
        return ResponseEntity.ok(answerService.delete(answerId));
    }

    @PutMapping("/{answerId}")
    public ResponseEntity<?> update(@PathVariable Long answerId, @RequestBody AnswerForm form) {
        return ResponseEntity.ok(answerService.update(answerId, form));
    }
}
