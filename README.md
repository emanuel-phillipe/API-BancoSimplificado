# API - Banco Simplificado
Uma API que simula o processo de cadastro de usuário em um banco de dados. Usando Java Spring, criou-se também uma imitação de transações de usuário para usuário, além de salvar essas transações também no banco de dados. 

### Criação de Usuários
Ao criar um novo usuário, é necessário informações do cliente, que são: CPF, E-mail, número de telefone e nome completo. Ademais, na criação, a conta do cliente, como em um banco comum, tem o item "balance" definido para 0.0, que representa o saldo da pessoa.

### Transferências
Para que uma transferência aconteça, faz-se necessário o CPF do remetente e destinatário do valor e também o dinheiro a ser enviado para fazer a requisição. 

## Dependências
- Spring Web;
- PostgresSQL;
- Flyway Migration;
- Lombock;
