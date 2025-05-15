package com.example.overclockAPI.controlers;

import com.example.overclockAPI.entitys.Usuarios;
import com.example.overclockAPI.services.endpoints.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/usuarios")
public class UsuariosController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuarios>> getUsuario(){
        List<Usuarios> usuarioEntities = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarioEntities);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Usuarios> getUsuario(@PathVariable Long id){
        ResponseEntity<Usuarios> usuarios = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuarios.getBody());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)//INFORMAR SE FOI SUCEDIDA
    public ResponseEntity<Usuarios> postUsuario(@RequestBody Usuarios usuarios){
        usuarios = usuarioService.salvarUsuario(usuarios);
        return ResponseEntity.ok(usuarios);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, String>> deleteUsuario(@PathVariable Long id){
        boolean deletado = usuarioService.deletarUsuario(id);
        Map<String, String> resposta = new HashMap<>();

        if(deletado){
            resposta.put("message", "Usuario foi deletado com sucesso!");
            return ResponseEntity.ok(resposta);
        }
        else{
            resposta.put("messsage", "Erro ao deletar usuario!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resposta);
        }
    }
}
