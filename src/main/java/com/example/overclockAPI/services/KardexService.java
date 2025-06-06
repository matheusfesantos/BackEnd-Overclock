package com.example.overclockAPI.services;


import com.example.overclockAPI.dto.db.KardexView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class KardexService {
    @Autowired
    com.example.overclockAPI.repositories.KardexRepository repository;

    public List<KardexView> listarKardex() {
        return repository.listarKardex();
    }
}