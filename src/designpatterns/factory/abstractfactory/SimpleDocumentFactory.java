package designpatterns.factory.abstractfactory;

public class SimpleDocumentFactory {

    public static DocumentFactory createDocumentFactory(DocumentType documentType, String fileName){

        if(documentType == null){
            throw new IllegalArgumentException("DocumentType cannot be null");
        }

        switch (documentType){
            case SPREADSHEET:
                return new SpreadsheetDocumentFactory(fileName);
            case TEXT:
                return  new TextDocumentFactory(fileName);
            default:
                throw new IllegalArgumentException("DocumentType not supported");
        }

    }
}
