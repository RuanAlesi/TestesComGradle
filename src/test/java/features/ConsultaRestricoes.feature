#language: pt
  Funcionalidade: Consultas de restrições

    Contexto:
      Dado o end-point "http://localhost:8080/api/v1/restricoes/"

    Esquema do Cenario: Consulta de restrições
      Quando eu consulto restricao pelo <CPF>
      Entao deve ser retornado <STATUSCODE>
      Exemplos:
        | CPF           |   STATUSCODE  |
        | "97093236014" |   200         |
        | "99999999999" |   204         |