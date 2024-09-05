package com.pms.user.service.config;


import org.springframework.context.annotation.Configuration;

import java.beans.BeanProperty;
import java.beans.Customizer;
import java.text.CollationElementIterator;
import java.util.Collection;
import java.util.Collections;

@Configuration
public class ApplicationConfiguration {

    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.sessionManagement(
                management->management.sessionCreationPolicy(
                        sessionCreationPolicy.STATELESS
                )
        ).authorizeRequests(
                Authorize-> Authorize.requestMatchers("/api/**").authenticated().anyRequest().permitAll()
        ).addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
                .csrf(csrf->csrf.disable())
                .cors(cors->cors.configurationSource(CorsConfigurationSource)))
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
return http.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration cfg = new CorsConfiguration();
                cfg.setAllowedOrigins(Collections.singletonList("*"));
                cfg.setAllowedMethods(Collections.singletonList("*"));
                cfg.setAllowCredentials(true);
                cfg.setAllowedHeaders(Collections.singletonList("*"));
                cfg.setExposedHeaders(Arrays.asList("Authorization"));
                cfg.setMaxAge(3600L);
                return cfg;
            }
        };
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}


