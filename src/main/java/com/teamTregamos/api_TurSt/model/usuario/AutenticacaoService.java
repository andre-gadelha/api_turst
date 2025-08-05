package com.teamTregamos.api_TurSt.model.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//Classe responsável pelo serciço para autenticar o usuário
//Utiliza a anotação @Service para ser reconhecida como um componente do Spring
//Implementa a interface UserDetailsService para que o Spring Security saiba como carregar os detalhes do usuário durante o processo de autenticação
@Service
public class AutenticacaoService implements UserDetailsService {

    //Repository que será utilizado para buscar os usuários no banco de dados
    //@Autowired é usado para injetar a dependência do UsuarioRepository, que é responsável por acessar os dados do usuário no banco de dados
    @Autowired
    private UsuarioRepository repository;

    //Usa @Override para indicar que este método está sobrescrevendo um método da interface UserDetailsService
    //Usa throws UsernameNotFoundException para lançar uma exceção caso o usuário não seja encontrado no banco de dados
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Método criado no Repository para busca o usuário pelo nome de usuário (username)
        return repository.findByUsuario(username);
    }
}
