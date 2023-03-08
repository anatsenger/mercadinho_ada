package com.example.financeiro.repositories;

import com.example.financeiro.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, String> {
    Produto findByNome(String nome);
}
