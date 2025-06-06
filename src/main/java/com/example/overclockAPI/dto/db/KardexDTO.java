package com.example.overclockAPI.dto.db;

import java.sql.Timestamp;

public interface KardexDTO {
    Timestamp getData();
    String getTipo();
    Integer getIdPeca();    // Integer no lugar de Long para evitar mismatch
    String getDescricao();
    Integer getQuantidade();
    String getOrigem();
}