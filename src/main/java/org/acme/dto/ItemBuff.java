package org.acme.dto;

import org.acme.Campo;

import lombok.Data;

@Data
public class ItemBuff {

	@Campo(indiceDoCampo = 1, tamanhoMaximo = 100)
	private String item;

	@Campo(indiceDoCampo = 2, tamanhoMaximo = 10)
	private double valorVendido;

	@Campo(indiceDoCampo = 3, tamanhoMaximo = 10)
	private double valorRecebido;

	@Campo(indiceDoCampo = 4, tamanhoMaximo = 10)
	private int valorComprado;

	// Getters e setters
}