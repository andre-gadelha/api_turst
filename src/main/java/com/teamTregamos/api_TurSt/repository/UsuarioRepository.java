package com.teamTregamos.api_TurSt.repository;

import com.teamTregamos.api_TurSt.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    UserDetails findByEmail(String email);

    //UserDetails findByUsuario(String username);
}
