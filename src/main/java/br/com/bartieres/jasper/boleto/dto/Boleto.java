package br.com.bartieres.jasper.boleto.dto;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;

@Data
public class Boleto {

	protected BufferedImage logoEmpresa;

	private String linhaDigitavel;
	private String instrucoesBoleto;

	private Banco banco = new Banco();
	private Beneficiario beneficiario = new Beneficiario();
	private Pagador pagador = new Pagador();
	private Datas datas = new Datas();

	protected BigDecimal valorCobrado = BigDecimal.ZERO;
	protected BigDecimal valorBoleto = BigDecimal.ZERO;
	protected BigDecimal quantidadeMoeda = BigDecimal.ZERO;
	protected BigDecimal valorMoeda;
	protected BigDecimal valorDescontos = BigDecimal.ZERO;
	protected BigDecimal valorDeducoes = BigDecimal.ZERO;
	protected BigDecimal valorMulta = BigDecimal.ZERO;
	protected BigDecimal valorAcrescimos = BigDecimal.ZERO;

	protected String especieMoeda;
	protected int codigoEspecieMoeda;
	protected String especieDocumento;
	protected String numeroDocumento;
	protected String localPagamento = "Pagável em qualquer agência bancaria até o vencimento";
	protected boolean aceite;

	protected String codigoBarras;
	protected BufferedImage qrCode;
}
