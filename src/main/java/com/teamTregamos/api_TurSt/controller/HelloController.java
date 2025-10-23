package com.teamTregamos.api_TurSt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/")
public class HelloController {

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping
    public String hello(UriComponentsBuilder uriBuilder){

        //Recuperando a URL
        var uri = uriBuilder.path("/swagger-ui/index.html").buildAndExpand().toUri();

        return "<h3>Olá!<br><br>" +
                "Este é o diretório raiz da API - " + appName + " Versão: 0.0.4  .<br><br>" +
                //"Este é o diretório raiz da API - .<br><br>" +
                "Acesse os Endpoints: " +
                "<a href='" + uri + "'>Documentação</a></h3>";
    }

}
