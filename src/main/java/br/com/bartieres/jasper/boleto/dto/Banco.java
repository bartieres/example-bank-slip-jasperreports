package br.com.bartieres.jasper.boleto.dto;

import lombok.Data;

import java.awt.image.BufferedImage;

@Data
public class Banco {

	protected BufferedImage image;

	private String numeroFormatadoComDigito = "111-1";

	private String numeroFormatado;

	private String geraCodigoDeBarrasPara;

	private String codigoBeneficiarioFormatado;

	private String carteiraFormatado;

	private String nossoNumeroFormatado;

	private String agenciaECodigoBeneficiario;

	private String nossoNumeroECodigoDocumento;

}
