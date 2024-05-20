package org.acme.dto;

import org.acme.Campo;

import lombok.Data;

@Data
public class ItemBuff {

	private String fluxo = "MFL001";
	
	@Campo(indiceDoCampo = 1, tamanhoMaximo = 100)
	private String item;

	@Campo(indiceDoCampo = 2, tamanhoMaximo = 10)
	private Double valorVendido;

	@Campo(indiceDoCampo = 3, tamanhoMaximo = 10)
	private Double valorRecebido;

	@Campo(indiceDoCampo = 4, tamanhoMaximo = 10)
	private Double valorComprado;

	// Getters e setters
}