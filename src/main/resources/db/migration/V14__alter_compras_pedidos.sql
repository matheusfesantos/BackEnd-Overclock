/*
ALTER TABLE pedidos
    ADD COLUMN titulo VARCHAR(255);
 */

/*
 ALTER TABLE compras
    ADD COLUMN id_pedido INT,
    ADD CONSTRAINT fk_pedido FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido);

 */

/*
 ALTER TABLE pedidos
    ADD COLUMN id_compra INT,
    ADD CONSTRAINT fk_compra FOREIGN KEY (id_compra) REFERENCES compras(id_compra);

 */

/*
 ALTER TABLE compras
    ADD COLUMN id_pedido INT,
    ADD CONSTRAINT fk_pedido FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido);
 */