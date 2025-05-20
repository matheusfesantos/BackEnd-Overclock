package com.example.overclockAPI.controlers;

import com.example.overclockAPI.entitys.Usuarios;
import com.example.overclockAPI.infra.security.TokenService;
import com.example.overclockAPI.services.endpoints.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
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

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<List<Usuarios>> getUsuario(){
        List<Usuarios> usuarioEntities = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarioEntities);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getUsuarioAutenticado(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");

        try{
            if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("message", "Formato do token inválido!"));
            }

            String token = authHeader.substring(7);
            String username = tokenService.extrairUsername(token);

            Usuarios usuario = usuarioService.usarDetails(username);
            return ResponseEntity.ok(usuario);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body
                    (Map.of("message","Um erro inesperado aconteceu!"));
        }
    }
}
