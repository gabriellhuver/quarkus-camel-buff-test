package org.acme.dto;

import java.util.List;

import lombok.Data;

@Data
public class Transaction {
    private double valorTotalVendido;
    private double valorTotalRecebido;
    private double valorTotalLucro;
    private int totalItemsVendidos;
    private List<LogEntry> log;

    // Getters e Setters omitidos para brevidade
}