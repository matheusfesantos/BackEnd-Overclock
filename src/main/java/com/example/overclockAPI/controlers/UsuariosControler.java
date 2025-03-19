package com.example.overclockAPI.controlers;

import com.example.overclockAPI.entitys.Usuario;
import com.example.overclockAPI.exception.ValorNuloException;
import com.example.overclockAPI.repository.UsuarioRepository;
import com.example.overclockAPI.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuarios")
public class UsuariosControler {

    private final UsuarioService usuarioService;

    private final UsuarioRepository userRepos;

    public UsuariosControler(UsuarioService usuarioService, UsuarioRepository userRepos) {
        this.usuarioService = usuarioService;
        this.userRepos = userRepos;
    }

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
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        if (userRepos.existsById(id)) {
            userRepos.deleteById(id);
        }
        else{
            return ResponseEntity.notFound().build();
        }
        return null;
    }
}
