<!-- PROJECT LOGO -->
<br />
<div align="center">
  <h3 align="center">Sistema de Gerenciamento de Reuniões e Salas de Conferência</h3>

  <p align="center">
    Uma aplicação para gerenciar reservas de salas de conferência e reuniões.
    <br />
    <br />
    <a href="#">Visualizar Demo</a>
    ·
    <a href="#">Reportar Bug</a>
    ·
    <a href="#">Solicitar Recurso</a>
  </p>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Sumário</summary>
  <ol>
    <li><a href="#sobre-o-projeto">Sobre o Projeto</a></li>
    <li><a href="#tecnologias-utilizadas">Tecnologias Utilizadas</a></li>
    <li><a href="#iniciando-o-projeto">Iniciando o Projeto</a></li>
    <ul>
        <li><a href="#pré-requisitos">Pré-requisitos</a></li>
        <li><a href="#instalação">Instalação</a></li>
    </ul>
    <li><a href="#uso">Uso</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contribuições">Contribuições</a></li>
    <li><a href="#licença">Licença</a></li>
  </ol>
</details>

---

## Sobre o Projeto

O Sistema de Gerenciamento de Reuniões e Salas de Conferência foi desenvolvido para facilitar o agendamento e gerenciamento de salas de conferência para reuniões, eventos corporativos e outras necessidades empresariais. Agora, com a adição de envio de e-mails para notificações e o uso de DTOs e Mappers, o sistema está mais robusto e escalável.

## Tecnologias Utilizadas

As principais tecnologias utilizadas no projeto são:

- **Java Spring Boot** - Framework para o desenvolvimento do backend.
- **PostgreSQL** - Banco de dados utilizado para armazenar as informações.
- **Lombok** - Biblioteca para facilitar a criação de código repetitivo como getters, setters, e construtores.
- **Arquitetura em Camadas** - MVC (Model-View-Controller) e Clean Architecture para garantir separação de responsabilidades.
- **Mockito** - Framework para testes unitários.
- **DTO e Mapper** - Usado para transferir dados de maneira eficiente entre as camadas do sistema.

## Iniciando o Projeto

### Pré-requisitos

Para rodar o projeto localmente, é necessário ter os seguintes itens instalados:

* Docker (opcional para execução sem configuração manual)
* Java 11 ou superior
* PostgreSQL

### Instalação

1. Clone o repositório
   ```sh
   git clone https://github.com/LuizGuilhes123/sistema-reunioes.git```

2. Passos para Configuração

. Crie um banco de dados PostgreSQL.
. Atualize o arquivo `application.properties` com suas credenciais do banco de dados e configurações de e-mail.
. Execute o projeto:
   `./mvnw spring-boot:run`
  
### Uso

Uma vez que o projeto está rodando, você pode acessar as seguintes funcionalidades principais:

- CRUD de Reuniões
- CRUD de Salas de Conferência
- Gerenciamento de Reservas
- Envio de e-mails para notificação de agendamentos e cancelamentos
- Visualização de Disponibilidade de Salas

### Roadmap

- [x] Implementar DTOs e Mappers
- [x] Adicionar testes unitários com Mockito
- [x] Implementar envio de e-mails para notificações de reuniões
- [ ] Implementar autenticação de usuários
- [ ] Adicionar filtros avançados para pesquisa de salas
- [ ] Integrar notificações por e-mail para lembretes de reuniões

## Contribuições

Contribuições são o que fazem a comunidade open-source um lugar incrível para aprender, inspirar, e criar. Qualquer contribuição que você fizer será **muito apreciada**.

1. Realize um Fork do projeto
2. Crie uma Branch para sua Feature (`git checkout -b feature/AmazingFeature`)
3. Faça o Commit das suas mudanças (`git commit -m 'Adiciona uma nova feature incrível'`)
4. Realize o Push para a Branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## Licença

Distribuído sob a licença MIT. Veja `LICENSE` para mais informações.
