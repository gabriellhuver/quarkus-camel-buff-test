package org.acme.dto;

import lombok.Data;

@Data
public class LogEntry {
    private String item;
    private double valorVendido;
    private double valorRecebido;
    private double valorComprado;
    private double lucro;
    private String dataVenda;

    // Getters e Setters omitidos para brevidade
}