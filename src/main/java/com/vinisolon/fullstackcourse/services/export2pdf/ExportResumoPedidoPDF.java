package com.vinisolon.fullstackcourse.services.export2pdf;

import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.vinisolon.fullstackcourse.dto.ProdutoResumoPedidoDTO;
import com.vinisolon.fullstackcourse.dto.ResumoPedidoDTO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Locale;

@Service
public class ExportResumoPedidoPDF {

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private final NumberFormat df = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    private void setResponseHeader(HttpServletResponse response, String fileName) {
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + fileName;
        response.setHeader(headerKey, headerValue);
    }

    private Font getFontInstance(String fontName, int size, Color color) {
        Font font = FontFactory.getFont(fontName);
        font.setSize(size);
        font.setColor(color);
        return font;
    }

    private void setHeaderImage(Document document) throws IOException {
        Image logo = Image.getInstance("./images/logo-tenda-pedido-pdf.jpg");
        logo.setAlignment(Element.ALIGN_CENTER);
        document.add(logo);
    }

    private void setHeaderTable(Document document, ResumoPedidoDTO peditoToPdf) {
        PdfPTable table = new PdfPTable(3);
        table.setSpacingBefore(15);
        table.setWidthPercentage(100f);

        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(10);

        Font font = getFontInstance(FontFactory.HELVETICA_BOLD, 18, Color.BLACK);

        cell.setColspan(3);

        cell.setPhrase(new Phrase(peditoToPdf.getNomeCliente(), font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("CNPJ: " + peditoToPdf.getDocumentoCliente(), font));
        table.addCell(cell);

        font = getFontInstance(FontFactory.HELVETICA_BOLD, 14, Color.BLACK);

        cell.setColspan(0);

        cell.setPhrase(new Phrase("Pedido: " + peditoToPdf.getNumeroPedido(), font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Data: " + sdf.format(peditoToPdf.getDataRealizacaoPedido()), font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Total: " + df.format(peditoToPdf.getValorTotalPedido()), font));
        table.addCell(cell);

        document.add(table);
    }

    private void setContentTable(Document document, ResumoPedidoDTO pedidoToPdf) {
        PdfPTable table = new PdfPTable(3);
        table.setSpacingBefore(15);
        table.setWidthPercentage(100f);

        Paragraph paragraph = new Paragraph("Lista de produtos", getFontInstance(FontFactory.HELVETICA_BOLD, 16, Color.BLACK));
        paragraph.setSpacingBefore(15);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);

        PdfPCell cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5);

        Font font = getFontInstance(FontFactory.HELVETICA, 12, Color.BLACK);

        for (ProdutoResumoPedidoDTO produto : pedidoToPdf.getProdutosPedido()) {
            cell.setPhrase(new Phrase(produto.getNomeProduto(), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(produto.getQuantidadeProduto().toString() + (produto.getQuantidadeProduto() > 1 ? " itens" : " item"), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(df.format(produto.getPrecoProduto()) + " un.", font));
            table.addCell(cell);
        }

        document.add(table);
    }

    public void export(ResumoPedidoDTO pedidoToPdf, HttpServletResponse response) {
        try (Document document = new Document(PageSize.A4)) {
            setResponseHeader(response, pedidoToPdf.getNumeroPedido().toString() + ".pdf");
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            setHeaderImage(document);
            setHeaderTable(document, pedidoToPdf);
            setContentTable(document, pedidoToPdf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportBase64(ResumoPedidoDTO pedidoToPdf, HttpServletResponse response) {
        try (Document document = new Document(PageSize.A4)) {
            PdfWriter.getInstance(document, Base64.getEncoder().wrap(response.getOutputStream()));
            document.open();
            setHeaderImage(document);
            setHeaderTable(document, pedidoToPdf);
            setContentTable(document, pedidoToPdf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
