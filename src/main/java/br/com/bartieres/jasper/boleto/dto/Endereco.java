package br.com.bartieres.jasper.boleto.dto;

import lombok.Data;

@Data
public class Endereco {

	private String logradouro;
	private String bairro;
	private String cep;
	private String cidade;
	private String uf;

	public String getEnderecoCompleto(){
		return "Teste";
	}
}
