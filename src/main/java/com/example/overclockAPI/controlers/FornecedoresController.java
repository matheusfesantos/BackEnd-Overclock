package com.example.overclockAPI.controlers;

import com.example.overclockAPI.entitys.Fornecedores;
import com.example.overclockAPI.services.FornecedoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/fornecedores")
public class FornecedoresController {

    @Autowired
    private FornecedoresService fornecedoresService;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Fornecedores> getAll() {
        return fornecedoresService.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Fornecedores getById(@PathVariable Long id_fornecedor) {
        return fornecedoresService.findById(id_fornecedor);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Fornecedores update(Fornecedores fornecedores) {
        return fornecedoresService.save(fornecedores);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable Long id_fornecedor) {
        boolean usuarioDeletado = fornecedoresService.deletarUsuario(id_fornecedor);
        Map<String, String> resposta = new HashMap<>();

        if(usuarioDeletado){
            resposta.put("message", "Usuario foi deletado com sucesso!");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(resposta);
        }
        else{
            resposta.put("message", "Erro ao deletar o usuario !");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
        }
    }
}