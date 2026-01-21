package com.example.api_gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * Custom key resolver for rate limiting based on client IP address.
 *
 * <p>This component implements the {@link KeyResolver} interface to provide
 * per-client rate limiting functionality. Each unique IP address is tracked
 * independently, allowing fair resource allocation across different clients.</p>
 *
 * <p>The resolver is registered as a Spring bean with the name "ipKeyResolver"
 * and is referenced in the gateway configuration for the RequestRateLimiter filter.</p>
 *
 * <p><strong>Usage in application.yaml:</strong></p>
 * <pre>
 * filters:
 *   - name: RequestRateLimiter
 *     args:
 *       key-resolver: "#{@ipKeyResolver}"
 * </pre>
 *
 * @author Pial Kanti Samadder
 * @since 1.0
 * @see KeyResolver
 * @see org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter
 */
@Component("ipKeyResolver")
public class IpKeyResolver implements KeyResolver {

    /**
     * Resolves the rate limiting key from the incoming request.
     *
     * <p>This implementation extracts the client's IP address from the request
     * to use as the rate limiting key. Each unique IP address will have its
     * own rate limit bucket.</p>
     *
     * @param exchange the current server web exchange containing request details
     * @return a {@link Mono} emitting the client's IP address as the rate limit key
     * @throws NullPointerException if the remote address cannot be determined
     */
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        // Extract the client IP address from the request's remote address
        String clientIp = Objects.requireNonNull(
            exchange.getRequest().getRemoteAddress()
        ).getAddress().getHostAddress();

        return Mono.just(clientIp);
    }
}
