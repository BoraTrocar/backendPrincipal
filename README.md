# :books: Bora Troca BackEnd

## Descrição
Bora troca é uma aplicação web de troca de livros


## Pré requisitos
Para que o projeto rode corretamente, certifique-se de ter instalado em sua máquina: 

- Java (Versão: 17 ou superior)
- Maven (Versão: 3.9.2 ou superior) 
- MySql (Versão: 8.0 ou superior)

## Clonando o repositorio

Abra seu terminal do git e execute o comando:

```

git clone https://github.com/RickAE/boraTrocaBackEnd.git

```

## Importando o projeto na IDE      

Use a IDE de sua preferência e importe o projeto como um Maven Project


## Conexão com o banco de dados

O banco de dados da aplicação é o MySql, que está rodando na conexão local padrão, utilizando a porta 3306. 
O usuário e senha padrões são **root**, caso use algo diferente, fique a vontade para mudar no application properties:

```

url = jdbc:mysql://${MYSQL_HOST:localhost}:3306/boratroca?createDatabaseIfNotExist=true
username = seu usuario
password = sua senha

```

