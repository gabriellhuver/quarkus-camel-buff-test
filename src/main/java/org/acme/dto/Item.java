package org.acme.dto;

import org.acme.Campo;

import lombok.Data;

@Data
public class Item {

	private String item;

	private double valorVendido;

	private double valorRecebido;

	private int valorComprado;

	private int fee;

	private double lucro;

	private String dataCompra;

	private String dataVenda;

	// Getters e setters
}