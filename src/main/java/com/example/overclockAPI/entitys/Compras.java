package com.example.overclockAPI.entitys;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "compras")
@Data
public class Compras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_compra;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime data_compra;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_peca")
    private Pecas pecas;

    @Column(name = "qtd_compras")
    private int quantidade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private Usuarios usuarios;

    @Column(name = "id_fornecedor")
    private Long id_fornecedor;

    private String observacao;

    @PrePersist
    private void DataCompra(){
        this.data_compra = LocalDateTime.now();
    }
}
