package com.leodelmiro.cliente.core.domain;

import java.time.LocalDateTime;

public class Cliente {
    private Long id;
    private CPF cpf;
    private String nome;
    private String email;
    private LocalDateTime criadoEm;

    public Cliente() {
    }

    public Cliente(Long id, CPF cpf, String nome, String email, LocalDateTime criadoEm) {
        if (nome.isBlank()) throw new IllegalArgumentException("Nome não pode ser vazio");
        if (email.isBlank()) throw new IllegalArgumentException("Email não pode ser vazio");

        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.criadoEm = (criadoEm == null) ? LocalDateTime.now() : criadoEm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CPF getCpf() {
        return cpf;
    }

    public void setCpf(CPF cpf) {
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

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = (criadoEm == null) ? LocalDateTime.now() : criadoEm;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }
}
