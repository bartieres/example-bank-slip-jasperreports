package br.com.bartieres.jasper.boleto.dto;

import lombok.Data;

@Data
public class Pagador {

    private String nome = "";
    private String documento = "";
    private Endereco endereco = new Endereco();
}
