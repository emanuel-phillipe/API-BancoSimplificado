CREATE TABLE transactions (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    amount FLOAT NOT NULL,
    sender_cpf TEXT NOT NULL,
    receiver_cpf TEXT NOT NULL,
    time_created DATE NOT NULL
);