DROP TYPE status_pedido;

CREATE TYPE status_pedido AS ENUM ('pendente', 'comprado', 'em estoque');

DROP TABLE IF EXISTS movimentacao_pedido CASCADE;
DROP TABLE IF EXISTS movimentacao_compra CASCADE;