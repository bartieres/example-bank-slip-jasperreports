package br.com.bartieres.jasper.boleto.report;

import br.com.bartieres.jasper.boleto.dto.Boleto;
import br.com.bartieres.jasper.boleto.dto.BoletoReport;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ReportGenerator {

    public static Map<String, Object> createMapObjectToReport(BoletoReport report) {
        var map = new HashMap<String, Object>();
        //map.put("logo", getImage("logo.png"));
        map.put("beneficiario.nomeBeneficiario", "Andre Bartieres");
        map.put("qrCode", "00020101021226980014br.gov.bcb.pix2576api-h.developer.btgpactual.com/pc/p/v2/cobv/e97abbcd057a4d79ac8d8ff52492023b5204000053039865802BR5924LLZ SOLUCAO COBRANCA S.A6014BELO HORIZONTE61083010000062070503***63049FBA");
        map.put("numeroFormatadoComDigito", "208-1");
        //map.put("linhaDigitavel", report.getLinhaDigitavel());
        map.put("dtVencimento", report.getDtVencimento());
        map.put("codigoDeBarras", "20890001091000000049681050767508197380000010000");
        map.put("linhaDigitavel", "20890001091000000049681050767508197380000010000");
        map.put("boleto", report);
        return map;
    }

    private static Object getImage(String s) {

        Image image = null;
        InputStream stream;
        try {
            var url = ReportGenerator.class.getClassLoader().getResource("images");
            File initialFile = new File(url.getPath().concat("/").concat(s));
            stream = new FileInputStream(initialFile);
            byte[] bytesImage = stream.readAllBytes();
            ImageIcon imageIcon = new ImageIcon(bytesImage);
            image = imageIcon.getImage();
            stream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  image;
    }

    public static void createReportFile(Boleto report) {

        String file = "/src/main/resources/jasper/boleto-default.jasper";

        try {
            Path path = Paths.get("");
            String jasperFilePath = path.toAbsolutePath().toString() + file;

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Arrays.asList(report));
            var jasperPrint = JasperFillManager.fillReport(jasperFilePath, null, dataSource);

            //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFilePath, mapReport, new JREmptyDataSource());
            byte[] reportByte = JasperExportManager.exportReportToPdf(jasperPrint);

            writeBytesToFileNio("/src/main/resources/relatorios/Relatorio1.pdf", reportByte);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void writeBytesToFileNio(String s, byte[] report) throws IOException {
        Path path = Paths.get("");
        String jasperFilePath = path.toAbsolutePath().toString() + s;
        Path path2 = Paths.get(jasperFilePath);
        Files.write(path2, report);
    }
}
