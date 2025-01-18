package com.leodelmiro.cliente.config.cliente;

import com.leodelmiro.cliente.dataprovider.gateway.cliente.CadastraClienteGatewayImpl;
import com.leodelmiro.cliente.core.usecase.cliente.impl.CadastraClienteUseCaseImpl;
import com.leodelmiro.cliente.core.usecase.cliente.impl.IdentificaClienteUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CadastraClienteConfig {

    @Bean
    public CadastraClienteUseCaseImpl cadastraClienteUseCase(
            CadastraClienteGatewayImpl cadastraClienteGatewayImpl,
            IdentificaClienteUseCaseImpl identificaClienteUseCaseImpl
    ) {
        return new CadastraClienteUseCaseImpl(cadastraClienteGatewayImpl, identificaClienteUseCaseImpl);
    }
}
