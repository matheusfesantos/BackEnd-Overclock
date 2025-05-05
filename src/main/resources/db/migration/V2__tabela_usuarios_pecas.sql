CREATE TABLE usuarios (
    id_usuario SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    senha_hash TEXT NOT NULL,
    tipo tipo_usuario default 'usuario',
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    cpf TEXT NOT NULL
);

CREATE TABLE pecas (
    id_peca SERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    descricao TEXT,
    categoria VARCHAR(50) NOT NULL,
    marca VARCHAR(50),
    qtd_estoque INT NOT NULL DEFAULT 0,
    preco_custo DECIMAL(10, 2) NOT NULL,
    preco_venda DECIMAL(10, 2) NOT NULL,
    id_fornecedor INT
);