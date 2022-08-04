package com.example.stackoverflow.api.v1;

import com.example.stackoverflow.rateLimiting.PricingPlanService;
import com.example.stackoverflow.security.filter.TokenService;
import io.github.bucket4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/token/v1")
public class TokenV1Controller {
    private final TokenService tokenService;
    private final PricingPlanService pricingPlanService;

    public TokenV1Controller(TokenService tokenService, PricingPlanService pricingPlanService) {
        this.tokenService = tokenService;
        this.pricingPlanService = pricingPlanService;
    }

    @PostMapping("/refresh")
    ResponseEntity<?> refreshToken(@RequestHeader(value = "x-api-key") String apiKey,
            HttpServletRequest request, HttpServletResponse response) throws IOException {
        Bucket bucket = pricingPlanService.resolveBucket(apiKey);
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        if (probe.isConsumed()) {
            tokenService.refreshToken(request, response);
            return ResponseEntity.ok()
                    .header("X-Rate-Limit-Remaining", Long.toString(probe.getRemainingTokens())).build();
            }

        long waitForRefill = probe.getNanosToWaitForRefill() / 60000000000L;
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .header("X-Rate-Limit-Retry-After-Minutes", String.valueOf(waitForRefill))
                .build();
    }
}
