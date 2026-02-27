package com.example.gateway.filter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class RouteValidator {

        public static final List<String> openApiEndpoints = List.of(
                        "/api/v1/identity/register",
                        "/api/v1/identity/token",
                        "/api/v1/identity/login",
                        "/api/v1/identity/validate",
                        "/actuator",
                        "/management");

        public Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints
                        .stream()
                        .noneMatch(uri -> request.getURI().getPath().contains(uri));

}