# BackEnd-Overclock
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white) ![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white) ![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white) ![AWS](https://img.shields.io/badge/AWS-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white) ![Render](https://img.shields.io/badge/Render-46E3B7?style=for-the-badge&logo=render&logoColor=white)

Bem-vindo ao repositório do **BackEnd-Overclock**! Este projeto é uma API backend que gerencia dados relacionados à gestão de peças e fornecedores, com foco em escalabilidade, performance e boas práticas de desenvolvimento. Ele é parte integrante de um sistema de **Planejamento de Recursos de Materiais (MRP)**.

**Versão atual:** [v0.4.2](https://github.com/matheusfesantos/BackEnd-Overclock/releases/tag/v0.4.2)

---

## 📋 Sobre o Projeto

O **BackEnd-Overclock** foi desenvolvido para oferecer suporte ao gerenciamento de fornecedores e peças em um sistema de MRP. Ele fornece endpoints para operações de CRUD e integração com um banco de dados PostgreSQL. Esta versão não inclui autenticação, facilitando o uso em ambientes de desenvolvimento.

---

## 🚀 Tecnologias Utilizadas

As principais tecnologias empregadas no projeto incluem:

- **Java 17**: Linguagem de programação.
- **Spring Framework**:
  - **Spring Boot**: Framework principal para construção da API.
  - **Spring Web**: Para desenvolvimento de endpoints REST.
  - **Spring DevTools**: Para maior agilidade no desenvolvimento.
- **PostgreSQL**: Banco de dados relacional (versão 15).
- **Flyway**: Para controle e versionamento de migrations do banco de dados.
- **Lombok**: Para redução de boilerplate no código.
- **Maven**: Gerenciador de dependências e build.
- **Docker**: Para containerização e portabilidade.
- **GitHub Actions**: Para CI/CD automatizado.

---

## 🆕 Novidades na Versão [v0.4.2](https://github.com/matheusfesantos/BackEnd-Overclock/releases/tag/v0.4.2)

### 🔧 **Melhorias no Sistema**
- Migração para **PostgreSQL 15**.
- Adição de **Flyway** para controle de migrations.
- Novas funcionalidades para gestão de **fornecedores** e **peças**.
- Pipeline CI/CD atualizado para incluir automação de testes e deploy.

---

## 📂 Estrutura do Projeto

```
BackEnd-Overclock/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com.example.overclock/  # Pacotes principais
│   │   │   │   ├── controllers/        # Controladores da aplicação
│   │   │   │   ├── services/           # Lógica de negócios
│   │   │   │   ├── repositories/       # Repositórios de acesso ao banco
│   │   │   │   ├── models/             # Modelos de dados
│   │   ├── resources/
│   │       ├── application.yml         # Configurações do Spring
├── docker-compose.yml                  # Configuração de serviços Docker
├── pom.xml                             # Arquivo de configuração do Maven
└── ...
```

---

## 🔧 Instalação e Configuração

### Pré-requisitos
- **Java 17** instalado.
- **PostgreSQL** (versão 15 ou superior) configurado.
- **Docker** e **Docker Compose** instalados.
- **Maven** instalado.

### Passos
1. Clone o repositório:
   ```bash
   git clone https://github.com/matheusfesantos/BackEnd-Overclock.git
   ```
2. Navegue até o diretório do projeto:
   ```bash
   cd BackEnd-Overclock
   ```
3. Configure as variáveis de ambiente no arquivo `application.yml`:
   - Atualize as credenciais do banco de dados PostgreSQL.
4. Execute os serviços com Docker Compose:
   ```bash
   docker-compose up
   ```
5. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```
6. Acesse a API na URL:
   ```
   http://localhost:8080
   ```

---

## 🧑‍💻 Como o Dev Front-End Pode Usá-lo

O **BackEnd-Overclock** foi projetado para ser a base de dados e lógica de negócios do sistema MRP. Abaixo estão as informações para que o desenvolvedor Front-End, utilizando **TypeScript**, possa consumir a API de forma eficiente:

### 📡 **Endpoints Disponíveis**
1. **Gestão de Peças**:
   - `GET /pecas`: Lista todas as peças cadastradas.
   - `POST /pecas`: Cadastra uma nova peça.
   - `PUT /pecas/{id}`: Atualiza informações de uma peça.
   - `DELETE /pecas/{id}`: Remove uma peça do sistema.

2. **Gestão de Fornecedores**:
   - `GET /fornecedores`: Lista todos os fornecedores.
   - `POST /fornecedores`: Adiciona um novo fornecedor.
   - `PUT /fornecedores/{id}`: Atualiza informações de um fornecedor.
   - `DELETE /fornecedores/{id}`: Remove um fornecedor do sistema.

3. **Usuários**:
   - `GET /usuarios`: Lista todos os usuários.
   - `POST /usuarios`: Cria um novo usuário.

### 🛠️ **Recomendação: Uso do Axios**
No Front-End, recomendamos o uso da biblioteca **Axios** para consumir a API, devido à sua simplicidade e suporte a TypeScript.

#### Exemplo de Requisição com Axios e TypeScript:
```typescript
import axios, { AxiosResponse } from 'axios';

// Definição da interface para os dados da peça
interface Peca {
  id: number;
  nome: string;
  descricao: string;
  preco: number;
}

// URL base da API
const api = axios.create({
  baseURL: 'http://localhost:8080',
});

// Exemplo de requisição GET para listar peças
const listarPecas = async (): Promise<Peca[]> => {
  try {
    const resposta: AxiosResponse<Peca[]> = await api.get('/pecas');
    console.log('Peças cadastradas:', resposta.data);
    return resposta.data;
  } catch (erro) {
    console.error('Erro ao buscar peças:', erro);
    throw erro;
  }
};

// Exemplo de uso da função
listarPecas().then((pecas) => console.log(pecas));
```

### 🌐 **Fluxo de Integração**
- O **Front-End** deve consumir os dados da API para exibir informações aos usuários, como:
  - Catálogo de peças.
  - Lista de fornecedores.
  - Detalhes de estoque.
- Além disso, o **Front-End** pode enviar dados para serem processados pelo Back-End, como ao cadastrar um novo fornecedor ou atualizar informações de uma peça.

---

## 🧪 Testes

Para rodar os testes automatizados, utilize o comando:
```bash
mvn test
```

---

## 🌐 Deploy

O deploy da aplicação está configurado com **GitHub Actions** para automação do pipeline de CI/CD. As alterações nos branches **main** e **develop** acionam o fluxo de integração contínua, que inclui:

- Execução de testes.
- Build da aplicação.
- Deploy na infraestrutura configurada.

---