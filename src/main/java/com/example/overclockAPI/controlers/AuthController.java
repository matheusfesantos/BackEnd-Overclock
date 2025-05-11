package com.example.overclockAPI.controlers;

import com.example.overclockAPI.entitys.Usuario;
import com.example.overclockAPI.entitys.dto.AuthDTO;
import com.example.overclockAPI.entitys.dto.LoginReespondeDTO;
import com.example.overclockAPI.entitys.dto.RegisterDTO;
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

            var token = tokenService.generateToken((Usuario) auth.getPrincipal());

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

            Usuario usuario = new Usuario();

            usuario.setUsername(registerDTO.username());
            usuario.setSenha("{noop}" + registerDTO.senha());
            usuario.setNome(registerDTO.nome());
            usuario.setEmail(registerDTO.email());
            usuario.setCpf(registerDTO.cpf());
            usuario.setTipo(registerDTO.tipoUsuario());

            this.usuarioRepos.save(usuario);

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
