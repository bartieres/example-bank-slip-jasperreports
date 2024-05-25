package br.com.bartieres.jasper.boleto.report;

import br.com.bartieres.jasper.boleto.dto.Boleto;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class ReportGenerator {

    public static void createReportFile(Boleto report) {

        String file = "/src/main/resources/jasper/boleto.jasper";

        try {
            Path path = Paths.get("");
            String jasperFilePath = path.toAbsolutePath() + file;

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Arrays.asList(report));
            var jasperPrint = JasperFillManager.fillReport(jasperFilePath, null, dataSource);

            byte[] reportByte = JasperExportManager.exportReportToPdf(jasperPrint);

            writeBytesToFileNio("/src/main/resources/relatorios/Relatorio.pdf", reportByte);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void writeBytesToFileNio(String s, byte[] report) throws IOException {
        Path path = Paths.get("");
        String jasperFilePath = path.toAbsolutePath() + s;
        Path path2 = Paths.get(jasperFilePath);
        Files.write(path2, report);
    }

    public static BufferedImage generateQRCodeImage(String text, int width, int height) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
            return MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getLocalizedMessage());
        }
    }

    public static BufferedImage loadImage(String path) {
        try (InputStream inputStream = ReportGenerator.class.getResourceAsStream(path)) {
            if (inputStream == null) {
                throw new IOException("Recurso não encontrado: " + path);
            }
            return ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
