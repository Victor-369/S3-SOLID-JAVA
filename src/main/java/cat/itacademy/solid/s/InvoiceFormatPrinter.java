package cat.itacademy.solid.s;

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
}
