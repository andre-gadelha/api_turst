package com.teamTregamos.api_TurSt.model;

import com.teamTregamos.api_TurSt.dto.DadosCadastroUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name="tb_usuario")
@Entity(name="Usuario")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cpfCnpj;
    private String email;
    private String senha;

    //Controle de permissões
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                () -> "ROLE_USER" // Define a role padrão para o usuário
        );
    }

    //Construtor padrão
    public Usuario() {
        // Construtor JPA padrão exigido pelo Hibernate
    }

    //Construtor para criar um usuário a partir do DTO dos dados de cadastro
    public Usuario(DadosCadastroUsuario dadosUsuario, String senhaCodificada) {
        this.nome = dadosUsuario.nome();
        this.cpfCnpj = dadosUsuario.cpfCnpj();
        this.email = dadosUsuario.email();
        this.senha = senhaCodificada;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
