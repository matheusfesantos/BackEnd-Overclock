package com.example.overclockAPI.entitys;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

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