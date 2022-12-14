# niffler-consumer

Este projeto tem a missão de consumir a fila de mensagens enviada por uma api para que seja analisada e debitar o saldo de pontos do usuário de acordo com a mensagem.

- [Desenvolvimento](#desenvolvimento)
    - [Requisitos](#requisitos)
    - [Configuração](#configuração)
    - [Testes](#Testes)


## Desenvolvimento

### Requisitos

```

* Maven
* Mysql
* RabbitMq

```

### Configuração

Lista de variáveis de ambiente necessárias para a execução da aplicação

| Variável                     | Descrição                                                      |   Tipo   | Obrigatório | Valor Padrão |
|------------------------------|----------------------------------------------------------------|:--------:|:-----------:|:------------:|
| RABBITMQ_HOST                | Host para acesso ao RabbitMq                                   |  Texto   |     Não     |   niffler    |
| RABBITMQ_PASSWORD            | Senha para acesso ao RabbitMq                                  |  Texto   |     Não     |   niffler    |
| RABBITMQ_PORT                | Porta para acesso ao RabbitMq                                  | Numérico |     Não     |     5672     |
| RABBITMQ_USERNAME            | Usuário para acesso ao RabbitMq                                |  Texto   |     Não     |   niffler    |
| DATABASE_NAME                | Nome do banco de dados                                         |  Texto   |     Não     |   niffler    |
| DATABASE_USERNAME            | Usuário para conexão de dados                                  |  Texto   |     Não     |   niffler    |
| DATABASE_PASSWORD            | Senha do usuário para acesso ao banco                          |  Texto   |     Não     |   niffler    |
| DATABASE_HOST                | Host para acesso ao Banco                                      |  Texto   |     Não     |  localhost   |
| DATABASE_PORT                | Porta para acesso ao Banco                                     | Numérico |     Não     |     3306     |
| CHANNEL_ID                   | ID do canal para alterar os pontos no StreamElements           |  Texto   |     Sim     |      -       |
| SECRET_STREAM_ELEMENTS_TOKEN | Token de autenticação para alterar os pontos no StreamElements |  Texto   |     Sim     |      -       |
| STREAM_ELEMENTS_API_BASE     | URL base do StreamElements                                     |  Texto   |     Sim     |      -       |

> Path do RabbitMq local:
>> localhost:15672/

### Testes

```bash
# unit tests
$ mvn package

```