package br.com.bartieres.jasper.boleto.dto;

import lombok.Data;

@Data
public class Beneficiario {

	private String agencia;
	private String digitoAgencia;

	private String codigoBeneficiario;
	private String digitoCodigoBeneficiario;

	private String carteira;
	private String nossoNumero;
	private String digitoNossoNumero;
	
	private String nomeBeneficiario;
	private String documento;
	private Endereco endereco = new Endereco();

	private String numeroConvenio;
	private String agenciaCodigo;
}
