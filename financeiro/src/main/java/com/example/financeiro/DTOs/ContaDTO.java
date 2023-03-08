package com.example.financeiro.DTOs;

import com.example.financeiro.entity.Compra;
import com.example.financeiro.entity.Conta;
import com.example.financeiro.repositories.CompraRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ContaDTO {
    private String idConta;

    private String idCompra;
    private double valorCompra;

    public Conta toEntity(){
        CompraRepository compraRepository = null;
        Conta entity = new Conta();
        entity.setIdConta(idConta);
        entity.setIdCompra(compraRepository.findByIdCompra(idCompra));
        entity.setValorCompra(valorCompra);
        return entity;
    }
}
