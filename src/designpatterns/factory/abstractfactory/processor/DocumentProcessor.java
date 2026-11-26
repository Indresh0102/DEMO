package designpatterns.factory.abstractfactory.processor;

import designpatterns.factory.abstractfactory.DocumentType;

public abstract class DocumentProcessor {
    private String fileName;

    public DocumentProcessor(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public abstract void createDocumentProcessor();
    public abstract void processDocument();
    public abstract String getDocumentProcessorName();
    public abstract DocumentType getDocumentType();
}
