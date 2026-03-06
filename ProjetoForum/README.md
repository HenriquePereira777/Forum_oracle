# 📚 ForumHub API

API REST desenvolvida em **Java com Spring Boot** para gerenciamento de
um fórum de discussões.\
Este projeto foi desenvolvido como parte do desafio **Oracle Next
Education + Alura**.

A aplicação permite que usuários autenticados possam:

-   Criar tópicos
-   Listar tópicos
-   Visualizar detalhes de um tópico
-   Atualizar tópicos
-   Excluir tópicos
-   Autenticar usuários utilizando **JWT**

------------------------------------------------------------------------

# 🚀 Tecnologias utilizadas

-   Java 17+
-   Spring Boot
-   Spring Security
-   JWT (JSON Web Token)
-   Spring Data JPA
-   Hibernate
-   MySQL
-   Maven
-   Insomnia / Postman
-   Lombok

------------------------------------------------------------------------

# 📂 Estrutura do projeto

    src
     ├── controller
     │   ├── TopicoController
     │   └── AutenticacaoController
     │    
     │ 
     ├── domain
     │     ├── Topico
     │     ├── Usuario
     │     ├── Curso
     │     └── Resposta
     │
     ├── infra
     │     ├── security
     │     │     ├── DadosTokenJWT
     │     │     ├── SecurityConfigurations
     │     │     ├── SecurityFilter
     │     │     └── TokenService
     │     │
     │     └── exception
     │           └── TratarErros
     │
     └── 

------------------------------------------------------------------------

# 🔐 Autenticação

A autenticação da API é feita utilizando **JWT (JSON Web Token)**.

Fluxo de autenticação:

    Login → valida credenciais → gera token → cliente usa token nas requisições

------------------------------------------------------------------------

# 🔑 Endpoint de Login

### POST

    /login

### Request

``` json
{
  "login": "admin",
  "senha": "123456"
}
```

### Response

``` json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI..."
}
```

------------------------------------------------------------------------

# 🔒 Utilizando o Token

Após o login, envie o token no header:

    Authorization: Bearer SEU_TOKEN_AQUI

Exemplo:

    Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI...

------------------------------------------------------------------------

# 📌 Endpoints da API

## Criar Tópico

### POST

    /topicos

### Request

``` json
{
  "titulo": "Erro no Spring",
  "mensagem": "Não estou conseguindo salvar",
  "autorId": 1,
  "cursoId": 2
}
```

------------------------------------------------------------------------

# 📄 Listar Tópicos

### GET

    /topicos

Com paginação automática.

------------------------------------------------------------------------

# 🔎 Detalhar Tópico

### GET

    /topicos/{id}

------------------------------------------------------------------------

# ✏️ Atualizar Tópico

### PATCH

    /topicos/{id}

### Request

``` json
{
  "titulo": "Novo título",
  "mensagem": "Nova mensagem",
  "status": "ABERTO"
}
```

------------------------------------------------------------------------

# ❌ Excluir Tópico

### DELETE

    /topicos/{id}

------------------------------------------------------------------------

# 🗄️ Estrutura do Banco de Dados

Principais tabelas:

-   usuarios
-   cursos
-   topicos
-   respostas

Relacionamentos:

    Usuario 1 --- N Topicos
    Curso   1 --- N Topicos
    Topico  1 --- N Respostas

------------------------------------------------------------------------

# 🛠️ Configuração do projeto

### 1️⃣ Clonar o repositório

``` bash
git clone https://github.com/HenriquePereira777/Forum_oracle.git
```

------------------------------------------------------------------------

### 2️⃣ Configurar banco de dados

No arquivo:

    application.properties

Exemplo:

``` properties
spring.datasource.url=jdbc:mysql://localhost/forumhub
spring.datasource.username=root
spring.datasource.password=123456

spring.jpa.hibernate.ddl-auto=update
```

------------------------------------------------------------------------

### 3️⃣ Configurar JWT

``` properties
jwt.secret=123456789
jwt.expiration=86400000
```

------------------------------------------------------------------------

### 4️⃣ Rodar o projeto

``` bash
mvn spring-boot:run
```

Servidor:

    http://localhost:8080

------------------------------------------------------------------------

# 🧪 Testes da API

Ferramentas recomendadas:

-   Insomnia
-   Postman

Fluxo de testes:

1.  Criar usuário no banco\
2.  Fazer login\
3.  Copiar token\
4.  Usar token nos endpoints

------------------------------------------------------------------------

# 📖 Regras de negócio implementadas

-   Não permitir **tópicos duplicados** (mesmo título + mensagem)
-   Apenas **usuários autenticados** podem acessar a API
-   Tokens possuem **expiração**
-   Validação de dados com **Bean Validation**

------------------------------------------------------------------------

# 👨‍💻 Autor

Projeto desenvolvido por **Henrique Pereira**

Durante o programa:

**Oracle Next Education (ONE) + Alura**

------------------------------------------------------------------------

# 🎯 Objetivo do projeto

Praticar conceitos de:

-   Arquitetura REST
-   Segurança com Spring Security
-   Autenticação com JWT
-   Boas práticas em Spring Boot
-   Estruturação de APIs profissionais
