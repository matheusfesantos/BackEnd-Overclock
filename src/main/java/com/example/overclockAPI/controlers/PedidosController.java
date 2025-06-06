package com.example.overclockAPI.controlers;

import com.example.overclockAPI.dto.db.PedidosDTO;
import com.example.overclockAPI.infra.security.TokenService;
import com.example.overclockAPI.services.FornecedoresService;
import com.example.overclockAPI.services.PecasService;
import com.example.overclockAPI.services.UsuarioService;
import com.example.overclockAPI.services.auth.PedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/pedidos")
public class PedidosController {

    @Autowired
    PedidosService pedidosService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    FornecedoresService fornecedoresService;

    @Autowired
    PecasService pecasService;

    @Autowired
    TokenService tokenService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(pedidosService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(pedidosService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> salvarPedido (@RequestBody PedidosDTO pedidosDTO,
                                           @RequestHeader("Authorization") String authorizationHeader){
        if (authorizationHeader == null ||!authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(Map.of("message", "Token invalido"));
        }

        try {
            String token = authorizationHeader.substring(7);
            String username = tokenService.extrairUsername(token);
            Long userId = usuarioService.buscarIdPorUsername(username);

            if (pedidosDTO.id_peca() == 0 || pedidosDTO.id_fornecedor() == 0
                    || pedidosDTO.quantidade() == 0){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).
                        body(Map.of("message", "Campos nulos"));
            }

            boolean fornecedorExist = fornecedoresService.validarById((long) pedidosDTO.id_fornecedor());
            if (!fornecedorExist) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        Map.of("message", "Fornecedor não encontrado com ID: " + pedidosDTO.id_fornecedor()));
            }

            boolean pecaExist = pecasService.validarId((long) pedidosDTO.id_peca());
            if (!pecaExist) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        Map.of("message", "Peça não encontrada com ID: " + pedidosDTO.id_peca()));
            }

            boolean pedidoCriado = pedidosService.save(pedidosDTO, userId);

            if (pedidoCriado){
                return ResponseEntity.status(HttpStatus.CREATED).
                        body(Map.of("message", "Pedido cadastrado com sucesso!"));
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                        body(Map.of("message", "Erro ao cadastrar pedido"));
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(Map.of("message", "Erro ao cadastrar pedido"));
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletarPedido (@RequestBody Long id){

        try {
            boolean pedidoExist = pedidosService.validarId(id);

            if (pedidoExist){
                boolean pedidoDeletado = pedidosService.deletarPedido(id);

                if (pedidoDeletado){
                    return ResponseEntity.status(HttpStatus.ACCEPTED).
                            body(Map.of("message", "Pedido deletado com sucesso!"));
                }
                else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                            body(Map.of("message", "Erro ao deletar pedido"));
                }
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("message", "Esse pedido não existe mais"));
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(Map.of("message", "Erro inesperado"));
        }
    }
}
