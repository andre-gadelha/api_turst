package com.teamTregamos.api_TurSt.security;

import com.teamTregamos.api_TurSt.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//Classe de filtro de segurança que intercepta cada requisição HTTP
@Component
public class SecurityFilter extends OncePerRequestFilter {

    //Classe responsável por validar o token JWT presente no cabeçalho Authorization das requisições HTTP
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //método que é chamado para filtrar as requisições HTTP
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var tokenJWT = recuperaToken(request);

        if(tokenJWT != null){

            //varifica se o token JWT é válido e obtém o assunto (subject) do token
            var validaToken = tokenService.getSubject(tokenJWT); // Valida o token JWT
            //Busca o usuário associado ao token JWT no repositório de usuários
            var usuarioAutenticado = usuarioRepository.findByEmail(validaToken);

            //Definir a autenticação no SecurityContext
            if(usuarioAutenticado != null) {
                // Criar o objeto de autenticação
                var authentication = new UsernamePasswordAuthenticationToken(
                        usuarioAutenticado.getUsername(),  // principal (username)
                        null,         // credentials (não precisamos da senha aqui)
                        usuarioAutenticado.getAuthorities()// authorities (permissões do usuário)
                );

                // Definir no SecurityContext para o Spring Security reconhecer
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("Usuário autenticado com sucesso: " + usuarioAutenticado.getUsername());
            }
        }

        //Para seguir o fluxo de autenticação, você pode adicionar a lógica de validação do token aqui.
        filterChain.doFilter(request, response);
    }

    public String recuperaToken(HttpServletRequest request){

        var autorizationHeader = request.getHeader("Authorization");

        if (autorizationHeader != null) {
            autorizationHeader = request.getHeader("Authorization").replace("Bearer ", "").trim();
            //Recupera o token JWT do cabeçalho Authorization
            return autorizationHeader;
        }else{
            autorizationHeader = null;
            return autorizationHeader;
        }
    }
}
