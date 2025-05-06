CREATE TABLE fornecedores (
   ID_fornecedor INT PRIMARY KEY,
   nome VARCHAR(100) NOT NULL,
   cnpj VARCHAR(18) NOT NULL UNIQUE,
   telefone VARCHAR(15),
   email VARCHAR(100)
);

CREATE TYPE status_pedido
AS ENUM ('pedente', 'comprado', 'entregue');

CREATE TABLE pedidos (
    ID_pedido INT PRIMARY KEY,
    data_pedido DATE DEFAULT CURRENT_TIMESTAMP,
    status status_pedido NOT NULL,
    observacao TEXT,
    ID_usuario INT,
    FOREIGN KEY (ID_usuario) REFERENCES usuarios(ID_usuario)
);