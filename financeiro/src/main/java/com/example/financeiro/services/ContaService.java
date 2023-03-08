package com.example.financeiro.services;

import com.example.financeiro.entity.Conta;
import com.example.financeiro.entity.Produto;
import com.example.financeiro.repositories.ContaRepository;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Service;
import com.example.financeiro.entity.Compra;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepository contaRepository;

    public Conta execute(Conta conta){
        Conta savedConta = contaRepository.save(conta);
        List<Produto> produtos = savedConta.getIdCompra().getProdutosCompradosList();
        var valorCompra = produtos.stream()
                .reduce(0.0, (valorTotal, produto) -> valorTotal + produto.getPreco(), Double::sum);
        savedConta.setValorCompra(valorCompra);

        return savedConta;
    }
}