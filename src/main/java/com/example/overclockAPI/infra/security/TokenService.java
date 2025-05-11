package com.example.overclockAPI.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.overclockAPI.entitys.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    @Value("${api.security.token.security}")
    private String secret;

    public String generateToken(Usuario usuario){

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("OverclockAPI")
                    .withSubject(usuario.getUsername()) //Username é base da Criptografia
                    .sign(algorithm);
            // SEM EXPIRAÇÃO DE TOKEN

            return token;
        }
        catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar token JWT");
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("OverclockAPI")
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch (JWTVerificationException e){
            return "";
        }
    }

    private Instant getExpirationDate(){
        return Instant.now().plusSeconds(1800);
    }//Usar quando aplicar a expiração de TOKEN
}
