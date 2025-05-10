package com.example.overclockAPI.entitys.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoUsuario {
    ADMIN("ADMIN"),
    USUARIO("USUARIO");

    private String tipo;

    TipoUsuario(String tipo) {
        this.tipo = tipo;
    }

    @JsonValue
    public String getRole() {
        return this.tipo;
    }

    @JsonCreator
    public static TipoUsuario fromString(String role) {
        if (role == null) {
            throw new IllegalArgumentException("Tipo de usuário não pode ser nulo!");
        }
        
        for (TipoUsuario tipoUsuario : TipoUsuario.values()) {
            if (tipoUsuario.tipo.equalsIgnoreCase(role)) {
                return tipoUsuario;
            }
        }
        throw new IllegalArgumentException(
            "Tipo de usuário inválido! Valores aceitos: ADMIN ou USUARIO"
        );
    }

    @Override
    public String toString() {
        return this.tipo;
    }
}