package br.com.bartieres.jasper.boleto.service.impl;

import br.com.bartieres.jasper.boleto.dto.Boleto;
import br.com.bartieres.jasper.boleto.report.ReportGenerator;
import br.com.bartieres.jasper.boleto.service.BoletoService;
import org.springframework.stereotype.Service;

@Service
public class BoletoServiceImpl implements BoletoService {

    @Override
    public void generate() {
        Boleto boleto = new Boleto();
        boleto.setLogoEmpresa(ReportGenerator.loadImage("/images/logo.png"));
        boleto.getBanco().setImage(ReportGenerator.loadImage("/images/logo_banco.png"));

        boleto.setLinhaDigitavel("01010.10202 02030.30304.04040 5 0505060606070707");
        String codigo = "01010102020203030304040405050506060607070708080";
        boleto.setCodigoBarras(convertCodigoBarras(codigo));
        boleto.setQrCode(ReportGenerator.generateQRCodeImage("MinhaChavePix", 90, 90));

        boleto.setInstrucoesBoleto("ESTE BOLETO NÃO QUITA E/OU DECLARA O IMÓVEL ISENTO DE DÉBITOS ANTERIORES\n" +
                "NÃO RECEBER APÓS 10 DIAS DO VENCIMENTO.\n" +
                "APÓS VENCIMENTO, COBRAR MULTA DE 3.0% MAIS JUROS DE 0.033% AO DIA.\n" +
                "APÓS 10 DIAS DE VENCIDO, ACRESCIDO DE CORREÇÃO E ENCARGOS DE COBRANÇA.\n" +
                "ENTRAR EM CONTATO COM A EMPRESA POR TELEFONE: 0800-000 OU POR E-MAIL: EMPRESA@EMPRESA.COM.BR\n" +
                "Não receber pagamento em cheque.");
        ReportGenerator.createReportFile(boleto);
    }

    private static String convertCodigoBarras(String code) {
        return code.substring(0, 4)
                + code.substring(32, code.length())
                + code.substring(4, 9)
                + code.substring(10, 20)
                + code.substring(21, 31);
    }
}
