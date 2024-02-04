package com.kafka.estudokafka.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Venda {

    private Long operacao;
    private Long cliente;
    private Integer quantidadeIngressos;
    private BigDecimal valorTotal;
    private String status;

    public Venda(Long operacao, Long cliente, Integer quantidadeIngressos, BigDecimal valorTotal) {
        this.operacao = operacao;
        this.cliente = cliente;
        this.quantidadeIngressos = quantidadeIngressos;
        this.valorTotal = valorTotal;
    }
}
