# language: pt
Funcionalidade: Cliente API

  Cenário: Cadastro de um novo cliente
    Dado que eu tenho os dados de um novo cliente
    Quando eu envio uma requisição para cadastrar cliente
    Então o cliente deve ser cadastrado com sucesso status 201

  Cenário: Identifica cliente existente
    Dado que eu busco um cliente existente
    Quando eu envio uma requisição para identificar cliente existente
    Então o cliente deve ser retornado com sucesso status 200

  Cenário: Identifica cliente não existente
    Dado que eu busco um cliente inexistente
    Quando eu envio uma requisição para identificar cliente inexistente
    Então deve ser retornado 404