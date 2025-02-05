package com.leodelmiro.cliente.core.domain;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import java.util.Objects;

public class CPF {
    private String cpf;

    public CPF() {
    }

    public CPF(String cpf) {
        if (!isValid(cpf)) throw new IllegalArgumentException("Esse CPF é inválido");
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Boolean isValid(String cpf) {
        var validator = new CPFValidator();
        validator.initialize(null);
        return cpf.matches("^[0-9]{11}$") && validator.isValid(cpf, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CPF cpf1 = (CPF) o;
        return Objects.equals(cpf, cpf1.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cpf);
    }
}
