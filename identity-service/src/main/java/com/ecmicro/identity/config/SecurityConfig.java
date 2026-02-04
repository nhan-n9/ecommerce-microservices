package com.example.ecommerce.security;

import com.example.ecommerce.user.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity(debug = true)
//@AllArgsConstructor
//@NoArgsConstructor
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;
    private final JWTFilter jwtFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(request -> request
                .requestMatchers("/users/register", "/users/login", "/actuator/**", "/management/**").permitAll()
                .requestMatchers(
                        "/users/details/**",
                        "/products/new",
                        "/products/update/**",
                        "/products/delete/**"
                ).hasAnyRole(RoleEnum.MEMBER.name(), RoleEnum.ADMIN.name())
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults())
            .csrf(csrf -> csrf.disable())
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);     // Add JWT filter (1st param) before default (2nd param)

        // authorization is at the end of the filter chain
        return http.build();
    }

    /*
    * DaoAuthenticationProvider uses UserDetailsService -> look up the UserDetails
    * DaoAuthenticationProvider uses PasswordEncoder -> validate the password on the UserDetails (which was just looked up)
    * */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    /* Create Bean for AuthenticationManager -> to handle JWT authentication
    * AuthenticationManager is an interface -> cannot create object directly -> get it from AuthenticationConfiguration
    * */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
