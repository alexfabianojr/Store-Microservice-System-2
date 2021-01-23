package br.com.alura.microservice.transportador.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoucherDTO {
	private Long numero;
	private LocalDate previsaoParaEntrega;
}
