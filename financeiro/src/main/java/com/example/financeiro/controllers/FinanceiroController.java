package com.example.financeiro.controllers;


import com.ada.mercado.Conta;
import com.example.financeiro.DTOs.CompraDTO;
import com.example.financeiro.Producer.ContaProducer;
import com.example.financeiro.entity.Produto;
import com.example.financeiro.repositories.ProdutoRepository;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/conta")
@AllArgsConstructor
public class FinanceiroController {
    private final ProdutoRepository produtoRepository;
    private final ContaProducer contaProducer;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> sendMessage(@RequestBody CompraDTO compraDTO){
        var id = UUID.randomUUID().toString();

        List<CharSequence> listNomeProduts = compraDTO.getProdutosCompradosList().stream()
                .map(p->(CharSequence) p).collect(Collectors.toList());

        List<Produto> listProdutos = listNomeProduts.stream()
                .map(produto -> produtoRepository.findByNome(produto.toString())).collect(Collectors.toList());


        var messageConta = Conta.newBuilder().setIdConta(id)
                .setIdCompra(compraDTO.getIdCompra())
                .setValorCompra(listProdutos.stream()
                        .reduce(0.0, (valorTotal, produto)-> valorTotal + produto.getPreco(), Double::sum))
                .build();

        contaProducer.sendMessage(messageConta);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}