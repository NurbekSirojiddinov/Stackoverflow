package com.example.stackoverflow.api.v1;

import com.example.stackoverflow.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ViewController {

    @GetMapping("")
    public String viewHomepage() {
        return "index";
    }

    @GetMapping("/register")
    public String signUp(final Model model) {
        model.addAttribute("user", new UserEntity());

        return "sign_up";
    }

    @GetMapping("/ask_question")
    public String askQuestion() {
        return "question";
    }

    @GetMapping("/logged_in")
    public String signIn() {
        return "success_login";
    }
}
