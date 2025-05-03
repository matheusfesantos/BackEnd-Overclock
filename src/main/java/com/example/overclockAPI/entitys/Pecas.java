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

    @Column(name = "nome")
    private String nome_do_produto;

    @Column(name = "descricao")
    private String descricao_do_produto;

    @Column(name = "categoria")
    private String categoria_do_produto;

    @Column(name = "marca")
    private String marca_do_produto;

    @Column(name = "qtd_estoque")
    private int quantidade_estoque;

    @Column(name = "preco_custo")
    private double preco_custo;

    @Column(name = "preco_venda")
    private double preco_venda;

    //@ManyToOne
    //@JoinColumn
    private int ID_fornecedor;
}
