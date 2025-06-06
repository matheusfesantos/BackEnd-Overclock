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

    @GetMapping("{id_fornecedor}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Fornecedores getbyId (@PathVariable Long id_fornecedor) {
        return fornecedoresService.findById(id_fornecedor);
    }

    @PostMapping
    public ResponseEntity<?> update(@RequestBody Fornecedores fornecedores) {
        try{
            if (fornecedores.getCnpj_fornecedor() == null || fornecedores.getNome_fornecedor() == null
                    || fornecedores.getEmail_fornecedor() == null || fornecedores.getTelefone_fornecedor() == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).
                        body(Map.of("message", "Campos Nullos"));
            }
            fornecedoresService.save(fornecedores);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    Map.of("message", "Fornecedor cadastrado com sucesso!")
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of("message", "Erro ao cadastrar fornecedor!")
            );
        }
    }

    @DeleteMapping("{id_fornecedor}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable Long id_fornecedor) {
        Map<String, String> resposta = new HashMap<>();

        try{
            boolean usuarioDeletado = fornecedoresService.deletarUsuario(id_fornecedor);

            if(usuarioDeletado){
                resposta.put("message", "Usuario foi deletado com sucesso!");
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resposta);
            }
            else{
                resposta.put("message", "Esse usuario não existe!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
            }
        }
        catch (Exception e){
            resposta.put("message", "Erro ao deletar usuario!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
        }
    }
}