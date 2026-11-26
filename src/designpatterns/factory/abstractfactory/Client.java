package designpatterns.factory.abstractfactory;
import designpatterns.factory.abstractfactory.printer.DocumentPrinter;
import designpatterns.factory.abstractfactory.processor.DocumentProcessor;

public class Client {
    public static void main(String[] args) {
        DocumentFactory df = SimpleDocumentFactory.createDocumentFactory(
                DocumentType.TEXT,"MyTodo.txt"
        );
        DocumentProcessor dp = df.createDocumentProcessor();
        dp.createDocumentProcessor();
        System.out.println(dp.getDocumentProcessorName());
        dp.processDocument();
        System.out.println("DocumentProcessor creation Completed!");



    }
}
