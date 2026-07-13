# Auth Notification Platform

Projeto desenvolvido para estudo de arquitetura backend utilizando **Java** e **Spring Boot**.

A aplicação implementa autenticação de usuários com **JWT**, comunicação assíncrona entre microsserviços utilizando **RabbitMQ**, envio de notificações e monitoramento através de **Prometheus** e **Grafana**.

O objetivo deste projeto é aplicar conceitos utilizados em aplicações modernas, como microsserviços, mensageria, autenticação, observabilidade e boas práticas de desenvolvimento.

---

## Tecnologias

- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- PostgreSQL
- Flyway
- RabbitMQ
- Docker & Docker Compose
- Swagger / OpenAPI
- Prometheus
- Grafana
- Maven

---

## Arquitetura

O projeto é composto por **dois microsserviços**.

### Authentication API (notification-services)

Responsável por:

- Cadastro de usuários
- Login e autenticação
- Geração e validação de JWT
- Recebimento das solicitações de envio de notificações
- Persistência dos dados
- Atualização do status das notificações

### Notification Worker (send-worker)

Responsável por:

- Consumir mensagens da fila RabbitMQ
- Processar o envio da notificação
- Retornar o resultado (**SENT** ou **FAIL**) para a API principal

---

## Fluxo da aplicação

```text
Cliente
    │
    ▼
Authentication API
    │
    │ Publica mensagem
    ▼
RabbitMQ
    │
    ▼
Notification Worker
    │
    │ Processa envio
    ▼
RabbitMQ
    │
    │ Retorna status
    ▼
Authentication API
    │
    ▼
PostgreSQL
```

### Fluxo

1. O usuário realiza o cadastro.
2. O usuário faz login.
3. A API gera um JWT.
4. O cliente autenticado solicita o envio de uma notificação.
5. A API publica uma mensagem no RabbitMQ.
6. O Notification Worker consome a mensagem.
7. O Worker processa a notificação.
8. O resultado (SENT ou FAIL) é enviado novamente para a API.
9. A API atualiza o status da notificação no banco de dados.

---

## Funcionalidades

- Cadastro de usuários
- Login com JWT
- Spring Security
- Hash de senhas utilizando BCrypt
- Comunicação assíncrona com RabbitMQ
- Arquitetura baseada em microsserviços
- Banco de dados PostgreSQL
- Versionamento do banco com Flyway
- Documentação com Swagger/OpenAPI
- Tratamento global de exceções
- Observabilidade com Prometheus
- Dashboards no Grafana
- Docker Compose para toda a infraestrutura

---

## Estrutura do projeto

```text
NOTIFICATION-SERVICE
│
├── notification-services
│
├── send-worker
│
├── docker-compose.yml
│
└── README.md
```

---

## Como executar

### 1. Clone o projeto

```bash
git clone https://github.com/seu-usuario/auth-notification-platform.git
```

### 2. Acesse o diretório

```bash
cd NOTIFICATION-SERVICE
```

### 3. Suba a infraestrutura

```bash
docker compose up -d
```

Serão iniciados:

- PostgreSQL
- RabbitMQ
- Prometheus
- Grafana
- app_producer
- app_consumer

### 4. Execute os microsserviços

Na API:

```bash
cd notification-services
mvn spring-boot:run
```

Em outro terminal:

```bash
cd send-worker
mvn spring-boot:run
```

---

## Documentação

Após iniciar a aplicação:

| Ferramenta | URL |
|------------|-----|
| Swagger | http://localhost:8080/swagger-ui/index.html |
| Prometheus | http://localhost:9090 |
| Grafana | http://localhost:3000 |

---

## Observabilidade

A aplicação disponibiliza métricas utilizando **Spring Boot Actuator** e **Micrometer**, permitindo o monitoramento através do Prometheus e a criação de dashboards no Grafana.

Métricas monitoradas incluem:

- Requisições HTTP
- Tempo de resposta
- Consumo de memória
- Uso da CPU
- Threads
- JVM
- Health Check

---

## Próximas melhorias

- [ ] Refresh Token
- [ ] Testes Unitários
- [ ] Testes de Integração
- [ ] Rate Limiting
- [ ] Resilience4j (Circuit Breaker)
- [ ] Logs centralizados
- [ ] CI/CD com GitHub Actions
- [ ] Deploy em Cloud (AWS)

---

## Objetivo

Este projeto foi desenvolvido para fins de estudo e aperfeiçoamento em desenvolvimento Backend Java, aplicando conceitos de:

- Arquitetura de Microsserviços
- Mensageria
- Autenticação e Autorização
- Segurança
- Observabilidade
- Persistência de Dados
- APIs REST
- Boas práticas de desenvolvimento

---

##  Autor

**Adilson Junior**

Backend Developer | Java | Spring Boot
