package com.example.financeiro.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Compra")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String idCompra;
    @CreatedDate
    private LocalDateTime dataCompra;
    private String cpf;
    @OneToMany(mappedBy = "nome")
    private List<Produto> produtosCompradosList;
}