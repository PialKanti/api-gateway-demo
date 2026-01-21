package com.example.api_gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * Custom gateway filter for API key authentication.
 *
 * <p>This filter validates incoming requests by checking for a valid API key
 * in the request headers. Requests without a valid API key are rejected with
 * a 401 Unauthorized response.</p>
 *
 * <p>The filter is registered as a Spring component and can be applied to routes
 * in the gateway configuration using the filter name "ApiKeyFilter".</p>
 *
 * <p><strong>Note:</strong> This implementation uses a hardcoded API key for
 * demonstration purposes. In production, keys should be stored securely and
 * validated against a database or external service.</p>
 *
 * @author Pial Kanti Samadder
 * @since 1.0
 * @see AbstractGatewayFilterFactory
 */
@Component
public class ApiKeyFilter extends AbstractGatewayFilterFactory<ApiKeyFilter.Config> {

    /** HTTP header name used to pass the API key. */
    private static final String API_KEY_HEADER = "X-API-KEY";

    /** Valid API key for authentication (demo purposes only). */
    private static final String VALID_API_KEY = "demo-key";

    /** JSON response body returned when authentication fails. */
    private static final String UNAUTHORIZED_RESPONSE = "{\"error\": \"Unauthorized\"}";

    /**
     * Constructs the ApiKeyFilter and registers the Config class with the parent factory.
     */
    public ApiKeyFilter() {
        super(Config.class);
    }

    /**
     * Creates and returns the gateway filter that performs API key validation.
     *
     * <p>The returned filter intercepts each request, extracts the API key from
     * the request headers, and either allows the request to proceed or returns
     * an unauthorized response.</p>
     *
     * @param config the filter configuration (currently unused but required by the factory pattern)
     * @return a {@link GatewayFilter} that validates API keys
     */
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // Extract API key from the request header
            String apiKey = exchange.getRequest().getHeaders().getFirst(API_KEY_HEADER);

            // Reject request if API key is missing or invalid
            if (!VALID_API_KEY.equals(apiKey)) {
                return writeUnauthorizedResponse(exchange.getResponse());
            }

            // API key is valid; continue with the filter chain
            return chain.filter(exchange);
        };
    }

    /**
     * Writes a 401 Unauthorized JSON response to the client.
     *
     * <p>This method sets the appropriate HTTP status code and content type,
     * then writes the error message as a reactive stream.</p>
     *
     * @param response the server HTTP response to write to
     * @return a {@link Mono} that completes when the response has been written
     */
    private Mono<Void> writeUnauthorizedResponse(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        // Convert error message to bytes and wrap in a DataBuffer for reactive writing
        byte[] bytes = UNAUTHORIZED_RESPONSE.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);

        return response.writeWith(Mono.just(buffer));
    }

    /**
     * Configuration class for the ApiKeyFilter.
     *
     * <p>This class is required by the {@link AbstractGatewayFilterFactory} pattern
     * but is currently empty as no configuration options are needed. Future enhancements
     * could add configurable properties such as custom header names or key validation rules.</p>
     */
    public static class Config {
    }
}
