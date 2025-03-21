package com.example.overclockAPI.repository;

import com.example.overclockAPI.entitys.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{}
