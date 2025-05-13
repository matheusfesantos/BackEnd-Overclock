# BackEnd-Overclock

![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge\&logo=spring\&logoColor=white) ![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge\&logo=jsonwebtokens\&logoColor=white) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
 ![Render](https://img.shields.io/badge/Render-46E3B7?style=for-the-badge\&logo=render\&logoColor=white)

Bem-vindo ao repositório do **BackEnd-Overclock**! Este projeto é uma API backend que gerencia dados relacionados à gestão de peças e fornecedores, com foco em escalabilidade, performance e boas práticas de desenvolvimento. Ele é parte integrante de um sistema de **Planejamento de Recursos de Materiais (MRP)**.

**Versão atual:** [v0.5.4](https://github.com/matheusfesantos/BackEnd-Overclock/releases/tag/v0.5.4)

## 📋 Sobre o Projeto

O **BackEnd-Overclock** foi desenvolvido para oferecer suporte ao gerenciamento de fornecedores e peças em um sistema de MRP. Ele fornece endpoints para operações de CRUD e integração com um banco de dados PostgreSQL. Esta versão inclui autenticação com JWT e melhorias no pipeline de CI/CD, com configurações para garantir mais segurança e escalabilidade.

## 🚀 Tecnologias Utilizadas

As principais tecnologias empregadas no projeto incluem:

* **Java 17**: Linguagem de programação.
* **Spring Framework**:

  * **Spring Boot**: Framework principal para construção da API.
  * **Spring Web**: Para desenvolvimento de endpoints REST.
  * **Spring Security**: Implementação de autenticação JWT para segurança.
  * **Spring DevTools**: Para maior agilidade no desenvolvimento.
* **PostgreSQL**: Banco de dados relacional (versão 15).
* **Flyway**: Para controle e versionamento de migrations do banco de dados.
* **JWT (JSON Web Tokens)**: Para autenticação e controle de acesso.
* **Lombok**: Para redução de boilerplate no código.
* **Maven**: Gerenciador de dependências e build.
* **Docker**: Para containerização e portabilidade.
* **GitHub Actions**: Para CI/CD automatizado.

## 📋 Objetivo do Projeto

O BackEnd-Overclock tem como objetivo fornecer a infraestrutura backend para um sistema de gestão de peças e fornecedores. Ele oferece endpoints que permitem:

* Adicionar, editar, listar e remover peças e fornecedores.
* Facilitar o gerenciamento de informações em um sistema de MRP.
* Implementação de autenticação JWT para maior segurança.
* Ser escalável e preparado para futuras implementações, como controle de papéis de usuários, refresh token e integração com frontend.

## 🌐 URL de Produção

A aplicação está hospedada em Render e pode ser acessada através do seguinte link:

[https://backend-projeto-integrador.onrender.com](https://backend-projeto-integrador.onrender.com)

## 🧑‍💻 Direções para o Desenvolvedor Front-End

Para integrar o BackEnd-Overclock com o Front-End, o desenvolvedor pode consumir os seguintes endpoints da API. Abaixo, apresentamos uma lista de recursos importantes que o Front-End pode utilizar:

### 📡 **Endpoints Disponíveis**

1. **Gestão de Peças**:

   * `GET /pecas`: Lista todas as peças cadastradas.
   * `POST /pecas`: Cria uma nova peça no sistema.
   * `PUT /pecas/{id}`: Atualiza as informações de uma peça.
   * `DELETE /pecas/{id}`: Remove uma peça do sistema.

2. **Gestão de Fornecedores**:

   * `GET /fornecedores`: Lista todos os fornecedores cadastrados.
   * `POST /fornecedores`: Adiciona um novo fornecedor ao sistema.
   * `PUT /fornecedores/{id}`: Atualiza os dados de um fornecedor.
   * `DELETE /fornecedores/{id}`: Remove um fornecedor do sistema.

3. **Autenticação (Novo na versão v0.5.4)**:

   * `POST /auth/register`: Cria um novo usuário.
   * `POST /auth/login`: Autentica um usuário e gera um token JWT.

### Comportamento dos Endpoints de DELETE:

Quando um endpoint DELETE é chamado para excluir um recurso (como peça ou fornecedor), a resposta não inclui a "data" e retorna apenas a mensagem de status conforme o resultado da operação.

#### Exemplos de resposta para a exclusão de uma peça:

1. **Peça deletada com sucesso**:

   ```json
   {
     "status": "success",
     "message": "Peça deletada"
   }
   ```

2. **Peça não existe** (tentativa de exclusão de uma peça inexistente):

   ```json
   {
     "status": "error",
     "message": "Peça não existe"
   }
   ```

3. **Erro ao deletar a peça**:

   ```json
   {
     "status": "error",
     "message": "Erro ao deletar a peça"
   }
   ```

### Exemplo de consumo da API no Front-End (Axios)

```typescript
import axios from 'axios';

// URL da API no ambiente de produção (Render)
const apiUrl = 'https://backend-projeto-integrador.onrender.com';

// Requisição para deletar uma peça
axios.delete(`${apiUrl}/pecas/1`)
  .then((response) => {
    console.log('Resposta:', response.data);
  })
  .catch((error) => {
    console.error('Erro ao deletar peça:', error);
  });
```

### Erro de CORS:

Se você enfrentar problemas de CORS (Cross-Origin Resource Sharing), isso ocorre quando a aplicação Front-End tenta acessar a API de um domínio diferente do domínio da API. Para resolver isso, é importante que o Front-End seja configurado para se comunicar com o servidor corretamente. Caso tenha dificuldades com isso, entre em contato com Matheus para orientações adicionais sobre a configuração de CORS no Spring.

## 🛠️ Melhorias Recentes (v0.5.4)

* **Autenticação JWT**: Implementação de sistema de autenticação via JWT, com endpoints para registro e login.
* **Segurança e Variáveis de Ambiente**: Substituição de dados sensíveis no arquivo `application.properties` por variáveis de ambiente, protegendo credenciais.
* **CI/CD com GitHub Actions**: Melhorias na configuração do pipeline para garantir maior segurança e eficiência no processo de build e deploy.
