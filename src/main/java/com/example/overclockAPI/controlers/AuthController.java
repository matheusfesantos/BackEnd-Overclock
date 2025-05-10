package com.example.overclockAPI.controlers;

import com.example.overclockAPI.entitys.Usuario;
import com.example.overclockAPI.entitys.dto.AuthDTO;
import com.example.overclockAPI.entitys.dto.RegisterDTO;
import com.example.overclockAPI.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepos;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthDTO authDTO){

        var usernamePassword = new UsernamePasswordAuthenticationToken
                (authDTO.username(), authDTO.senha());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO registerDTO){

        try{
            if (this.usuarioRepos.findByUsername(registerDTO.username()) != null){
                return ResponseEntity.badRequest()
                        .body("Usuario já cadastrado!");
            }
            Usuario usuario = new Usuario();

            usuario.setUsername(registerDTO.username());
            usuario.setSenha("{noop}" + registerDTO.senha());
            usuario.setNome(registerDTO.nome());
            usuario.setEmail(registerDTO.email());
            usuario.setCpf(registerDTO.cpf());
            usuario.setTipo(registerDTO.tipoUsuario());

            this.usuarioRepos.save(usuario);

            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            return ResponseEntity.badRequest()
                    .body("Erro ao cadastrar usuário!");
        }
    }
}
