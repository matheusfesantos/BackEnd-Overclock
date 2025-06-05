ALTER TABLE pedidos ALTER COLUMN status TYPE TEXT;

UPDATE pedidos SET status = 'PENDENTE' WHERE LOWER(status) = 'pedente';
UPDATE pedidos SET status = 'COMPRADO' WHERE LOWER(status) = 'comprado';
UPDATE pedidos SET status = 'ENTREGUE' WHERE LOWER(status) = 'entregue';

DROP TYPE IF EXISTS status_pedido;

CREATE TYPE status_pedido AS ENUM ('PENDENTE', 'COMPRADO', 'EM_ESTOQUE', 'ENTREGUE');

ALTER TABLE pedidos ALTER COLUMN status TYPE status_pedido USING status::status_pedido;