package com.example.financeiro.repositories;

import com.example.financeiro.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, String> {
    Compra findByIdCompra(String id);
}

