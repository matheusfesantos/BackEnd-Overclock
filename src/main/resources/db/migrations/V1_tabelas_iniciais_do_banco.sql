-- TABELA USÁRIOS
CREATE TABLE USUARIOS (
    ID_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    senha_hash TEXT NOT NULL ,
    tipo enum('admin','usuario') DEFAULT 'usuario',
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CPF CHAR(14) NOT NULL,
);


-- TABELA FORNECEDORES
CREATE TABLE fornecedores (
    ID_fornecedor INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    telefone VARCHAR(15),
    email VARCHAR(100)
);


-- TABELA PEÇAS
CREATE TABLE pecas (
    ID_peca INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR (120) NOT NULL,
    descricao TEXT,
    categoria VARCHAR (50) NOT NULL,
    marca VARCHAR (50),
    qtd_estoque INT NOT NULL DEFAULT 0,
    preco_custo DECIMAL(10, 2) NOT NULL,
    preco_venda DECIMAL(10, 2) NOT NULL,
    ID_Fornecedor INT,
    FOREIGN KEY (ID_fornecedor) REFERENCES fornecedores(ID_fornecedor)
);


-- TABELA PEDIDOS
CREATE TABLE pedidos (
    ID_pedido INT AUTO_INCREMENT PRIMARY KEY,
    data_pedido DATETIME DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) NOT NULL DEFAULT 'Pendente',
    observacao TEXT,
    ID_usuario INT,
    FOREIGN KEY (ID_usuario) REFERENCES usuarios(ID_usuario)
);

-- TABELA MOVIMENTAÇÃO DE PEDIDOS
CREATE TABLE movimentacao_pedido (
    ID_movimentacao INT AUTO_INCREMENT PRIMARY KEY,
    ID_pedido INT,
    ID_peca INT,
    quantidade INT NOT NULL,
    tipo ENUM('entrada', 'saida') NOT NULL,
    data_movimentacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ID_pedido) REFERENCES pedidos(ID_pedido),
    FOREIGN KEY (ID_peca) REFERENCES pecas(ID_peca)
);

-- TABELA COMPRAS
CREATE TABLE compras (
    ID_compra INT AUTO_INCREMENT PRIMARY KEY,
    data_compra DATETIME DEFAULT CURRENT_TIMESTAMP,
    observacao TEXT,
    ID_usuario INT,
    ID_fornecedor INT,
    FOREIGN KEY (ID_usuario) REFERENCES usuarios(ID_usuario),
    FOREIGN KEY (ID_fornecedor) REFERENCES fornecedores(ID_fornecedor)
);

-- TABELA MOVIMENTAÇÕES DE COMPRA
CREATE TABLE movimentacoes_compra(
    ID_movimentacao INT AUTO_INCREMENT PRIMARY KEY,
    ID_compra INT,
    ID_peca INT,
    quantidade INT NOT NULL,
    tipo ENUM('entrada', 'saida') NOT NULL,
    data_movimentacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ID_compra) REFERENCES compras(ID_compra),
    FOREIGN KEY (ID_peca) REFERENCES pecas(ID_peca)
);
