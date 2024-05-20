package org.acme.dto;

import org.acme.Campo;

import lombok.Data;

@Data
public class Item {

	private String item;

	private Double valorVendido;

	private Double valorRecebido;

	private Double valorComprado;

	private Double fee;

	private Double lucro;

	private String dataCompra;

	private String dataVenda;

	// Getters e setters
}