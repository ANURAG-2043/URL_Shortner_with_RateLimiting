package com.urlshortener.ratelimiter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * Rate Limiter Filter
 *
 * Limits number of requests per IP using Redis
 */

@Component
public class RateLimiterFilter implements Filter {

    @Autowired
    private StringRedisTemplate redisTemplate;

    // maximum allowed requests
    private static final int MAX_REQUESTS = 100;

    // time window in seconds
    private static final int WINDOW = 60;

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // get client IP
        String ip = httpRequest.getRemoteAddr();

        // Redis key
        String key = "rate_limit:" + ip;

        // increment request count
        Long requestCount = redisTemplate.opsForValue().increment(key);

        // set expiry for window
        if (requestCount == 1) {
            redisTemplate.expire(key, WINDOW, TimeUnit.SECONDS);
        }

        // check limit
        if (requestCount > MAX_REQUESTS) {

            httpResponse.setStatus(429);
            httpResponse.getWriter().write("Too Many Requests");

            return;
        }

        // continue request
        chain.doFilter(request, response);
    }
}