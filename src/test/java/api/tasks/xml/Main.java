package api.tasks.xml;

public class Main {
    
    public static void main(String... args) {
        SimpleXMLParser xml = new SimpleXMLParser("1.xml");
        String value = xml.get("catalog/book/author");
        System.out.println(value);
    }  
}