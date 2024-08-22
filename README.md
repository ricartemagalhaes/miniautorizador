Mini-Autorizador
Este projeto é um mini-autorizador que processa transações de cartões de benefícios como Vale Refeição e Vale Alimentação. Ele permite criar cartões, consultar saldo e autorizar transações.

Tecnologias Utilizadas
Java 17
Spring Boot 3.3.3
MySQL
Maven
Como Rodar o Projeto
Clone o Repositório

bash
Copiar código
git clone https://github.com/ricartemagalhaes/miniautorizador
Suba o Banco de Dados

Utilize Docker Compose para iniciar o MySQL:

bash
Copiar código
docker-compose up -d
Configure a Aplicação

Verifique as configurações do banco de dados no arquivo src/main/resources/application.properties.

Execute a Aplicação

bash
Copiar código
./mvnw spring-boot:run
Endpoints Principais
Criar Cartão: POST /cartoes
Consultar Saldo: GET /cartoes/{numeroCartao}
Autorizar Transação: POST /transacoes
Como Testar
Execute os testes automatizados:

bash
Copiar código
./mvnw test
