# Desafio Design Patterns com Java: Dos Clássicos (GoF) ao Spring Framework

Projeto criado para entrega do Desafio Padrões de Projetos na Prática com Java, com as implementações dos padrões de projeto explorados no Lab "Explorando Padrões de Projetos na Prática com Java". 

Implementando funcionalidades para cadastro de Pessoas utilizando o framework Spring, bem como garantindo a segurança dos acessos através do Spring Security.

Especificamente, este projeto explorou alguns padrões usando o Spring Framework, são eles:

- Security
- Singleton
- Strategy/Repository
- Facade
- JPA



## Documentação da API

#### Lista de URIs e funcionalidades disponíveis através do Swagger

#### http://localhost:8080/swagger-ui

Contudo para desfrutar das funcionalidades, será necessário autenticação, conforme orientações abaixo, que disponibilizará as credenciais de acesso do usuário administrador, permitindo através deste a exclusão ou inclusão de novos usuários/acessos.



## Autenticação

Na requisição POST informe o seguinte login, para obter um token valido que permitirá acessar os demais métodos.

```http
  POST /login
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `username` | `string` | **Obrigatório**. |
| `password` | `string` | **Obrigatório**. |


Para rodar os testes, adicione o JSON abaixo do Body da requisição

```bash 
{
    "username":"admin",
    "password":"admin123"
}
```

