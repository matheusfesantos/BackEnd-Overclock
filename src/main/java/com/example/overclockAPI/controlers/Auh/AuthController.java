package com.example.overclockAPI.controlers.Auh;

import com.example.overclockAPI.entitys.Usuarios;
import com.example.overclockAPI.dto.security.AuthDTO;
import com.example.overclockAPI.dto.security.LoginReespondeDTO;
import com.example.overclockAPI.dto.security.RegisterDTO;
import com.example.overclockAPI.infra.security.TokenService;
import com.example.overclockAPI.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepos;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated AuthDTO authDTO){

        try{
            var usernamePassword = new UsernamePasswordAuthenticationToken
                    (authDTO.username(), authDTO.senha());

            var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = tokenService.generateToken((Usuarios) auth.getPrincipal());

            return ResponseEntity.ok(new LoginReespondeDTO(token));
        }
        catch (Exception e){
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Map.of("message", "Você não possui cadastro!"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Validated RegisterDTO registerDTO){

        try{
            if
            (this.usuarioRepos.findByUsername(registerDTO.username()) != null){
                return ResponseEntity.badRequest()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Map.of("message", "Usuário já cadastrado!"));
            }

            if
            (this.usuarioRepos.findByEmail(registerDTO.email()) != null){
                return ResponseEntity.badRequest()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Map.of("message", "Email já cadastrado!"));
            }

            Usuarios usuarios = new Usuarios();

            usuarios.setUsername(registerDTO.username());
            usuarios.setSenha("{noop}" + registerDTO.senha());
            usuarios.setNome(registerDTO.nome());
            usuarios.setEmail(registerDTO.email());
            usuarios.setCpf(registerDTO.cpf());
            usuarios.setTipo(registerDTO.tipoUsuario());

            this.usuarioRepos.save(usuarios);

            return ResponseEntity.ok()
                    .body(Map.of("message", "Usuário cadastrado com sucesso!"));
        }
        catch (Exception e){
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Map.of("message", "Erro ao cadastrar usuário!"));
        }
    }
}
