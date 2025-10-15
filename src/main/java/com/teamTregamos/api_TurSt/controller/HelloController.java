package com.teamTregamos.api_TurSt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/")
public class HelloController {

    @GetMapping
    public String hello(UriComponentsBuilder uriBuilder){
        //Recuperando a URL
        var uri = uriBuilder.path("/swagger-ui/index.html").buildAndExpand().toUri();

        return "Olá!\n Este é o diretório raiz da API - TusSt. " +
                "Leia a documentação: " +
                "<a href='" + uri + "'>Documentação</a>";
    }

}
