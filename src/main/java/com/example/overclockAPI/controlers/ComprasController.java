package com.example.overclockAPI.controlers;

import com.example.overclockAPI.dto.db.ComprasDTO;
import com.example.overclockAPI.infra.security.TokenService;
import com.example.overclockAPI.services.ComprasService;
import com.example.overclockAPI.services.FornecedoresService;
import com.example.overclockAPI.services.PecasService;
import com.example.overclockAPI.services.UsuarioService;
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

    @Autowired
    TokenService tokenService;

    @Autowired
    PecasService pecasService;

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

    @PostMapping
    public ResponseEntity<?> criarCompra(
            @RequestBody ComprasDTO comprasDTO,
            @RequestHeader("Authorization") String authorizationHeader){

        try {
            if (authorizationHeader == null ||!authorizationHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("message", "Formato do token inválido!"));
            }
            String token = authorizationHeader.replace("Bearer ", "");
            String username = tokenService.extrairUsername(token);
            Long userId = usuarioService.buscarIdPorUsername(username);

            if (comprasDTO.id_fornecedor() == 0 || comprasDTO.id_peca() == 0
                    || comprasDTO.quantidade() == 0){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
                        (Map.of("message", "Dados inválidos!"));
            }

            boolean fornecedorExist = fornecedoresService.validarById((long) comprasDTO.id_fornecedor());
            if (!fornecedorExist) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        Map.of("message", "Fornecedor não encontrado com ID: " + comprasDTO.id_fornecedor()));
            }

            boolean pecaExist = pecasService.validarId((long) comprasDTO.id_peca());
            if (!pecaExist) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        Map.of("message", "Peça não encontrada com ID: " + comprasDTO.id_peca()));
            }


            boolean compraCriada = comprasService.saveCompra(comprasDTO, userId);

            if (compraCriada){
                return ResponseEntity.status(HttpStatus.CREATED).body
                        (Map.of("message","Compra criada com sucesso!"));
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
                        (Map.of("message","Erro ao criar compra!"));
            }
        }
        catch (RuntimeException e) {
            if (e.getMessage().contains("Estoque insuficiente")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        Map.of("message", "Estoque insuficiente para realizar a compra",
                                "error", e.getMessage())
                );
            }
            throw e;
        }
        catch (Exception e){
            System.err.println("Erro ao criar compra: " + e.getMessage());
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body
                    (Map.of(
                            "message", "Aconteceu um erro inesperado!",
                            "error", e.getMessage(),
                            "details", e.getClass().getName()
                    ));
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
