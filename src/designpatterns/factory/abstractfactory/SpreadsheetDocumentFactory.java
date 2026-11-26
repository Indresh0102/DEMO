package designpatterns.factory.abstractfactory;

import designpatterns.factory.abstractfactory.parser.DocumentParser;
import designpatterns.factory.abstractfactory.parser.SpreadsheetDocumentParser;
import designpatterns.factory.abstractfactory.printer.DocumentPrinter;
import designpatterns.factory.abstractfactory.printer.SpreadsheetDocumentPrinter;
import designpatterns.factory.abstractfactory.processor.DocumentProcessor;
import designpatterns.factory.abstractfactory.processor.SpreadsheetDocumentProcessor;

public class SpreadsheetDocumentFactory extends DocumentFactory {
    public SpreadsheetDocumentFactory(String fileName) {
        super(fileName);
    }
    public DocumentProcessor createDocumentProcessor(){
        return new SpreadsheetDocumentProcessor(getFileName());
    }
    public DocumentPrinter createDocumentPrinter(DocumentProcessor processor){
        if(processor==null ||
                processor.getDocumentType()!=DocumentType.SPREADSHEET){
            throw new IllegalArgumentException("DocumentType not supported for SpreadsheetDocumentPrinter");
        }
        return new SpreadsheetDocumentPrinter(processor);
    }
    public DocumentParser createDocumentParser(){
        return new SpreadsheetDocumentParser(getFileName());
    }
    public DocumentType getDocumentType(){
        return DocumentType.SPREADSHEET;
    }
}
