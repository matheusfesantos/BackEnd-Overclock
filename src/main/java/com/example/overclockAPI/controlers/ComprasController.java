package com.example.overclockAPI.controlers;

import com.example.overclockAPI.entitys.Compras;
import com.example.overclockAPI.services.endpoints.ComprasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/compras")
public class ComprasController {

    @Autowired
    ComprasService comprasService;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> getAllCompras(){
        try{
             return ResponseEntity.ok(comprasService.findAll());
         }
        catch (Exception e){
            return ResponseEntity.badRequest().body
                    (Map.of("message","Aconteceu um erro inesperado!"));
         }
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> getById(@PathVariable Long id){
        boolean exist = comprasService.existsById(id);

        try{
            if (exist){
                return ResponseEntity.ok(comprasService.findById(id));
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
                        (Map.of("message","Compra não encontrada!"));
            }
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body
                    (Map.of("message","Aconteceu um erro inesperado!"));
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable Long id){
        try{
            boolean compraDeletada = comprasService.deletarCompra(id);

            if (compraDeletada){
                return ResponseEntity.status(HttpStatus.ACCEPTED).body
                        (Map.of("message","Compra deletada com sucesso!"));
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
                        (Map.of("message","Compra não encontrada!"));
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
                    (Map.of("message","Erro ao deletar compra!"));
        }
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, String>> editarCompra
            (@PathVariable Long id, @RequestBody Compras compras){

        try{
            boolean atualizado = comprasService.saveCompra(compras);

            if (atualizado){
                return ResponseEntity.status(HttpStatus.ACCEPTED).body
                        (Map.of("message","Compra atualizada com sucesso!"));
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
                        (Map.of("message","Compras não encontrada!"));
            }

        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
                    (Map.of("message","Erro ao atualizar compra!"));
        }
    }
}
