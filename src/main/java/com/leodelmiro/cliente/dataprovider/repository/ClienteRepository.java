package com.leodelmiro.cliente.dataprovider.repository;

import com.leodelmiro.cliente.dataprovider.repository.entity.CPFEntity;
import com.leodelmiro.cliente.dataprovider.repository.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    Optional<ClienteEntity> findClienteByCpf(CPFEntity cpf);
}
