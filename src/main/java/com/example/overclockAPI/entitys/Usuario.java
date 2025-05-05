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

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private  String email;

    @Column(name = "senha_hash")
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoUsuario tipo;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime data_criacao;

    @Column(name = "cpf")
    private int cpf;

    @PrePersist //incrementar automaticamente data da criação do usuario
    private void DataCriacao(){
        this.data_criacao = LocalDateTime.now();
    }
}