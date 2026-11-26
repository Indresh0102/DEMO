package designpatterns.factory.abstractfactory.printer;

import designpatterns.factory.abstractfactory.DocumentType;
import designpatterns.factory.abstractfactory.processor.DocumentProcessor;

public class SpreadsheetDocumentPrinter extends DocumentPrinter {
    public SpreadsheetDocumentPrinter(DocumentProcessor processor) {
        super(processor);
    }

    @Override
    public void createDocumentPrinter() {
        System.out.println("Created spreadsheet document printer: " + getFileName());
    }

    @Override
    public void processDocumentPrinter() {
        System.out.println("Prepared spreadsheet document for printing: " + getFileName());
    }

    @Override
    public String printDocument() {
        return "Printed spreadsheet document: " + getFileName();
    }

    @Override
    public DocumentType getDocumentType() {
        return DocumentType.SPREADSHEET;
    }
}
