#language: pt
Funcionalidade: Manutenão de simulações no end-point /api/v1/simulacoes

  Cenario: Criando uma nova simulação e deletando em seguida (caminho feliz)
    Dado as seguintes informacoes da simulacao
    | cpf      | 97093236014         |
    | nome     | Nome do cliente     |
    | email    | email@valido.com.br |
    | valor    | 1000                |
    | parcelas | 10                  |
    | seguro   | true                |
    Quando quando eu envio a requisicao para o endereco "http://localhost:8080/api/v1/simulacoes"
    Entao deve ser retornado o status code "201"
    E a simulacao deve ser deletada

  Cenario: Tentando criar uma simulação com dados incorretos
    Dado as seguintes informacoes da simulacao
      | cpf      | 66414919004         |
      | email    | email@valido.com.br |
      | valor    | 1000                |
      | parcelas | 10                  |
      | seguro   | true                |
    Quando quando eu envio a requisicao para o endereco "http://localhost:8080/api/v1/simulacoes"
    Entao deve ser retornado o status code "400"

  Cenario: Tentando criar uma simulação já existente para o CPF
    Dado as seguintes informacoes da simulacao
      | cpf      | 66414919004         |
      | nome     | Nome do cliente     |
      | email    | email@valido.com.br |
      | valor    | 1000                |
      | parcelas | 10                  |
      | seguro   | true                |
    Quando quando eu envio a requisicao para o endereco "http://localhost:8080/api/v1/simulacoes"
    Entao deve ser retornado o status code "409"

  Cenario: Alterando uma simulação já existente
    Dado as seguintes informacoes da simulacao
      | nome     | Nome do cliente alterado     |
      | cpf      | 66414919004                  |
      | email    | email@valido.com.br          |
      | valor    | 1000                         |
      | parcelas | 10                           |
      | seguro   | true                         |
    Quando quando eu envio a alteracao para o endereco "http://localhost:8080/api/v1/simulacoes/66414919004"
    Entao deve ser retornado o status code "200"

  Cenario: Alterando uma simulação não existente
    Dado as seguintes informacoes da simulacao
      | nome     | Nome do cliente alterado     |
      | cpf      | 66414919004                  |
      | email    | email@valido.com.br          |
      | valor    | 1000                         |
      | parcelas | 10                           |
      | seguro   | true                         |
    Quando quando eu envio a alteracao para o endereco "http://localhost:8080/api/v1/simulacoes/99999999998"
    Entao deve ser retornado o status code "404"

  Cenario: Consulta de todas as simulações
    Dado o end-point "http://localhost:8080/api/v1/simulacoes"
    Quando eu consulto as simulacoes
    Entao deve ser retornado o status code "200"

  Cenario: Consulta de uma a simulação existente
    Dado o end-point "http://localhost:8080/api/v1/simulacoes/17822386034"
    Quando eu consulto as simulacoes
    Entao deve ser retornado o status code "200"

  Cenario: Consulta de uma a simulação não existente
    Dado o end-point "http://localhost:8080/api/v1/simulacoes/99999999998"
    Quando eu consulto as simulacoes
    Entao deve ser retornado o status code "404"