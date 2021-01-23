package com.example.loja.controller;

import com.example.loja.dto.CompraDTO;
import com.example.loja.model.Compra;
import com.example.loja.service.CompraService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compra")
@AllArgsConstructor
public class CompraController {

    private final CompraService compraService;

    @PostMapping
    public Compra realizaCompra(@RequestBody CompraDTO compraDTO) {
        return compraService.realizaCompra(compraDTO);
    }

    @GetMapping("/{id}")
    public Compra getById(@PathVariable("id") Long id) {
        try {
            return compraService.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
