package com.example.overclockAPI.repository;

import com.example.overclockAPI.entitys.Compras;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ComprasRepository extends JpaRepository<Compras, Long> {


}
