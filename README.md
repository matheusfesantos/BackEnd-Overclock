# BackEnd-Projeto-Integrador
<p>
  <img src="https://skillicons.dev/icons?i=spring,mysql,aws,docker,jwt&theme=dark" width="150" alt="Framewors"/>
</p>

Bem-vindo ao repositório do **BackEnd-Projeto-Integrador**! Este projeto foi desenvolvido como parte de um **projeto integrador da faculdade**, utilizando tecnologias modernas para criar uma API backend funcional e escalável.

## 📋 Sobre o Projeto

O objetivo deste projeto é desenvolver uma API RESTful para [descreva brevemente o propósito do sistema ou problema a ser resolvido]. A aplicação foi construída com o **Spring Framework** e outras ferramentas para garantir performance, simplicidade e escalabilidade.

## 🚀 Tecnologias Utilizadas

As principais tecnologias e dependências utilizadas neste projeto incluem:

- **Java 17**: Linguagem de programação.
- **Spring Framework**:
  - **Spring Web**: Para construção da API REST.
  - **Spring DevTools**: Para desenvolvimento mais rápido com hot reload.
- **Lombok**: Para reduzir boilerplate no código.
- **MySQL Driver**: Para conexão com o banco de dados MySQL.
- **Maven**: Gerenciador de dependências e build.
- **Docker**: Ferramenta de containerização para portabilidade.
- **Render**: Plataforma de hospedagem utilizada para deploy.
- **CI/CD**: Pipeline de Integração e Entrega Contínuas.

## 📂 Estrutura do Projeto

A estrutura do projeto segue as boas práticas do Spring Framework:

```
BackEnd-Projeto-Integrador/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com.example.projeto/  # Pacotes principais
│   │   │   │   ├── controllers/      # Controladores da aplicação
│   │   │   │   ├── services/         # Lógica de negócios
│   │   │   │   ├── repositories/     # Repositórios de acesso ao banco
│   │   │   │   ├── models/           # Modelos de dados
│   │   ├── resources/
│   │       ├── application.yml       # Configurações do Spring
├── docker-compose.yml                # Configuração de serviços Docker
├── pom.xml                           # Arquivo de configuração do Maven
└── ...
```

## 🔧 Instalação e Execução

### Pré-requisitos
- **Java 17** instalado.
- **Docker** e **Docker Compose** instalados.
- **Maven** instalado.

### Passos
1. Clone o repositório:
   ```bash
   git clone https://github.com/matheusfesantos/BackEnd-Projeto-Integrador.git
   ```
2. Navegue até o diretório do projeto:
   ```bash
   cd BackEnd-Projeto-Integrador
   ```

3. Execute os serviços com Docker Compose:
   ```bash
   docker-compose up
   ```

4. Certifique-se de que o banco de dados está configurado corretamente no arquivo `application.yml`.

5. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```

6. Acesse a API na URL:
   ```
   http://localhost:8080
   ```

## 🧪 Testes

Para rodar os testes automatizados, utilize o comando:
```bash
mvn test
```

## 🌐 Deploy

O deploy da aplicação é realizado na plataforma **Render**. O pipeline de CI/CD foi configurado para realizar o build e deploy automaticamente após os commits na branch principal.

## 📚 Aprendizados e Contribuições Acadêmicas

Este projeto foi uma oportunidade para aplicar:
- Boas práticas de desenvolvimento backend com **Spring**.
- Integração com banco de dados relacional (**MySQL**).
- Automação de deploy com **CI/CD**.
- Containerização utilizando **Docker**.
- Uso de ferramentas como **Lombok** para simplificação do código.

## 🤝 Contribuição

Contribuições externas são bem-vindas! Siga os passos abaixo para contribuir:

1. Faça um fork do repositório.
2. Crie um branch para suas alterações:
   ```bash
   git checkout -b minha-feature
   ```
3. Faça commit das suas alterações:
   ```bash
   git commit -m "Descrição das alterações"
   ```
4. Envie suas alterações para o branch remoto:
   ```bash
   git push origin minha-feature
   ```
5. Abra um Pull Request.

## 🛡️ Licença

Este projeto está licenciado sob a licença **MIT**. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.

## 📞 Contato

Se tiver dúvidas ou sugestões, sinta-se à vontade para entrar em contato:
- **Autor**: Matheus F. E. Santos
- **GitHub**: [@matheusfesantos](https://github.com/matheusfesantos)

---

Esperamos que este projeto seja útil tanto para fins acadêmicos quanto como aprendizado prático. 🚀
```

Certifique-se de revisar e ajustar a seção **Sobre o Projeto** para incluir uma descrição mais detalhada do propósito do sistema, caso necessário. Se precisar de mais ajuda, é só avisar!