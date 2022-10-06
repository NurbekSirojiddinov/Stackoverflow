package com.example.stackoverflow.api.v1;

import com.example.stackoverflow.service.QuestionVoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/question-vote")
public class QuestionVoteV1Controller {

    private final QuestionVoteService questionVoteService;

    public QuestionVoteV1Controller(QuestionVoteService questionVoteService) {
        this.questionVoteService = questionVoteService;
    }

    @PutMapping("{questionId}/up")
    public ResponseEntity<?> upVote(@PathVariable Long questionId, final Principal principal) {
        return ResponseEntity.ok(questionVoteService.upVote(questionId, principal.getName()));
    }

    @PutMapping("{questionId}/down")
    public ResponseEntity<?> downVote(@PathVariable Long questionId, final Principal principal) {
        return ResponseEntity.ok(questionVoteService.downVote(questionId, principal.getName()));
    }
    @PutMapping("{questionId}/revert")
    public ResponseEntity<?> revertVote(@PathVariable Long questionId, final Principal principal) {
        return ResponseEntity.ok(questionVoteService.revertVote( questionId, principal.getName()));
    }
}
