package designpatterns.factory.abstractfactory.printer;

import designpatterns.factory.abstractfactory.DocumentType;
import designpatterns.factory.abstractfactory.processor.DocumentProcessor;

public class TextDocumentPrinter extends DocumentPrinter {
    public TextDocumentPrinter(DocumentProcessor processor) {
        super(processor);
    }

    @Override
    public void createDocumentPrinter() {
        System.out.println("Created text document printer: " + getFileName());
    }

    @Override
    public void processDocumentPrinter() {
        System.out.println("Prepared text document for printing: " + getFileName());
    }

    @Override
    public String printDocument() {
        return "Printed text document: " + getFileName();
    }

    @Override
    public DocumentType getDocumentType() {
        return DocumentType.TEXT;
    }
}
