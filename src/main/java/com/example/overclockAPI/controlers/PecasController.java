package com.example.overclockAPI.controlers;

import com.example.overclockAPI.entitys.Pecas;
import com.example.overclockAPI.services.PecasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/pecas/")
public class PecasController {

    @Autowired
    private PecasService pecasService;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<Pecas>> getPecas(){
        List<Pecas> pecasList = pecasService.listarPecas();
        return ResponseEntity.ok(pecasList);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Pecas> getPecasById(@PathVariable Long id){
        ResponseEntity<Pecas> pecasResponseEntity = pecasService.listarPecasPorID(id);
        return ResponseEntity.ok(pecasResponseEntity.getBody());
    }


}
