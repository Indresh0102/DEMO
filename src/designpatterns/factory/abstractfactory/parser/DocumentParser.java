package designpatterns.factory.abstractfactory.parser;

import designpatterns.factory.abstractfactory.DocumentType;

public abstract class DocumentParser {
    private String fileName;

    public DocumentParser(String fileName) {
        this.fileName = fileName;
    }
    public String getFileName() {
        return this.fileName;
    }
    public abstract void createDocumentParser();
    public abstract void processDocumentParser();
    public abstract String getDocumentParserName();
    public abstract DocumentType getDocumentType();
}
