# Enquete

API REST de enquetes com votação ao vivo.

A turma cria enquetes, vota pelo navegador e acompanha o placar atualizando em tempo real.

## Tecnologias

- Java 25
- Spring Boot 4 (Spring Web MVC, Spring Data JPA, Validation)
- H2 Database

## Como rodar

```bash
./mvnw spring-boot:run
```

- A aplicação sobe em `http://localhost:8080`.
- A página de votação fica em `http://localhost:8080/`.
- O console do H2 fica em `http://localhost:8080/h2-console`. Use a JDBC URL que aparece no log, com usuário `sa` e senha em branco.

## Endpoints

| Método | Rota | O que faz |
| --- | --- | --- |
| `POST` | `/api/polls` | Cria uma enquete |
| `GET` | `/api/polls` | Lista as enquetes |
| `POST` | `/api/polls/{id}/votes` | Registra um voto |
| `GET` | `/api/polls/{id}/results` | Mostra o placar |

## Links

- [O caminho de um voto](https://claude.ai/artifact/KRJ2jSG7YAA8V9P8ArDDfS): animação 3D das camadas da aplicação e das validações
- [Spring Initializr](https://start.spring.io): gerador do projeto inicial
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa): documentação do projeto