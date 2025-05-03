-- POPULAR O BANCO
INSERT INTO fornecedores (nome, cnpj, telefone, email)
VALUES
    ('Fornecedor Tech Ltda', '12345678901234', '(11) 98765-4321', 'contato@fornecedortech.com.br'),
    ('Componentes Eletrônicos S.A.', '98765432109876', '(21) 91234-5678', 'vendas@componenteseletronicos.com.br');


INSERT INTO pecas (nome, descricao, categoria, marca, qtd_estoque, preco_custo, preco_venda, ID_fornecedor)
VALUES
    ('Memória RAM 8GB', 'Memória DDR4 8GB 2400MHz', 'Memória', 'Kingston', 50, 150.00, 199.99, 1),
    ('Gabinete ATX', 'Gabinete Gamer com LED RGB', 'Gabinete', 'Corsair', 20, 200.00, 299.99, 2);

INSERT INTO pedidos (status, observacao)
VALUES ('Pendente', 'Pedido de memórias RAM e gabinetes.');

INSERT INTO movimentacoes (ID_pedido, ID_peca, quantidade, tipo)
VALUES
    (1, 1, 10, 'saida'),  -- Saída de 10 memórias RAM
    (1, 2, 5, 'saida');    -- Saída de 5 gabinetes