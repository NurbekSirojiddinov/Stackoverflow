package com.example.stackoverflow.rateLimiting;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Refill;

import java.time.Duration;

public enum PricingPlan {

    FREE(3);

    private final int  bucketCapacity;

    PricingPlan(int bucketCapacity) {
        this.bucketCapacity = bucketCapacity;
    }

    Bandwidth getLimit() {
        return Bandwidth.classic(bucketCapacity, Refill.intervally(bucketCapacity, Duration.ofMinutes(1)));
    }

    public int bucketCapacity() {
        return bucketCapacity;
    }

    static PricingPlan resolvePlanFromApiKey(String apiKey) {
        return FREE;
    }
}
