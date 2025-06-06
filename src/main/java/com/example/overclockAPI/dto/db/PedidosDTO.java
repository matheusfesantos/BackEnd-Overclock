package com.example.overclockAPI.dto.db;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record PedidosDTO(
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDate data_pedido,
        String observacao,
        Long id_fornecedor,
        Long id_peca,
        int quantidade
)
{}
