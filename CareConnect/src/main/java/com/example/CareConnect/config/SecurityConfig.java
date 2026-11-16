package com.example.CareConnect.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import com.example.CareConnect.repository.AuthTokenRepository;
import com.example.CareConnect.security.TokenAuthenticationFilter;

import jakarta.servlet.DispatcherType;

@Configuration
public class SecurityConfig {

    private final AuthTokenRepository authTokenRepository;

    public SecurityConfig(AuthTokenRepository authTokenRepository) {
        this.authTokenRepository = authTokenRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        TokenAuthenticationFilter tokenAuthenticationFilter =
                new TokenAuthenticationFilter(authTokenRepository);

        http
         .cors(cors -> {})
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()

                .requestMatchers(
                    "/login",
                    "/css/**",
                    "/js/**",
                    "/images/**"
                ).permitAll()

                .requestMatchers(
                    "/api/register",
                    "/api/login",
                    "/api/doctors",
                    "/api/doctors/**"
                ).permitAll()

                .requestMatchers("/api/**").authenticated()

                .anyRequest().hasRole("ADMIN")
            )

            .exceptionHandling(exception -> exception
                .defaultAuthenticationEntryPointFor(
                    new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
                    request -> request.getServletPath().startsWith("/api/")
                )
            )

            // Administrator Login
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )

            // Logout
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .permitAll()
            )

            .addFilterBefore(
                tokenAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}
