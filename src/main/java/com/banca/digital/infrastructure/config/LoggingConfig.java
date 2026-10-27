package com.banca.digital.infrastructure.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

/**
 * Configuración de logging para el sistema de gestión de préstamos.
 * Implementa trazas estructuradas usando MDC para correlación de requests.
 */
@Slf4j
@Configuration
public class LoggingConfig {

    private static final String REQUEST_ID_HEADER = "X-Request-ID";
    private static final String REQUEST_ID_MDC_KEY = "requestId";
    private static final String CLIENT_ID_MDC_KEY = "clientId";
    private static final String ENDPOINT_MDC_KEY = "endpoint";
    private static final String METHOD_MDC_KEY = "method";

    @Bean
    public OncePerRequestFilter loggingFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain filterChain) throws ServletException, IOException {
                String requestId = extractOrGenerateRequestId(request);
                String clientId = request.getHeader("X-Client-ID");
                String endpoint = request.getRequestURI();
                String method = request.getMethod();

                try {
                    MDC.put(REQUEST_ID_MDC_KEY, requestId);
                    MDC.put(ENDPOINT_MDC_KEY, endpoint);
                    MDC.put(METHOD_MDC_KEY, method);
                    if (clientId != null && !clientId.isBlank()) {
                        MDC.put(CLIENT_ID_MDC_KEY, clientId);
                    }

                    log.info("Iniciando procesamiento de request: method={}, endpoint={}, requestId={}",
                            method, endpoint, requestId);

                    long startTime = System.currentTimeMillis();
                    filterChain.doFilter(request, response);
                    long duration = System.currentTimeMillis() - startTime;

                    log.info("Request completado: method={}, endpoint={}, status={}, durationMs={}, requestId={}",
                            method, endpoint, response.getStatus(), duration, requestId);

                    if (duration > 200) {
                        log.warn("Latencia elevada detectada: {}ms para {} {}", duration, method, endpoint);
                    }

                } catch (Exception e) {
                    log.error("Error procesando request: method={}, endpoint={}, requestId={}, error={}",
                            method, endpoint, requestId, e.getMessage(), e);
                    throw e;
                } finally {
                    MDC.clear();
                }
            }

            private String extractOrGenerateRequestId(HttpServletRequest request) {
                String requestId = request.getHeader(REQUEST_ID_HEADER);
                return (requestId != null && !requestId.isBlank()) ? requestId : UUID.randomUUID().toString();
            }
        };
    }
}