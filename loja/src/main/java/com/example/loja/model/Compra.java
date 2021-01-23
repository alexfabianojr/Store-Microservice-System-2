package com.example.loja.model;

import com.example.loja.enums.CompraState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pedidoId;
    private Integer tempoDePreparo;
    private String enderecoDestino;
    private LocalDate dataParaEntrega;
    private Long voucher;
    @Enumerated(EnumType.STRING)
    private CompraState state;
}
