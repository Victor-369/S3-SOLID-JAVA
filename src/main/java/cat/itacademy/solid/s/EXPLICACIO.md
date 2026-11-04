# Anàlisi

## Descripció
Donada una factura, prepara el format, tant de la capçalera com les línies i calcula el total. Per acabar guardant-ho en
un fitxer txt en una ruta específica.

## Responsabilitats
A continuació es mostra el codi original. 

**Guardar en fitxer**
``` Java
try {
    Files.writeString(destination, text.toString());
} catch (IOException exception) {
    throw new UncheckedIOException(
        "Could not save invoice: " + invoice.number(),
        exception
    );
}
```

**Preparació del format de la factura (capçalera)**
``` Java
String lineSeparator = System.lineSeparator();
StringBuilder text = new StringBuilder();
text.append("INVOICE").append(lineSeparator);
text.append("Number: ").append(invoice.number()).append(lineSeparator);
text.append("Customer: ").append(invoice.customerName()).append(lineSeparator);
text.append(lineSeparator);
```

**Preparació del format de la factura (línies) i el càlcul per línia i el total**
``` Java
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
```

## Explicació
La separació de responsabilitats facilita el manteniment i millora del codi, sense alterar la resta de funcionalitats de les diferents classes.
A més, evita barrejar les diferents finalitats/responsabilitats que tenia el codi original.

**InvoiceFormatPrinter**  
Té la responsabilitat de formatejar la factura, tant la capçalera com les línies. Es mantenen juntes per formar part de la factura com a tal i centrar-se en poder canviar el format visual 
i distribució de les dades de la factura.

**InvoiceCalculator**  
S'encarrega de fer els càlculs del total per línia i el total final de la factura. És una classe a part per facilitar els canvis que pugui haver-hi sobre els càlculs, 
ja siguin millores com ampliacions amb altres càlculs que pugui necessitar com per exemple l'IVA.

**saveToFile**  
Executa mètodes d'altres classes per recollir la informació per generar la factura. La seva principal responsabilitat és guardar el fitxer, no implementar el format de la factura o fer els càlculs.
Qualsevol canvi que sigui necessari per guardar el fitxer, com canviar el seu format o codificació, es podrà fer en aquest mètode evitant modificar qualsevol responsabilitat que no sigui sobre guardar el fitxer.
S'ha canviat "text" per "invoiceFullFormatted" per facilitar la seva comprensió.

A continuació es mostra el codi modificat amb les noves classes.

**Nova classe per formatejar les factures**
``` Java
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
        for (InvoiceLine line : invoice.lines()) {
            BigDecimal totalAmountOfInvoiceLine = InvoiceCalculator.totalAmountOfLine(line.unitPrice(), line.quantity());
            text.append(line.description())
                    .append(" - ").append(line.quantity())
                    .append(" x ").append(line.unitPrice())
                    .append(" = ").append(totalAmountOfInvoiceLine)
                    .append(lineSeparator);
        }

        BigDecimal totalAmountOfInvoice = InvoiceCalculator.totalAmountOfInvoice(invoice);
        text.append(lineSeparator).append("TOTAL: ").append(totalAmountOfInvoice);

        return text;
    }
}
```

**Nova classe per calcular les factures**
``` Java
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
```

**Format final de SaveToFile**
``` Java 
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
```

S'han afegit els tests `shouldGetOnlyInvoiceHeaderData`, `shouldGetOnlyInvoiceLinesData`, `shouldGetOnlyInvoiceLinesTotalAmount` i `shouldGetOnlyInvoiceTotalAmount` en `InvoiceServiceTest`, 
per comprovar que es mantenen els formats de factura originals, els totals de línies i el total de la factura.