package com.leodelmiro.cliente.dataprovider.repository;

import com.leodelmiro.cliente.dataprovider.repository.entity.ClienteEntity;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface ClienteRepository extends CrudRepository<ClienteEntity, String> {
    Optional<ClienteEntity> findClienteByCpf(String cpf);
}
