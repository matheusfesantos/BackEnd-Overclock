package com.example.overclockAPI.entitys;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "USUARIOS")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_usuario;

    private String nome;

    private  String email;

    private String senha_hash;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

    private Timestamp data_criacao;

    @Column(name = "CPF")
    private int cpf;

}
