package designpatterns.factory.abstractfactory.printer;

import designpatterns.factory.abstractfactory.DocumentType;
import designpatterns.factory.abstractfactory.processor.DocumentProcessor;

public abstract class DocumentPrinter {
    private DocumentProcessor processor;

    public DocumentPrinter(DocumentProcessor processor) {
        this.processor = processor;
    }
    public String getFileName(){
        return processor.getFileName();
    }
    public abstract void createDocumentPrinter();
    public abstract void processDocumentPrinter();
    public abstract String printDocument();
    public abstract DocumentType getDocumentType();
}
