package com.leodelmiro.cliente.dataprovider.repository.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.leodelmiro.cliente.config.LocalDateTimeConverter;

import java.time.LocalDateTime;

@DynamoDBTable(tableName ="tb_cliente")
public class ClienteEntity {
    @DynamoDBHashKey
    private String cpf;
    @DynamoDBAttribute
    private String nome;
    @DynamoDBAttribute
    private String email;
    @DynamoDBAttribute
    @DynamoDBTypeConverted( converter = LocalDateTimeConverter.class )
    private LocalDateTime criadoEm;

    public ClienteEntity() {
    }

    public ClienteEntity(Long id, String cpf, String nome, String email, LocalDateTime criadoEm) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.criadoEm = criadoEm;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }
}
