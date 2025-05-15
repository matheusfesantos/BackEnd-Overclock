package com.example.overclockAPI.controlers;

import com.example.overclockAPI.dto.db.ComprasDTO;
import com.example.overclockAPI.services.endpoints.ComprasService;
import com.example.overclockAPI.services.endpoints.FornecedoresService;
import com.example.overclockAPI.services.endpoints.UsuarioService;
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

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    FornecedoresService fornecedoresService;

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

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> editarCompra
            (@PathVariable Long id, @RequestBody ComprasDTO comprasDTO){
        boolean exist = comprasService.existsById(id);

        try{
            if (!exist){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
                        (Map.of("message","Compra não encontrada!"));
            }

            boolean compraAtualizada =
                    comprasService.editarCompra(id, comprasDTO.observacao());
            if (compraAtualizada){
                return ResponseEntity.status(HttpStatus.ACCEPTED).body
                        (Map.of("message","Compra atualizada com sucesso!"));
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
                        (Map.of("message","Erro ao atualizar compra!"));
            }
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body
                    (Map.of("message","Aconteceu um erro inesperado!"));
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> criarCompra(@RequestBody ComprasDTO comprasDTO){

        boolean usuarioExist =
                usuarioService.validarId(Long.valueOf(comprasDTO.id_usuario()));

        boolean fornecedorExist =
                fornecedoresService.validarById(Long.valueOf(comprasDTO.id_fornecedor()));

        if (!usuarioExist || !fornecedorExist){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
                    (Map.of("message","Usuario ou Fornecedor não existem"));
        }

        try {
            boolean compraCriada = comprasService.saveCompra(comprasDTO);
            if (compraCriada){
                return ResponseEntity.status(HttpStatus.CREATED).body
                        (Map.of("message","Compra criada com sucesso!"));
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
                        (Map.of("message","Erro ao criar compra!"));
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body
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
}
