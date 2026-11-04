package cat.itacademy.solid.s;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;

public class InvoiceService {

    public void saveToFile(Invoice invoice, Path destination) {
        StringBuilder text = InvoiceFormatPrinter.getFormatHeader(invoice);

        String lineSeparator = System.lineSeparator();
        BigDecimal total = new BigDecimal("0.00");
        for (InvoiceLine line : invoice.lines()) {
            BigDecimal lineTotal = line.unitPrice()
                    .multiply(BigDecimal.valueOf(line.quantity()));
            total = total.add(lineTotal);

            text.append(line.description())
                    .append(" - ").append(line.quantity())
                    .append(" x ").append(line.unitPrice())
                    .append(" = ").append(lineTotal)
                    .append(lineSeparator);
        }

        text.append(lineSeparator).append("TOTAL: ").append(total);

        try {
            Files.writeString(destination, text.toString());
        } catch (IOException exception) {
            throw new UncheckedIOException(
                    "Could not save invoice: " + invoice.number(),
                    exception
            );
        }
    }

}
