CREATE TYPE tipo_movimentacao
AS ENUM ('entrada', 'saida');

CREATE TABLE movimentacao_pedido (
    ID_movimentacao INT PRIMARY KEY,
    ID_pedido INT,
    ID_peca INT,
    quantidade INT NOT NULL,
    tipo tipo_movimentacao NOT NULL,
    data_movimentacao DATE DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ID_pedido) REFERENCES pedidos(ID_pedido),
    FOREIGN KEY (ID_peca) REFERENCES pecas(ID_peca)
);

CREATE TABLE compras (
    ID_compra INT PRIMARY KEY,
    data_compra DATE DEFAULT CURRENT_TIMESTAMP,
    observacao TEXT,
    ID_usuario INT,
    ID_fornecedor INT,
    FOREIGN KEY (ID_usuario) REFERENCES usuarios(ID_usuario),
    FOREIGN KEY (ID_fornecedor) REFERENCES fornecedores(ID_fornecedor)
);

CREATE TABLE movimentacoes_compra(
    ID_movimentacao INT PRIMARY KEY,
    ID_compra INT,
    ID_peca INT,
    quantidade INT NOT NULL,
    tipo tipo_movimentacao NOT NULL,
    data_movimentacao DATE DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ID_compra) REFERENCES compras(ID_compra),
    FOREIGN KEY (ID_peca) REFERENCES pecas(ID_peca)
)