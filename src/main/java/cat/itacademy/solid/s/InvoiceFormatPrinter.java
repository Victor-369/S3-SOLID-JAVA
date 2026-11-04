package cat.itacademy.solid.s;

import java.math.BigDecimal;

public class InvoiceFormatPrinter {
    private InvoiceFormatPrinter() {}

    public static StringBuilder getFormatHeader(Invoice invoice) {
        String lineSeparator = System.lineSeparator();
        StringBuilder text = new StringBuilder();
        text.append("INVOICE").append(lineSeparator);
        text.append("Number: ").append(invoice.number()).append(lineSeparator);
        text.append("Customer: ").append(invoice.customerName()).append(lineSeparator);
        text.append(lineSeparator);

        return text;
    }

    public static StringBuilder getFormatLines(Invoice invoice) {
        String lineSeparator = System.lineSeparator();
        StringBuilder text = new StringBuilder();
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

        return text;
    }
}
