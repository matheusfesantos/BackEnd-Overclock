package com.example.overclockAPI.entitys;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "pecas")
public class Pecas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_peca;

    private String nome;

    private String descricao;

    private String categoria;

    private String marca;

    @Column(name = "qtd_estoque")
    private int quantidade;

    @Column(name = "preco_custo")
    private double preco;

    @Column(name = "preco_venda")
    private double precoVenda;

    //@ManyToOne
    //@JoinColumn
    private int ID_fornecedor;
}
