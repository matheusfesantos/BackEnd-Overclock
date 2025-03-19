package com.example.overclockAPI.entitys;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_usuario")
    private Long id;

    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private  String email;

    @Getter
    @Setter
    @Column(name = "senha_hash", columnDefinition = "TEXT")
    private String senha;

    @Getter
    @Setter
    private TipoUsuario tipo;

    @Getter
    @Setter
    private Timestamp data_criacao;

    @Getter
    @Setter
    @Column(name = "CPF")
    private int cpf;

    @Getter
    @Setter
    private String authToken;

}
