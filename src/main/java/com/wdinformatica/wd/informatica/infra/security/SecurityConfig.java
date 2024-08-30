package com.wdinformatica.wd.informatica.infra.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustonUserDetailsService userDetailsService;

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/planos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/contato").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/planos/plan-requests").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/planos/reject-plan-request").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/planos/approve-plan-request").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/planos/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user/profile").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/planorequests").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/planos/api/request-plan").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/contato").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/planos/user-plan-requests/").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/planos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/user/update/*").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/user/avatar/*").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/planos/*").hasRole("ADMIN")
                        .requestMatchers(toH2Console()).permitAll()
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}

