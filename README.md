# WeatherForecast API

Uma API RESTful robusta desenvolvida em Java 21 e Spring Boot, focada em fornecer dados meteorológicos em tempo real via Open-Meteo, com suporte a autenticação JWT, histórico de buscas e sistema de favoritos para usuários.

## 🛡️ Tecnologias
![Java](https://img.shields.io/badge/Java-21-blue?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green?logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-latest-blue?logo=postgresql)
![JWT](https://img.shields.io/badge/Authentication-JWT-red?logo=jsonwebtokens)
![Maven](https://img.shields.io/badge/Build-Maven-orange?logo=apachemaven)

## 🚀 Funcionalidades
- **Consulta Meteorológica:** Obtenção de previsões simplificadas ou dados brutos via Open-Meteo com geocoding automático.
- **Segurança:** Autenticação e autorização robustas utilizando Spring Security e JWT.
- **Gestão de Usuário:** Registro e autenticação de usuários.
- **Favoritos:** Funcionalidade para listar, adicionar e remover cidades favoritas por usuário.
- **Histórico:** Acompanhamento do histórico de pesquisas realizadas.

## 🏗️ Arquitetura
O projeto segue o padrão de camadas para garantir separação de responsabilidades e manutenibilidade:
- **Controller:** Gerenciamento dos endpoints HTTP e validação inicial de requisições.
- **Service:** Orquestração das regras de negócio.
- **Repository:** Interação com o banco de dados via Spring Data JPA.
- **Domain:** Modelagem dos dados (Entities e DTOs).
- **Client:** Integração com APIs externas (Open-Meteo).

## 🌐 Endpoints Principais

### Autenticação
- `POST /auth/register`: Registra um novo usuário.
- `POST /auth/login`: Autentica e retorna um token JWT.

### Clima e Consultas
- `GET /api/weather?city={cidade}`: Previsão simplificada.
- `GET /api/weather/raw?city={cidade}`: Dados brutos da Open-Meteo.

### Usuário (Favoritos e Histórico)
- `GET /user/history`: Lista o histórico de pesquisas.
- `GET /user/favorite`: Lista os favoritos.
- `POST /user/favorite`: Adiciona uma cidade aos favoritos.
- `DELETE /user/favorite`: Remove uma cidade dos favoritos.

## 🛠️ Como rodar o projeto
1. **Pré-requisitos:**
   - Java 21
   - Maven
   - PostgreSQL instalado e rodando.
2. **Configuração:**
   - Clone o projeto.
   - Crie uma cópia de `src/main/resources/application.properties.example` chamada `application.properties` e preencha as credenciais do seu banco de dados.
3. **Migração:**
   - Execute `./mvnw flyway:migrate` para aplicar as migrations ao banco de dados.
4. **Execução:**
   - Execute `./mvnw spring-boot:run`.
   - A API estará disponível em `http://localhost:8080`.

## 🧪 Estratégia de Testes
O projeto utiliza JUnit 5 e Mockito para garantir a qualidade das regras de negócio.
- **Testes Unitários:** Foco nos Services, isolando dependências com Mocks.
- **Desafio Técnico:** Implementação de mocks estáticos com `Mockito.mockStatic` para testar componentes dependentes do `SecurityContextHolder`.
- **Comando:** `./mvnw test`

## 💡 Desafios Técnicos
- **Configuração de CORS:** Ajuste fino do `SecurityFilterChain` para permitir requisições do frontend mantendo a segurança JWT.
- **Flyway:** Resolução de conflitos de versionamento de migrações durante o desenvolvimento inicial.
- **Mock de Contexto de Segurança:** Superação da dificuldade de testar métodos que dependem de usuários autenticados via `SecurityContextHolder` utilizando Mockito para simular o contexto de segurança em ambiente de teste.

## 🗺️ Roadmap
- Implementar cache de requisições externas para otimizar performance.
- Conteinerização da aplicação com Docker e Docker Compose.

## 👤 Autor
Aureliano — Estudante de Engenharia de Software na Uniasselvi.
