package br.com.bartieres.jasper.boleto.service.impl;

import br.com.bartieres.jasper.boleto.dto.BoletoReport;
import br.com.bartieres.jasper.boleto.report.ReportGenerator;
import br.com.bartieres.jasper.boleto.service.BoletoService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class BoletoServiceImpl implements BoletoService {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void generate() {
        var boleto = BoletoReport.builder()
                .dtVencimento(formatter.format(LocalDate.now().plusDays(2)))
                .qrCode("")
                .linhaDigitavel("")
                .build();

        var map = ReportGenerator.createMapObjectToReport(boleto);
        ReportGenerator.createReportFile(map);
    }
}
