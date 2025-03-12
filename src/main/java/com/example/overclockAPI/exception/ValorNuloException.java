package com.example.overclockAPI.exception;

import com.example.overclockAPI.entitys.Usuario;

public class ValorNuloException extends RuntimeException {
    public ValorNuloException(Usuario usuario) {
        super("O valor de "+ usuario + " não pode ser nulo");
    }
}
