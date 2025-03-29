package com.example.overclockAPI.entitys;

import com.example.overclockAPI.entitys.enums.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

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

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime data_criacao;

    @Column(name = "CPF")
    private int cpf;

    @PrePersist //incrementar automaticamente data da criação do usuario
    private void DataCriacao(){
        this.data_criacao = LocalDateTime.now();
    }
}