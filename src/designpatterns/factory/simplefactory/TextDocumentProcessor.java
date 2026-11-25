package designpatterns.factory.simplefactory;

public class TextDocumentProcessor extends DocumentProcessor {

    public TextDocumentProcessor(String fileName) {
       super(fileName);
    }

    @Override
    void processDocument() {
        System.out.println("Processing text document: " + getFileName());
        System.out.println(getDocumentType() + " text processing done!");
    }

    @Override
    void getDocument() {
        System.out.println("Text document got: " + getFileName());
    }

    @Override
    void addDocument(String fileName) {
        setFileName(fileName);
        System.out.println(fileName + " added as a text document!");
    }

    @Override
    DocumentType getDocumentType() {
        return DocumentType.TEXT;
    }
}
