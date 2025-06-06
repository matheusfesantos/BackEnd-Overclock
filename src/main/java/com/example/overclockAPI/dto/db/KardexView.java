package com.example.overclockAPI.dto.db;

import java.sql.Date;

public interface KardexView {
    Date getData();
    String getTipo();
    Long getIdPeca();
    String getDescricao();
    Long getQuantidade();
    String getOrigem();
}