package com.example.overclockAPI.controlers;

import com.example.overclockAPI.entitys.Usuario;
import com.example.overclockAPI.exception.ValorNuloException;
import com.example.overclockAPI.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /*
    @GetMapping({"id"})
    public ResponseEntity<Usuario> getUsuarioById(@RequestBody Usuario id){
    }
     */

    @PostMapping("/adicionar")
    @ResponseStatus(HttpStatus.ACCEPTED)//INFORMAR SE FOI SUCEDIDA
    public ResponseEntity<Usuario> postUsuario(@RequestBody Usuario usuario){
        usuario = usuarioService.salvarUsuario(usuario);
        return ResponseEntity.ok(usuario);
    }

   /*
    @DeleteMapping("/delete")
    public ResponseEntity<Usuario> deleteUsuario(@RequestBody Usuario usuario){

    }
    */
}
