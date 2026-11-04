package cat.itacademy.solid.s;

import java.math.BigDecimal;

public class InvoiceCalculator {
    private InvoiceCalculator() {}

    public static BigDecimal totalAmountOfLine(BigDecimal unitPrice, int quantity) {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public static BigDecimal totalAmountOfInvoice(Invoice invoice) {
        BigDecimal total = new BigDecimal("0.00");
        for (InvoiceLine line : invoice.lines()) {
            total = total.add(totalAmountOfLine(line.unitPrice(), line.quantity()));
        }

        return total;
    }
}
