package com.example.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
<<<<<<< HEAD
import org.springframework.security.config.http.SessionCreationPolicy;
=======
>>>>>>> ca0c1ffc095da7becef6089f6a226bb29fdf15d5
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll());


    //     http.authorizeHttpRequests(authorize -> authorize
    //             .requestMatchers("/" ,"/admin/css/**", "/admin/js/**","/admin/fonts/**").permitAll()
    //             .requestMatchers( "/admin/page/**").hasRole("ADMIN")
    //             // .anyRequest().permitAll()
    //             // .requestMatchers("/admin/page/user").permitAll()
    //             .anyRequest().authenticated()
    //             );

    //     http.formLogin(login -> login.loginPage("/admin/login")
    //     .defaultSuccessUrl("/admin/page/index", true).permitAll());

    //    http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));

    //     http.logout(logout -> logout
    //             .logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
    //             .logoutSuccessUrl("/"));

        // http.csrf(csrf -> csrf.disable());
        return http.build();
    }
<<<<<<< HEAD
    
     @Bean 
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
=======

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

>>>>>>> ca0c1ffc095da7becef6089f6a226bb29fdf15d5
}
