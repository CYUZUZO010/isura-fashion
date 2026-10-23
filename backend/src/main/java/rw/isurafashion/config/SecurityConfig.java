package rw.isurafashion.config;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rw.isurafashion.security.JwtService;
import rw.isurafashion.security.TokenBlacklist;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain chain(
            HttpSecurity http,
            JwtService jwt,
            TokenBlacklist blacklist
    ) throws Exception {

        OncePerRequestFilter filter = new OncePerRequestFilter() {

            @Override
            protected void doFilterInternal(
                    HttpServletRequest req,
                    HttpServletResponse res,
                    FilterChain chain
            ) throws ServletException, IOException {

                String h = req.getHeader("Authorization");

                if (h != null && h.startsWith("Bearer ")) {

                    try {
                        String raw = h.substring(7);

                        if (blacklist.isRevoked(raw)) {
                            chain.doFilter(req, res);
                            return;
                        }

                        var c = jwt.parse(raw);

                        var role = c.get("role");

                        var authorities = List.of(
                                new SimpleGrantedAuthority("ROLE_" + role)
                        );

                        var auth = new UsernamePasswordAuthenticationToken(
                                c.getSubject(),
                                null,
                                authorities
                        );

                        req.setAttribute(
                                "userId",
                                c.get("id", Long.class)
                        );

                        SecurityContextHolder
                                .getContext()
                                .setAuthentication(auth);

                    } catch (Exception ignored) {
                        // Invalid JWT - request continues without authentication.
                    }
                }

                chain.doFilter(req, res);
            }
        };

        http
                .csrf(c -> c.disable())
                .cors(c -> c.configurationSource(cors()))
                .sessionManagement(s ->
                        s.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )
                .authorizeHttpRequests(a ->
                        a
                                .requestMatchers(
                                        "/api/auth/**",
                                        "/api/products/**",
                                        "/actuator/health",
                                        "/api/health"
                                )
                                .permitAll()
                                .requestMatchers("/api/admin/**")
                                .hasRole("ADMIN")
                                .anyRequest()
                                .authenticated()
                )
                .addFilterBefore(
                        filter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    CorsConfigurationSource cors() {

        CorsConfiguration c = new CorsConfiguration();

        c.setAllowedOrigins(
                List.of(
                        "http://localhost:5173",
                        "http://localhost:4173"
                )
        );

        c.setAllowedMethods(
                List.of(
                        "GET",
                        "POST",
                        "PUT",
                        "PATCH",
                        "DELETE",
                        "OPTIONS"
                )
        );

        c.setAllowedHeaders(List.of("*"));
        c.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource s =
                new UrlBasedCorsConfigurationSource();

        s.registerCorsConfiguration("/**", c);

        return s;
    }
}