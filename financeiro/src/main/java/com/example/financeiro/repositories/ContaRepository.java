package com.example.financeiro.repositories;

import com.example.financeiro.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    Optional<Conta> findByIdentificador(String identificador);
}