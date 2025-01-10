package br.com.guarda_sementes_api.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfigurations {

    private final SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/usuarios/registrar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuarios/autenticar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/sementes-disponiveis-troca").permitAll()
                        .requestMatchers(HttpMethod.POST, "/estados").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/estados/{estNrId}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/estados/{estNrId}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/cidades").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/cidades/{cidNrId}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/cidades/{cidNrId}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/instrucoes").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/instrucoes/{insNrId}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/instrucoes/{insNrId}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/categorias-armazem").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/categorias-armazem/{ctaNrId}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/categorias-armazem/{ctaNrId}").hasAuthority("ADMIN")
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/hello").hasAuthority("GUARDIAO")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
