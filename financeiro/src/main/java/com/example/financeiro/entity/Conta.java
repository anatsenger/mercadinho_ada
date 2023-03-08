package com.example.financeiro.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Conta")
public class Conta {
    @Id
    private String idConta;
    @OneToOne(mappedBy = "idCompra")
    private Compra idCompra;
    private double valorCompra;


}
