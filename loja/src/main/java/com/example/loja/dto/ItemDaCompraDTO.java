package com.example.loja.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDaCompraDTO {

    private Long id;
    private int quantidade;
}
