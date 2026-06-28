# 🎮 Sistema de Alerta de Promoções de Jogos

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Design Patterns](https://img.shields.io/badge/Design_Patterns-Observer-brightgreen?style=for-the-badge)

## 📖 Sobre o Projeto
Este projeto foi desenvolvido como resolução do desafio prático de **Padrões de Projeto (Design Patterns)** do bootcamp da DIO (Digital Innovation One). 

O sistema simula um catálogo de jogos digitais que monitora alterações de preços e notifica automaticamente os usuários quando um jogo de sua lista de favoritos entra em promoção. O projeto foi construído em **Java Puro**, priorizando boas práticas de Orientação a Objetos, separação de responsabilidades e regras de negócio blindadas.

## ✨ Funcionalidades Aplicadas
O sistema funciona através do terminal e demonstra a aplicação prática de conceitos avançados do Java Core:

* **Persistência de Dados (Padrão DAO):** Conexão com banco de dados MySQL para salvar e buscar o catálogo de jogos, separando a regra de negócio da infraestrutura.
* **Inteligência de Busca (Stream API):** Utilização de `filter`, `map`, `sorted` e `min` para processar a lista de jogos, encontrar os itens mais baratos e filtrar as promoções em tempo real.
* **Tratamento de Exceções Customizadas:** Validações rigorosas na camada de serviço (Service). O sistema impede a inserção de preços negativos ou dados nulos, lançando exceções de negócio controladas (`ValidacaoJogoException`) sem interromper a execução da aplicação.
* **Notificações Automatizadas (Padrão Observer):** Implementação arquitetural onde o Usuário (Observador) favorita um Jogo (Assunto). Quando o status do jogo muda para `EM_PROMOCAO`, o sistema dispara alertas (simulação de e-mail) automaticamente para todos os interessados.

## 🛠️ Tecnologias Utilizadas
* **Linguagem:** Java (Orientação a Objetos)
* **Banco de Dados:** MySQL
* **Conceitos Chave:** JDBC, Stream API, Exception Handling, Padrões de Projeto (DAO e Observer).

## 🚀 Como Executar o Projeto
1. Clone este repositório na sua máquina local:
   ```bash
   git clone [https://github.com/SEU_USUARIO/alerta-promocao-jogos.git](https://github.com/SEU_USUARIO/alerta-promocao-jogos.git)
