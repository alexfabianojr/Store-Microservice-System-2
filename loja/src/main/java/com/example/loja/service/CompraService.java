package com.example.loja.service;

import com.example.loja.client.FornecedorClient;
import com.example.loja.dto.CompraDTO;
import com.example.loja.dto.InfoFornecedorDTO;
import com.example.loja.dto.InfoPedidoDTO;
import com.example.loja.model.Compra;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CompraService {

//    @Autowired
//    private RestTemplate client;

//    @Autowired
//    private DiscoveryClient eurekaClient;

    @Autowired
    private FornecedorClient fornecedorClient;

    private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);

    public Compra realizaCompra(CompraDTO compraDTO) {

//        ResponseEntity<InfoFornecedorDTO> exchange = client.exchange(
//                "http://fornecedor/info/" + compraDTO.getEndereco().getEstado(),
//                HttpMethod.GET, null, InfoFornecedorDTO.class);
//
//        eurekaClient.
//                getInstances("fornecedor")
//                .stream()
//                .forEach(e -> System.out.println(e.getPort()));
//
//        System.out.println(exchange.getBody());

        LOG.info("Buscando estado");

        InfoFornecedorDTO infoPorEstado = fornecedorClient
                .getInfoPorEstado(compraDTO.getEndereco().getEstado());

        System.out.println(infoPorEstado.getEndereco());

        LOG.info("Fazendo o pedido");

        InfoPedidoDTO pedido = fornecedorClient.realizaPedido(compraDTO.getItens());

        return Compra.builder()
                .pedidoId(pedido.getId())
                .tempoDePreparo(pedido.getTempoDePreparo())
                .enderecoDestino(compraDTO.getEndereco().toString())
                .build();

    }
}
