package com.example.ecommerce.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final CustomUserDetailsService userDetailsService;

    // receives a Bearer token from client -> validate the token
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null, username = null;

        // value of "Authorization" from request's header
        String authHeader = request.getHeader("Authorization");

        // validate syntax -> get the token & extract the username
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtService.extractUsernameFromToken(token);
        }

        // after got values -> check existence & if already authenticated or not (if null -> process auth)
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // steps: validate token -> create Authentication obj
            CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);

            if (jwtService.validateToken(token, userDetails)) {
                // if valid -> work with the 2nd filter
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                // authToken now knows about the user, but no idea about the request obj

                // pass in the request obj
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // with a complete authToken -> set it into the context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // after done, let it continue with the next filter
        filterChain.doFilter(request, response);
    }
}
