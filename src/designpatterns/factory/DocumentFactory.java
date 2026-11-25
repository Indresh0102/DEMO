package designpatterns.factory;

public class DocumentFactory {
    public static DocumentProcessor createDocumentProcessor(DocumentType documentType, String fileName) {
        if (documentType==null) {
            throw new IllegalArgumentException("Document type cannot be null");
        }
        switch (documentType) {
            case TEXT:
                return new TextDocumentProcessor(fileName);
            case PRESENTATION:
                return new PresentationDocumentProcessor(fileName);
            case SPREADSHEET:
                return new SpreadsheetDocumentProcessor(fileName);
            default:
                throw new IllegalArgumentException("Document type not supported"+documentType);
        }
    }
}
