package com.example.mercado.configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
public class CompraDTO {
    private String idCompra;
    private String cpf;
    private List<String> produtosCompradosList;

}