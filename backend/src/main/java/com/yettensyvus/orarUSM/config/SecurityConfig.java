package com.yettensyvus.orarUSM.config;

import com.yettensyvus.orarUSM.security.JwtAuthenticationFilter;
import com.yettensyvus.orarUSM.security.JwtUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JwtUserDetailsService jwtUserDetailsService;

    public SecurityConfig(JwtUserDetailsService jwtUserDetailsService) {
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(jwtUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth -> auth
                // Public endpoints - no authentication required
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/public/**").permitAll()
                
                // Admin endpoints - require ADMIN role
                .requestMatchers("/admin/**").hasRole("ADMIN")
                
                // Professor endpoints - require PROFESSOR role
                .requestMatchers("/professor/**").hasRole("PROFESSOR")
                
                // Student endpoints - require STUDENT role
                .requestMatchers("/student/**").hasRole("STUDENT")
                
                // User management endpoints - require ADMIN role
                .requestMatchers("/users/**").hasRole("ADMIN")
                
                // CRUD endpoints - require ADMIN role
                .requestMatchers(HttpMethod.POST, "/faculties/**", "/groups/**", "/teachers/**", 
                               "/students/**", "/subjects/**", "/schedules/**", "/lessons/**",
                               "/buildings/**", "/classrooms/**", "/timeslots/**", "/subgroups/**")
                    .hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/faculties/**", "/groups/**", "/teachers/**", 
                               "/students/**", "/subjects/**", "/schedules/**", "/lessons/**",
                               "/buildings/**", "/classrooms/**", "/timeslots/**", "/subgroups/**")
                    .hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/faculties/**", "/groups/**", "/teachers/**", 
                               "/students/**", "/subjects/**", "/schedules/**", "/lessons/**",
                               "/buildings/**", "/classrooms/**", "/timeslots/**", "/subgroups/**")
                    .hasRole("ADMIN")
                
                // All other GET requests require authentication
                .requestMatchers(HttpMethod.GET, "/**").authenticated()
                
                // Any other request requires authentication
                .anyRequest().authenticated()
            )
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
