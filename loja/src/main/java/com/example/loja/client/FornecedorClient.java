package com.example.loja.client;

import com.example.loja.dto.InfoFornecedorDTO;
import com.example.loja.dto.InfoPedidoDTO;
import com.example.loja.dto.ItemDaCompraDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("fornecedor")
public interface FornecedorClient {

    @RequestMapping("/info/{estado}")
    InfoFornecedorDTO getInfoPorEstado(@PathVariable String estado);

    @PostMapping("/pedido")
    InfoPedidoDTO realizaPedido(List<ItemDaCompraDTO> itens);
}
