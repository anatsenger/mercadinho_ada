package com.example.financeiro.Consumer;

import com.ada.mercado.Compra;
import com.example.financeiro.DTOs.ContaDTO;
import com.example.financeiro.entity.Produto;
import com.example.financeiro.repositories.ContaRepository;
import com.example.financeiro.repositories.ProdutoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
@AllArgsConstructor
@KafkaListener(topics = "${topic.name}")
public class ContaConsumer {
    private final ContaRepository contaRepository;
    private  final ProdutoRepository produtoRepository;
    @KafkaHandler
    public void consumerCompra(Compra compra, Acknowledgment ack){
        log.info("Conta: " + compra);
        List<Produto> produtosList = null;
        compra.getProdutosCompradosList()
                .forEach(produto -> produtosList.add(produtoRepository.findByNome(produto.toString())));
        var contaDTO = ContaDTO.builder().build();
        contaDTO.setIdConta(UUID.randomUUID().toString());
        contaDTO.setIdCompra(compra.getIdCompra().toString());
        contaDTO.setValorCompra(produtosList.stream()
                .reduce(0.0, (valorTotal, produto)-> valorTotal + produto.getPreco(), Double::sum));
        contaRepository.save(contaDTO.toEntity());
        ack.acknowledge();
    }

}
