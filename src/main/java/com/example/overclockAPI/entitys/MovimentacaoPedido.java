package com.example.overclockAPI.entitys;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "movimentacoes_pedido")
public class MovimentacaoPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimentacao")
    private Long idMovimentacaoPedido;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedidos pedido;

    @ManyToOne
    @JoinColumn(name = "id_peca")
    private Pecas peca;

    private int quantidade;

    @Column(name = "data_movimentacao")
    private LocalDate dataMovimentacao;

    @PrePersist
    public void dataMovimentacao(){
        this.dataMovimentacao = LocalDate.now();
    }
}
