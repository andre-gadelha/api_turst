package com.teamTregamos.api_TurSt.model.infra.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.teamTregamos.api_TurSt.model.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

@Service
public class TokenService {
    // Classe responsável por gerar e validar tokens JWT para autenticação de usuários
    @Value("${api.security.token.secret}")
    public String secret;

    // Método para gerar um token JWT para um usuário
    public String gerarToken(Usuario usuario) {

        try {
            var algoritimo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API TurSt")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(new java.util.Date(System.currentTimeMillis() + 3600000)) // Token válido por 1 hora
                    .sign(algoritimo);
        } catch (JWTCreationException exception){
            // Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }

    }
    // Método para validar e obter o assunto (subject) do token JWT
    public String getSubject(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer("API TurSt")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            // Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException("Erro na validação do Token JWT", exception);
        }
    }
}
