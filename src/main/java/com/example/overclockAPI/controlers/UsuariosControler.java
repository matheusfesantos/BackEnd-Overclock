package com.example.overclockAPI.controlers;

import com.example.overclockAPI.entitys.Usuario;
import com.example.overclockAPI.exception.ValorNuloException;
import com.example.overclockAPI.repository.UsuarioRepository;
import com.example.overclockAPI.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/usuarios")
public class UsuariosControler {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuario(){
        List<Usuario> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Usuario> getUsuario(@PathVariable Long id){
        ResponseEntity<Usuario> usuarios = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuarios.getBody());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)//INFORMAR SE FOI SUCEDIDA
    public ResponseEntity<Usuario> postUsuario(@RequestBody Usuario usuario){
        usuario = usuarioService.salvarUsuario(usuario);
        return ResponseEntity.ok(usuario);
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
