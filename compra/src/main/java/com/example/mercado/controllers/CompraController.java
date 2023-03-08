package com.example.mercado.controllers;

import com.ada.mercado.Compra;
import com.example.mercado.configuration.CompraDTO;
import com.example.mercado.producer.CompraProducer;
import com.example.mercado.services.CreateCompraService;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/Compra")
@AllArgsConstructor
public class CompraController {
    private final CompraProducer compraProducer;
    private final CreateCompraService createCompraService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> sendMessage(@RequestBody CompraDTO compraDTO){
        var id = UUID.randomUUID().toString();

        var message = Compra.newBuilder().setIdCompra(id).setCpf(compraDTO.getCpf())
                .setProdutosCompradosList(compraDTO.getProdutosCompradosList().stream().map(p -> (CharSequence) p).collect(Collectors.toList()))
                .build();

        compraProducer.sendMessage(message);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.example.mercado.entity.Compra> findById(@PathVariable String id){
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID não informado!");
        }
        com.example.mercado.entity.Compra compra = createCompraService.getById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possivel localizar uma compra com o id informado!"));
        return ResponseEntity.ok(compra);
    }
}