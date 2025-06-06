package com.example.overclockAPI.controlers;

import com.example.overclockAPI.entitys.Pecas;
import com.example.overclockAPI.services.PecasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/pecas")
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

    @PostMapping
    public ResponseEntity<?> salvarPecas(@RequestBody Pecas pecas) {
        try {
            if (pecas.getQuantidade_estoque() < 0 || pecas.getNome_do_produto() == null ||
                    pecas.getNome_do_produto().isEmpty() || pecas.getDescricao_do_produto() == null ||
                    pecas.getCategoria_do_produto() == null || pecas.getPreco_custo() <= 0 ||
                    pecas.getPreco_venda() <= 0 || pecas.getFornecedor() == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body
                        (Map.of("message", "Campos essenciais nulos"));
            }

            Pecas pecaSalva = pecasService.salvarPecas(pecas);
            return ResponseEntity.status(HttpStatus.CREATED).body
                    (Map.of("message", "Peca cadastrada com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
                    (Map.of("message", "Erro ao cadastrar peca!"));
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, String>> excluirPecas(@PathVariable Long id){
        boolean deletado = pecasService.deletarPecas(id);
        Map<String, String> resposta = new HashMap<>();

        if(deletado){
            resposta.put("message", " delatada com sucesso");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(resposta);
        }
        else{
            resposta.put("message", "Erro ao deletar peça");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
        }
    }
}
