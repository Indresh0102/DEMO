package designpatterns.factory;

public class SpreadsheetDocumentProcessor extends DocumentProcessor {

    public SpreadsheetDocumentProcessor(String fileName) {
        super(fileName);
    }

    @Override
    void processDocument() {
        System.out.println("Processing spreadsheet document: " + getFileName());
        System.out.println(getDocumentType() + " spreadsheet processing done!");
    }

    @Override
    void getDocument() {
        System.out.println("Spreadsheet document got: " + getFileName());
    }

    @Override
    void addDocument(String fileName) {
        setFileName(fileName);
        System.out.println(fileName + " added as a spreadsheet document!");
    }

    @Override
    DocumentType getDocumentType() {
        return DocumentType.SPREADSHEET;
    }
}
