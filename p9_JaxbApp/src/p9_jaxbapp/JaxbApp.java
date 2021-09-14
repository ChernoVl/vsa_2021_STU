package p9_jaxbapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

public class JaxbApp {

    private final static String BOOKSTORE_XML = "./test/bookstore.xml";
    private final static String BOOK_XML = "./test/book.xml";

    public static void main(String[] args) throws PropertyException, JAXBException, FileNotFoundException {
        // create a book 
        Book book = new Book();
        book.setIsbn("978-0060554738");
        book.setName("Pipi Dlha Pancucha");
        book.setAuthor("Astrid Lindgrenova");
        book.setPublisher("Mlade Leta");

        // create JAXB context and instantiate marshaller
        JAXBContext context = JAXBContext.newInstance(Book.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // Write book as xml to System.out
        m.marshal(book, System.out);

        // Write book ax xml to File
        m.marshal(book, new File(BOOK_XML));

        // Read data from xml file, created before in a new Book object 
        JAXBContext context2 = JAXBContext.newInstance(Book.class);
        Unmarshaller um = context2.createUnmarshaller();
        Book book2 = (Book) um.unmarshal(new FileReader(BOOK_XML));
        System.out.println("Book: " + book2.getName() + " Author: "
                        + book2.getAuthor());
    }

    public static void main2(String[] args) throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(Book.class);
        System.out.println("Output from our XML");
        Unmarshaller um = context.createUnmarshaller();
        Bookstore bookstore = (Bookstore) um.unmarshal(new FileReader(BOOKSTORE_XML));
        ArrayList<Book> list = bookstore.getBookList();
        for(Book book: list){
            System.out.println("Isbn: " + book.getIsbn() + "from " + book.getAuthor());
        }
        
    }
}
