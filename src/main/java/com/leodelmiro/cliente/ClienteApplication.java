package com.leodelmiro.cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClienteApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClienteApplication.class, args);
    }
    /* TODO ADICIONAR COBERTURA DE TESTES 80% NA PIPELINE PARA DEV E INTEGRAÇÃO RODANDO PÓS DEV, GITHUB ACTIONS E INFRA DYNAMODB*/

}
