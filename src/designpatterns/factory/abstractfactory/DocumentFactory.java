package designpatterns.factory.abstractfactory;

import designpatterns.factory.abstractfactory.parser.DocumentParser;
import designpatterns.factory.abstractfactory.printer.DocumentPrinter;
import designpatterns.factory.abstractfactory.processor.DocumentProcessor;

public abstract class DocumentFactory {
    private String fileName;
    DocumentFactory(String fileName) {
        this.fileName = fileName;
    }
    public String getFileName() {
        return fileName;
    }
    public abstract DocumentProcessor createDocumentProcessor();
    public abstract DocumentPrinter createDocumentPrinter(DocumentProcessor processor);
    public abstract DocumentParser createDocumentParser();
    public abstract DocumentType getDocumentType();

}
