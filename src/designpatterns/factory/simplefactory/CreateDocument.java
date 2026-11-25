package designpatterns.factory.simplefactory;

public class CreateDocument {
    public static void main(String[] args) {
        DocumentProcessor d = DocumentFactory.createDocumentProcessor(
                DocumentType.TEXT, "todo.txt"
        );

        d.getDocument();
        d.processDocument();
        d.addDocument("Mytodo.txt");

        System.out.println(d.getDocumentType());
    }
}
