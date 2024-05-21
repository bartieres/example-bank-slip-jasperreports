package br.com.bartieres.jasper.boleto.report;

import br.com.bartieres.jasper.boleto.dto.BoletoReport;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ReportGenerator {

    public static Map<String, Object> createMapObjectToReport(BoletoReport report) {
        var map = new HashMap<String, Object>();
        map.put("logo", getImage("logo.png"));
        map.put("qrCode", report.getQrCode());
        map.put("linhaDigitavel", report.getLinhaDigitavel());
        map.put("dtVencimento", report.getDtVencimento());
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

    public static void createReportFile(Map<String, Object> mapReport) {

        String file = "/jasper/report.jasper";

        try {
            Path path = Paths.get("");
            String jasperFilePath = path.toAbsolutePath().toString() + file;

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFilePath, mapReport, new JREmptyDataSource());
            byte[] report = JasperExportManager.exportReportToPdf(jasperPrint);

            writeBytesToFileNio("/relatorios/Relatorio1.pdf", report);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void writeBytesToFileNio(String s, byte[] report) throws IOException {
        Path path = Paths.get(s);
        Files.write(path, report);
    }
}
