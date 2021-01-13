package com.example.loja.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraDTO {

    private List<ItemDaCompraDTO> itens;
    private EnderecoDTO endereco;
}
