package com.teamTregamos.api_TurSt.service;

import com.teamTregamos.api_TurSt.dto.DadosCadastroUsuario;
import com.teamTregamos.api_TurSt.model.Usuario;
import com.teamTregamos.api_TurSt.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Injeção do PasswordEncoder para codificar a senha

    @Transactional
    public Usuario cadastrarUsuario(DadosCadastroUsuario dadosUsuario){

        // Codifica a senha antes de salvar o usuário
        String senhaCodificada = passwordEncoder.encode(dadosUsuario.senha());

        /*
        Lógica para cadastrar um novo usuário
        * */

        return usuarioRepository.save(new Usuario(dadosUsuario, senhaCodificada));
    }

    public List<Usuario> listarUsuarios(){

        return usuarioRepository.findAll();
    }

}
