package designpatterns.factory.abstractfactory;

import designpatterns.factory.abstractfactory.parser.DocumentParser;
import designpatterns.factory.abstractfactory.parser.TextDocumentParser;
import designpatterns.factory.abstractfactory.printer.DocumentPrinter;
import designpatterns.factory.abstractfactory.printer.TextDocumentPrinter;
import designpatterns.factory.abstractfactory.processor.DocumentProcessor;
import designpatterns.factory.abstractfactory.processor.TextDocumentProcessor;

public class TextDocumentFactory extends DocumentFactory {
    public TextDocumentFactory(String fileName) {
        super(fileName);
    }

    @Override
    public DocumentProcessor createDocumentProcessor() {
        return new TextDocumentProcessor(getFileName());
    }

    @Override
    public DocumentPrinter createDocumentPrinter(DocumentProcessor processor) {
        if(processor==null ||
                processor.getDocumentType()!=DocumentType.TEXT){
            throw new IllegalArgumentException("DocumentType not supported for TextDocumentPrinter");
        }
        return new TextDocumentPrinter(processor);
    }

    @Override
    public DocumentParser createDocumentParser() {
        return new TextDocumentParser(getFileName());
    }

    @Override
    public DocumentType getDocumentType() {
        return DocumentType.TEXT;
    }
}
