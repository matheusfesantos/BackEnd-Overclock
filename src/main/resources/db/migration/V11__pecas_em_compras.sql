ALTER TABLE compras
    ADD COLUMN id_peca INTEGER;

ALTER TABLE compras
    ADD CONSTRAINT compras_id_peca_fkey
        FOREIGN KEY (id_peca) REFERENCES pecas(id_peca);