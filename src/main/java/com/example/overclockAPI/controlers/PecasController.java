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
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Pecas> salvarPecas(@RequestBody Pecas pecas){
        return ResponseEntity.ok(pecasService.salvarPecas(pecas));
    }

    /*
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, String>> editarPecas
            (@PathVariable Long id, @RequestBody Pecas pecas){
        boolean atualizado = pecasService.deletarPecas(id);
        Map<String, String> resposta = new HashMap<>();

        if(atualizado){
            resposta.put("message", pecas.getNome()+" atualizada com sucesso");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(resposta);
        }
        else{
            resposta.put("message", "Erro ao atualizar" + pecas.getNome());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
        }
    }
     */

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
