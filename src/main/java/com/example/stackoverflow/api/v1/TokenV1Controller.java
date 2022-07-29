package com.example.stackoverflow.api.v1;

import com.example.stackoverflow.security.filter.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/token/v1")
public class TokenV1Controller {
    private final TokenService tokenService;

    public TokenV1Controller(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/refresh")
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        tokenService.refreshToken(request, response);
    }
}
