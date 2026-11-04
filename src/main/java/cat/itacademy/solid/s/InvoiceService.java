package cat.itacademy.solid.s;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;

public class InvoiceService {

    public void saveToFile(Invoice invoice, Path destination) {
        StringBuilder invoiceHeaderFormatted = InvoiceFormatPrinter.getFormatHeader(invoice);
        StringBuilder invoiceLinesFormatted = InvoiceFormatPrinter.getFormatLines(invoice);
        StringBuilder invoiceFullFormatted = new StringBuilder();
        invoiceFullFormatted.append(invoiceHeaderFormatted);
        invoiceFullFormatted.append(invoiceLinesFormatted);

        try {
            Files.writeString(destination, invoiceFullFormatted.toString());
        } catch (IOException exception) {
            throw new UncheckedIOException(
                    "Could not save invoice: " + invoice.number(),
                    exception
            );
        }
    }

}
