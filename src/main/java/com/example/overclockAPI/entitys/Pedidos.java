package com.example.overclockAPI.entitys;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pedidos")
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pedido;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "data_pedido")
    private LocalDateTime data_pedido;

    @Column(name = "qtd_pedidos")
    private int quantidade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private Usuarios usuarios;

    @Column(name = "id_peca")
    private Long idPeca;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "id_fornecedor")
    private Long idFornecedor;

    @PrePersist
    private void DataPedido(){
        this.data_pedido = LocalDateTime.now();
    }
}
