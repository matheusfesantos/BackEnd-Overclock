package com.example.overclockAPI.entitys;

import com.example.overclockAPI.entitys.enums.TipoPedido;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private TipoPedido status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private Usuarios usuarios;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_compra")
    private Compras compras;

    @PrePersist
    private void DataPedido(){
        this.data_pedido = LocalDateTime.now();
    }
}
