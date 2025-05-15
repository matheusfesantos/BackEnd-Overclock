ALTER TABLE movimentacoes_compra DROP CONSTRAINT movimentacoes_compra_id_compra_fkey;

ALTER TABLE compras DROP CONSTRAINT compras_pkey;

CREATE SEQUENCE compras_id_compra_seq;

ALTER TABLE compras ALTER COLUMN id_compra SET DEFAULT nextval('compras_id_compra_seq');

ALTER TABLE compras ADD CONSTRAINT compras_pkey PRIMARY KEY (id_compra);

ALTER TABLE movimentacoes_compra
    ADD CONSTRAINT movimentacoes_compra_id_compra_fkey
        FOREIGN KEY (id_compra) REFERENCES compras(id_compra);

SELECT setval('compras_id_compra_seq', (SELECT MAX(id_compra) FROM compras));