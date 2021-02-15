package ua.mieshkov.corplan.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import ua.mieshkov.corplan.model.Tariff;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Slf4j
public class GeneratePdf {

    public static final String FONT = "src/main/resources/assets/fonts/ArialCR.TTF";

    public static ByteArrayInputStream tariffList(List<Tariff> tariffs, Locale locale) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Locale enLocale = new Locale("en", "US");
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
        try {
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(60);
            table.setWidths(new int[]{3, 2});

            BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font headFont = new Font(bf,16,Font.NORMAL);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase((String) bundle.getObject("title.tariff.name"), headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase((String) bundle.getObject("title.tariff.price"), headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for (Tariff tariff : tariffs) {
                PdfPCell cell;

                if(locale.equals(enLocale)) {
                    cell = new PdfPCell(new Phrase(tariff.getNameEn(), headFont));
                } else {
                    cell = new PdfPCell(new Phrase(tariff.getNameUkr(), headFont));
                }
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(tariff.getPrice().toString()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);

            document.close();

        } catch (DocumentException | IOException ex) {
            log.error("Error occurred: {0}", ex);
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
