# 🔗 Short URL - Encurtador de URLs

## 📋 Descrição
Este é um serviço de encurtamento de URLs desenvolvido com Spring Boot que permite transformar URLs longas em versões mais curtas e gerenciáveis. O projeto utiliza MongoDB para armazenamento persistente e Redis para cache, otimizando o desempenho das requisições.

## 🚀 Tecnologias Utilizadas
- Java 17
- Spring Boot 3.5.3
- Spring Data MongoDB
- Spring Data Redis
- Spring Validation
- Lombok
- Maven

## 🛠️ Pré-requisitos
- Java 17 ou superior
- Maven
- MongoDB
- Redis


## 🔧 Arquitetura

### Camadas do Projeto
- **Controller**: Manipula as requisições HTTP
- **Service**: Implementa a lógica de negócio
- **Repository**: Gerencia o acesso aos dados
- **Model**: Define as entidades e DTOs
- **Redis Service**: Gerencia o cache das URLs

### Componentes Principais
- **ShortUrlController**: API REST para encurtamento de URLs
- **ShortUrlService**: Lógica de negócio para geração e gestão de URLs curtas
- **RedisService**: Gerenciamento de cache com Redis
- **MongoDB**: Armazenamento persistente das URLs

## 📝 Features
- Encurtamento de URLs longas
- Cache em Redis para acesso rápido às URLs mais utilizadas
- Validação de URLs
- Expiração automática de cache (24 horas por padrão)
- Gestão de URLs através de MongoDB

## ✒️ Autor
- **Diego Sena** - [dsena7](https://github.com/dsena7)

## 🐳 Configuração com Docker
1. Build da aplicação: mvn clean package
2. Build e inicialização dos containers: docker-compose up --build
3. Para parar os containers: docker-compose down


