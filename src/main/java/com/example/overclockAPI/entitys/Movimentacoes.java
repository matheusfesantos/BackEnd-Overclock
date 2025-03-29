package com.example.overclockAPI.entitys;

import com.example.overclockAPI.entitys.enums.TipoMovimentacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "movimentacoes")
public class Movimentacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_movimentacao;

    private int ID_pedido;

    @ManyToOne
    @JoinColumn(name = "ID_peca", nullable = false)
    private Pecas pecas;

    @Column(name = "quantidade")
    private int quantidade;

    @Column(name = "tipo")
    private TipoMovimentacao tipo;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime data_movimentacao;

    @PrePersist
    public void DataMovimentacao() {
        this.data_movimentacao = LocalDateTime.now();
    }
}
