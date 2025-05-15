package com.example.overclockAPI.dto.security;

import com.example.overclockAPI.entitys.enums.TipoUsuario;

public record RegisterDTO(
        String username,
        String senha,
        String nome,
        String email,
        String cpf,
        TipoUsuario tipoUsuario
) {}
