package com.example.overclockAPI.repository;

import com.example.overclockAPI.entitys.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuarios,Long>{

    UserDetails findByUsername(String username);
    Usuarios findByEmail(String email);
}
