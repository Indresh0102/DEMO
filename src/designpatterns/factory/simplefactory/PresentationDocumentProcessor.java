package designpatterns.factory.simplefactory;

public class PresentationDocumentProcessor extends DocumentProcessor{


    public PresentationDocumentProcessor(String fileName) {
        super(fileName);
    }

    public void processDocument() {
        System.out.println("Processing file: " + getFileName());
        System.out.println(getFileName()+" Processing done!" );
    }

    public void addDocument(String fileName) {
        setFileName(fileName);
        System.out.println(getFileName()+" added to document!" );
    }

    public void getDocument() {
        System.out.println("document got: "+getFileName());
    }

    public DocumentType getDocumentType() {
        return DocumentType.PRESENTATION;
    }
}
