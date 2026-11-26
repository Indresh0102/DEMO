package designpatterns.factory.abstractfactory.processor;

import designpatterns.factory.abstractfactory.DocumentType;

public class TextDocumentProcessor extends DocumentProcessor {
    public TextDocumentProcessor(String fileName) {
        super(fileName);
    }

    @Override
    public void createDocumentProcessor() {
        System.out.println("Created text document processor: " + getFileName());
    }

    @Override
    public void processDocument() {
        System.out.println("Processed text document: " + getFileName());
    }

    @Override
    public String getDocumentProcessorName() {
        return getFileName();
    }

    @Override
    public DocumentType getDocumentType() {
        return DocumentType.TEXT;
    }
}
