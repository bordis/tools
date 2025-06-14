# 📈 Projeto Spring Boot com Spring AI e OpenAI - Consulta de Preço do Bitcoin

Este projeto é uma aplicação simples utilizando **Spring Boot** com **Spring AI** e **OpenAI**, demonstrando como utilizar *Tools* (ferramentas) em um agente para buscar informações externas — neste caso, o preço atual do **Bitcoin** usando a [API Ninjas](https://api-ninjas.com/).

## 🧠 Tecnologias Utilizadas

- Java 17+
- Spring Boot 3.x
- Spring AI
- OpenAI
- API Ninjas

## ⚙️ Requisitos

- Java 17 ou superior
- Maven
- Conta na OpenAI com chave de API
- Conta na [API Ninjas](https://api-ninjas.com/) com chave de API

## 🔐 Configuração de Variáveis de Ambiente

Antes de executar o projeto, configure as seguintes variáveis de ambiente no seu sistema ou em um arquivo `.env`:

```bash
# Chave da API OpenAI
OPENAI_API_KEY=sk-...

# Chave da API Ninjas
API_NINJA_KEY=...

# Endpoint da API de preço do Bitcoin da API Ninjas
API_NINJA_URL_BITCOIN=https://api.api-ninjas.com/v1/bitcoin
```
