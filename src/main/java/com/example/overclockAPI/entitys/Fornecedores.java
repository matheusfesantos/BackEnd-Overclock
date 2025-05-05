package com.example.overclockAPI.entitys;

import jakarta.persistence.*;

@Entity
@Table(name = "forncedores")
public class Fornecedores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_fornecedor;

    @Column(name = "nome")
    private String nome_fornecedor;

    @Column(name = "cpnj")
    private String cpnj_fornecedor;

    @Column(name = "telefone")
    private String telefone_fornecedor;

    @Column(name = "email")
    private String email_fornecedor;
}
