package com.example.loja.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraDTO {
    @JsonIgnore
    private Long compraId;
    private List<ItemDaCompraDTO> itens;
    private EnderecoDTO endereco;
}
