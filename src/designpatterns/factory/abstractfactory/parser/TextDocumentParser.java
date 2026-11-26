package designpatterns.factory.abstractfactory.parser;

import designpatterns.factory.abstractfactory.DocumentType;

public class TextDocumentParser extends DocumentParser {
    public TextDocumentParser(String fileName) {
        super(fileName);
    }

    @Override
    public void createDocumentParser() {
        System.out.println("Created text document parser: " + getFileName());
    }

    @Override
    public void processDocumentParser() {
        System.out.println("Processed text document parser: " + getFileName());
    }

    @Override
    public String getDocumentParserName() {
        return getFileName();
    }

    @Override
    public DocumentType getDocumentType() {
        return DocumentType.TEXT;
    }
}
