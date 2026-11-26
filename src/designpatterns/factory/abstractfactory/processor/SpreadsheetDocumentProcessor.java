package designpatterns.factory.abstractfactory.processor;

import designpatterns.factory.abstractfactory.DocumentType;

public class SpreadsheetDocumentProcessor extends DocumentProcessor {
    public SpreadsheetDocumentProcessor(String fileName) {
        super(fileName);
    }

    @Override
    public void createDocumentProcessor() {
        System.out.println("Created spreadsheet document processor: " + getFileName());
    }

    @Override
    public void processDocument() {
        System.out.println("Processed spreadsheet document: " + getFileName());
    }

    @Override
    public String getDocumentProcessorName() {
        return getFileName();
    }

    @Override
    public DocumentType getDocumentType() {
        return DocumentType.SPREADSHEET;
    }
}
