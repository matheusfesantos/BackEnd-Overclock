package com.example.overclockAPI.controlers;

import com.example.overclockAPI.dto.db.KardexView;
import com.example.overclockAPI.services.KardexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/kardex")
public class MovimentacaoController {

    @Autowired
    KardexService service;

    @GetMapping
    public List<KardexView> listar() {
        try{
            return service.listarKardex();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}