package com.example.overclockAPI.entitys;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "movimentacoes_compra")
public class MovimentacaoCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimentacao")  // Corrigindo o nome da coluna
    private Long idMovimentacaoCompras;

    @ManyToOne
    @JoinColumn(name = "id_compra")
    private Compras compra;

    @ManyToOne
    @JoinColumn(name = "id_peca")
    private Pecas peca;

    private int quantidade;

    @Column(name = "data_movimentacao")
    private LocalDate dataMovimentacao;

    @PrePersist
    public void prePersist() {
        this.dataMovimentacao = LocalDate.now();
    }

}
