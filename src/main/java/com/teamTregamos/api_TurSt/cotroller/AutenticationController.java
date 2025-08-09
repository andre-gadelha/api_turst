package com.teamTregamos.api_TurSt.cotroller;

import com.teamTregamos.api_TurSt.model.atividade.DadosEdicaoAtividade;
import com.teamTregamos.api_TurSt.model.infra.security.DadosToken;
import com.teamTregamos.api_TurSt.model.infra.security.TokenService;
import com.teamTregamos.api_TurSt.model.usuario.DadosAutenticacao;
import com.teamTregamos.api_TurSt.model.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutenticationController {

    //AuthenticationManager é uma interface do Spring Security que fornece métodos para autenticar usuários
    //Usa Automaticamente as configurações de segurança definidas na classe SecurityConfigurations
    @Autowired
    private AuthenticationManager authenticatorManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity auth(@RequestBody @Valid DadosAutenticacao autenticacao) {
        try{
            //Gera um token de autenticação usando o AuthenticationManager com os dados fornecidos pelo usuário
            var token = new UsernamePasswordAuthenticationToken(autenticacao.usuario(), autenticacao.senha());
            //O método authenticate do AuthenticationManager tenta autenticar o usuário com base no token fornecido
            var authentication = authenticatorManager.authenticate(token);

            var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

            // Exemplo de retorno de sucesso passando o usuário autenticado:
            return ResponseEntity.ok(new DadosToken(tokenJWT));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
