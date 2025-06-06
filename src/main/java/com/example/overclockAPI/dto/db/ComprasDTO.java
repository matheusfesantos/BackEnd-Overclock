package com.example.overclockAPI.dto.db;

public record ComprasDTO(
         Long id_peca,
         Long id_fornecedor,
         int quantidade,
         String observacao){
}
