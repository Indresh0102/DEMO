package designpatterns.factory.abstractfactory.parser;
import designpatterns.factory.abstractfactory.DocumentType;

public class SpreadsheetDocumentParser extends DocumentParser {
    public SpreadsheetDocumentParser(String fileName) {
        super(fileName);
    }
    @Override
    public void createDocumentParser(){
        System.out.println("Created spreadsheet document parser: " + getFileName());
    }
    @Override
    public void processDocumentParser(){
        System.out.println("Processed spreadsheet document parser: " + getFileName());
    }
    @Override
    public String getDocumentParserName(){
        return getFileName();
    }
    @Override
    public DocumentType getDocumentType(){
        return DocumentType.SPREADSHEET;
    }
}
