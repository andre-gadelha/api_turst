package com.teamTregamos.api_TurSt.infra.security;

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

//Usa @Configuration para indicar que a classe é uma classe de configuração do Spring
//Usa @EnableWebSecurity para habilitar a segurança web no aplicativo
@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    //Metodo que configura a segurança da aplicação desabilitando o CSRF (Cross-Site Request Forgery) e habilitando stado sem sessão (stateless)
    //Usa @Bean para indicar que o método retorna um bean gerenciado pelo Spring
    //Método do tipo SecurityFilterChain que configura a segurança da aplicação
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return
                http.csrf(csrf -> csrf.disable())
                        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authorizeHttpRequests(req -> {
                            req.requestMatchers("/auth").permitAll();//permite acesso sem autenticação ao endpoint /auth
                            req.requestMatchers(HttpMethod.GET,"/").permitAll();
                            req.requestMatchers("/v3/api-docs/**", "/swagger-ui.html/**", "/swagger-ui/**").permitAll();//permite acesso sem autenticação aos endpoints do Swagger
                            req.anyRequest().authenticated();
                        })
                        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                        .build();
    }

    //Método que retorna o AuthenticationManager configurado pelo Spring Security
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        //Retorna o AuthenticationManager configurado pelo Spring Security
        return configuration.getAuthenticationManager();
    }

    //Método que retorna um PasswordEncoder que usa o algoritmo BCrypt para codificar senhas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
