package com.example.overclockAPI.entitys.dto;

import com.example.overclockAPI.entitys.enums.TipoUsuario;

public record RegisterDTO(
        String username,
        String senha,
        String nome,
        String email,
        String cpf,
        TipoUsuario tipoUsuario
) {}
