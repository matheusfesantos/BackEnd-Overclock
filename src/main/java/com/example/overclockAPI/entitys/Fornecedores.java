package com.example.overclockAPI.entitys;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "fornecedores")
@Data
public class Fornecedores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_fornecedor;

    @Column(name = "nome")
    private String nome_fornecedor;

    @Column(name = "cnpj")
    private String cpnj_fornecedor;

    @Column(name = "telefone")
    private String telefone_fornecedor;

    @Column(name = "email")
    private String email_fornecedor;
}
