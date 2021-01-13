package com.example.loja.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Compra {
    private Long pedidoId;
    private Integer tempoDePreparo;
    private String enderecoDestino;
}
