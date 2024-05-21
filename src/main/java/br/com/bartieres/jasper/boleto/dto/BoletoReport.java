package br.com.bartieres.jasper.boleto.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoletoReport {

    private String logo;

    private String qrCode;

    private String linhaDigitavel;

    private String dtVencimento;
}
