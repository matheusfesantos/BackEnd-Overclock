package com.example.overclockAPI.repository;

import com.example.overclockAPI.entitys.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{

    UserDetails findByUsername(String username);
}
