package com.example.overclockAPI.repositories;

import com.example.overclockAPI.dto.db.KardexView;
import com.example.overclockAPI.entitys.Pecas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface KardexRepository extends JpaRepository<Pecas, Long> {
    
    @Query(value = """
        SELECT
            kardex.data as data,
            kardex.tipo as tipo,
            kardex.id_peca as idPeca,
            p.descricao as descricao,
            kardex.quantidade as quantidade,
            kardex.origem as origem
        FROM (
            SELECT
                mc.data_movimentacao AS data,
                'Entrada' AS tipo,
                mc.id_peca,
                mc.quantidade,
                'Compra' AS origem
            FROM movimentacoes_compra mc
    
            UNION ALL
    
            SELECT
                mp.data_movimentacao AS data,
                'Saída' AS tipo,
                mp.id_peca,
                mp.quantidade,
                'Pedido' AS origem
            FROM movimentacoes_pedido mp
        ) AS kardex
        JOIN pecas p ON kardex.id_peca = p.id_peca
        ORDER BY p.descricao, kardex.data, kardex.tipo
    """, nativeQuery = true)
    List<KardexView> listarKardex();
}