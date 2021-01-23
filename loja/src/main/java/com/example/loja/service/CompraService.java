package com.example.loja.service;

import com.example.loja.client.FornecedorClient;
import com.example.loja.client.TransportadorClient;
import com.example.loja.dto.*;
import com.example.loja.enums.CompraState;
import com.example.loja.model.Compra;
import com.example.loja.repository.CompraRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class CompraService {

//    @Autowired
//    private RestTemplate client;

//    @Autowired
//    private DiscoveryClient eurekaClient;

    @Autowired
    private FornecedorClient fornecedorClient;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private TransportadorClient transportadorClient;

    private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);

    @HystrixCommand(threadPoolKey = "realizaCompraThreadPool",
    fallbackMethod = "realizaCompraFallback")
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

        Compra compra = new Compra();

        compra.setState(CompraState.RECEBIDO);
        compraRepository.save(compra);

        compraDTO.setCompraId(compra.getId());

        LOG.info("Buscando estado");

        InfoFornecedorDTO infoPorEstado = fornecedorClient
                .getInfoPorEstado(compraDTO.getEndereco().getEstado());

        LOG.info("Fazendo o pedido");

        InfoPedidoDTO pedido = fornecedorClient.realizaPedido(compraDTO.getItens());

        compra.setPedidoId(pedido.getId());
        compra.setTempoDePreparo(pedido.getTempoDePreparo());
        compra.setState(CompraState.PEDIDO_REALIZADO);
        compra.setEnderecoDestino(compraDTO.getEndereco().toString());
        compraRepository.save(compra);

        EntregaDTO entregaDTO = EntregaDTO.builder()
                .pedidoId(pedido.getId())
                .dataParaEntrega(LocalDate.now().plusDays((long)pedido.getTempoDePreparo()))
                .enderecoOrigem(infoPorEstado.getEndereco())
                .enderecoDestino(compraDTO.getEndereco().toString())
                .build();

        VoucherDTO voucher = transportadorClient.reservaEntrega(entregaDTO);

        compra.setVoucher(voucher.getNumero());
        compra.setDataParaEntrega(voucher.getPrevisaoParaEntrega());
        compra.setState(CompraState.RESERVA_ENTREGA_REALIZADA);
        compraRepository.save(compra);

        return compra;

    }
    
    public Compra realizaCompraFallback(CompraDTO compraDTO) {
        if (compraDTO.getCompraId() != null) {
            return compraRepository
                    .findById(compraDTO.getCompraId())
                    .get();
        }
        return null;
    }

    public void cancelaCompra(Long id) {
        compraRepository.deleteById(id);
        LOG.info("Compra deletada");
    }

    @HystrixCommand(threadPoolKey = "getByIdThreadPool")
    public Compra getById(Long id) throws Exception {
        return compraRepository
                .findById(id)
                .orElseThrow(() -> new Exception("Compra nao encontrada"));
    }
}
