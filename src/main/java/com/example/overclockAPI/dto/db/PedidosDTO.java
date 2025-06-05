package com.example.overclockAPI.dto.db;

import com.example.overclockAPI.entitys.enums.TipoPedido;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record PedidosDTO(
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDate data_pedido,

        TipoPedido status,
        String observacao,
        Long id_usuario,
        Long id_compra,
        String titulo
)
{}
