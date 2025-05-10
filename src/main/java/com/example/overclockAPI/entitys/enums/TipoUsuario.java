package com.example.overclockAPI.entitys.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoUsuario {
    admin("admin"),

    usuario("usuario");

    private String role;

    TipoUsuario(String role){
        this.role = role;
    }

    @JsonValue
    public String getRole(){
        return role;
    }

    @JsonCreator
    public static TipoUsuario fromString(String role){
        for(TipoUsuario tipoUsuario : TipoUsuario.values()){
            if(tipoUsuario.getRole().equals(role)){
                return tipoUsuario;
            }
        }
        throw new IllegalArgumentException("Tipo de usuario inválido!");
    }
}
