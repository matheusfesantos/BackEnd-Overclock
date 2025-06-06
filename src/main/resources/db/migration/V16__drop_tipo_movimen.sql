ALTER TABLE movimentacoes_compra
    DROP COLUMN tipo;

ALTER TABLE movimentacao_pedido
RENAME TO movimentacoes_pedido;

ALTER TABLE movimentacoes_pedido
    DROP COLUMN tipo;