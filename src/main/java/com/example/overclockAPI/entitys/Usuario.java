package com.example.overclockAPI.entitys;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @Column(name = "username",nullable = false)
    private String username;

    @Getter
    @Setter
    @Column(name = "password", nullable = false)
    private String password;

    @Getter
    @Setter
    @Column(name = "email",nullable = false)
    private  String email;

    @Getter
    @Setter
    @Column(name = "criado_em",nullable = false)
    private String criadoEm;
}
