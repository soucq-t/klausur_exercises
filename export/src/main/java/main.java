import domain.Document;
import service.Exporter;
import service.PDF;
import service.XML;

public class main {
    public static void main(String[] args) {
        Document doc= new Document();
        Exporter exporter=new Exporter(new PDF());
        exporter.getExportformat().export(doc);
        exporter.setExportformat(new XML());
        exporter.getExportformat().export(doc);
    }
}
