package br.com.bartieres.jasper.boleto.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Data
@Builder
public class BoletoReport {

    private String nomeBeneficiario;

    private String logo;

    private String qrCode;

    private String linhaDigitavel;

    private String dtVencimento;

    protected BigDecimal valorBoleto = BigDecimal.ZERO;
    protected BigDecimal quantidadeMoeda;
    protected BigDecimal valorMoeda;
    protected BigDecimal valorDescontos = BigDecimal.ZERO;
    protected BigDecimal valorDeducoes = BigDecimal.ZERO;
    protected BigDecimal valorMulta = BigDecimal.ZERO;
    protected BigDecimal valorAcrescimos = BigDecimal.ZERO;

    protected String especieMoeda;
    protected int codigoEspecieMoeda;
    protected String especieDocumento;
    protected String numeroDocumento;
    protected boolean aceite;
    protected Banco banco;
    protected Datas datas;
    protected Pagador pagador;
    protected Beneficiario beneficiario;
    protected List<String> instrucoes = Collections.emptyList();
    protected List<String> descricoes = Collections.emptyList();
    protected List<String> locaisDePagamento = Collections.emptyList();
}
