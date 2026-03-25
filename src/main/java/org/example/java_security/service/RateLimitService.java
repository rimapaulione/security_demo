package org.example.java_security.service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimitService {

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    private Bucket createBucket(int capacity, int refillTokens, Duration duration) {
        return Bucket.builder()
                .addLimit(Bandwidth.classic(capacity, Refill.greedy(refillTokens, duration)))
                .build();
    }

    private Bucket resolveBucket(String key, int capacity, int refillTokens, Duration duration) {
        return buckets.computeIfAbsent(key, k -> createBucket(capacity, refillTokens, duration));
    }

    public void checkRegistrationLimit(String ip) {
        checkLimit("register:" + ip, 5, 5, Duration.ofMinutes(1));
    }

    public void checkAdminActionLimit(String username) {
        checkLimit("admin:" + username, 10, 10, Duration.ofMinutes(1));
    }

    private void checkLimit(String key, int capacity, int refillTokens, Duration duration) {
        Bucket bucket = resolveBucket(key, capacity, refillTokens, duration);

        if (!bucket.tryConsume(1)) {
            throw new RuntimeException("Too many requests. Please try again later.");
        }
    }
}
