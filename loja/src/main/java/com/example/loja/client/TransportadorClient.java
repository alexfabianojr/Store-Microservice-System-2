package com.example.loja.client;

import com.example.loja.dto.EntregaDTO;
import com.example.loja.dto.VoucherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("transportador")
public interface TransportadorClient {

    @PostMapping("/entrega")
    public VoucherDTO reservaEntrega(@RequestBody EntregaDTO pedidoDTO);
}
