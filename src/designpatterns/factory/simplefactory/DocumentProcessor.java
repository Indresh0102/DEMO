package designpatterns.factory.simplefactory;

public abstract class DocumentProcessor {
    private String fileName;

    public DocumentProcessor(String fileName) {
        this.fileName = fileName;
    }

    protected String getFileName() {return this.fileName;}
    protected void setFileName(String fileName) {this.fileName = fileName;}

    abstract void processDocument();
    abstract void getDocument();
    abstract void addDocument(String fileName);
    abstract DocumentType getDocumentType();
}
