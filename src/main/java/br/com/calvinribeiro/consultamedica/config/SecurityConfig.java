package br.com.calvinribeiro.consultamedica.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] PUBLIC_MATCHERS = {
            "/h2-console/**",
            "/swagger-ui/**",
            "/v3/aí-docs/**"
    };

    private static final String[] PUBLIC_MATCHERS_GET = {
                "/usuarios/**",
                "/consultas/**"
    };
    private static final String[] PUBLIC_MATCHERS_POST = {
            "/usuarios/**",
            "/consultas/**"
    };

    private static final String[] PUBLIC_MATCHERS_PUT = {
            "/usuarios/**",
            "/consultas/**"
    };

    private static final String[] PUBLIC_MATCHERS_DELETE = {
            "/usuarios/**",
            "/consultas/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/usuarios").hasAnyRole("ADMIN", "SECRETARIO")
                        .requestMatchers(HttpMethod.GET, "/usuarios").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/usuarios").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/usuarios").hasAnyRole("ADMIN", "SECRETARIO")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser("JAIR").password(passwordEncoder.encode("12345")).roles("ADMIN")
                .and()
                .withUser("CALVIN").password(passwordEncoder.encode("98765")).roles("PACIENTE")
                .and()
                .withUser("Fernanda").password(passwordEncoder.encode("121212")).roles("SECRETARIA");

    }
}