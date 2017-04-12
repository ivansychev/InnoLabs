package tests;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import workers.NumFileParser;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * Created by ivans on 12/04/2017.
 */
public class TestMain {
    private static NumFileParser numFileParser;

    @BeforeClass
    public static void init(){
         numFileParser = new NumFileParser();
    }

    @After
    public void clear(){
        numFileParser = new NumFileParser();
    }

    @Test
    public void testNumFileParser(){
        long result;

        numFileParser.passFile("testInputFiles/testInput1.txt");
        result = numFileParser.parseSum();
        assertEquals(30,result);

        numFileParser.passFile("testInputFiles/onlyNegativesTestInput1.txt");
        result = numFileParser.parseSum();
        assertEquals(0,result);

        numFileParser.passFile("testInputFiles/wrongTestInput1.txt");
        result = numFileParser.parseSum();
        assertEquals(0,result);

        numFileParser.passFile("testInputFiles/wrongTestInput2.txt");
        result = numFileParser.parseSum();
        assertEquals(0,result);

        numFileParser.passFile("testInputFiles/wrongTestInput3.txt");
        result = numFileParser.parseSum();
        assertEquals(0,result);

        numFileParser.passFile("testInputFiles/fileThatExists.txt");
        result = numFileParser.parseSum();
        assertEquals(0,result);

    }

    @Test
    public void testNumFilePasser() throws ClassNotFoundException, IllegalAccessException {
        numFileParser.passFile("testInputFiles/fileThatExists.txt");
        for(Field f : Class.forName("workers.NumFileParser").getDeclaredFields())
        {
            f.setAccessible(true);
            assertEquals("testInputFiles\\fileThatExists.txt",f.get(numFileParser).toString());
        }
    }
}

/*for(Field f : Class.forName("library.models.Book").getDeclaredFields()) {
        Element field = doc.createElement("field");

        Attr attrType1 = doc.createAttribute("isAccessible");
        attrType1.setValue("" + f.isAccessible());
        field.setAttributeNode(attrType1);

        Attr attrType2 = doc.createAttribute("type");
        attrType2.setValue(f.getType().toString());
        field.setAttributeNode(attrType2);

        Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(f.getName()));
        field.appendChild(name);

        f.setAccessible(true);

        Element value = doc.createElement("value");
        value.appendChild(doc.createTextNode(f.get(bk).toString()));
        field.appendChild(value);

        fields.appendChild(field);
        }*/

/*
package test;

        import library.Library;
        import library.models.Book;
        import library.models.BookInstance;
        import library.models.Reader;
        import org.junit.After;
        import org.junit.BeforeClass;
        import org.junit.Test;

        import java.util.HashSet;

        import static org.junit.Assert.*;

public class MainTest {

    @BeforeClass
    public static void init() {
        library = new Library();
    }

    @After
    public void clear() {
        library = new Library();
    }

    private static Library library;

    @Test
    public void buyBookTest() {
        library.buyBook("Intro to Java", "Schildt", "1241241ada", 5, 2017);
        assertEquals(1,library.getCatalog().size());
        Book book = new Book("Schildt", "Intro to Java", 2017, "1241241ada");
        assertTrue(library.getCatalog().contains(book));

        Book bookFromCatalog= library.getCatalog().iterator().next();
        assertTrue(book.getTitle().equals(bookFromCatalog.getTitle()));
        assertTrue(book.getAuthor().equals(bookFromCatalog.getAuthor()));
        assertTrue(book.getIsbn().equals(bookFromCatalog.getIsbn()));
        assertTrue(book.getYear()==bookFromCatalog.getYear());
    }

    @Test
    public void buyBookTestStore() {
        library.buyBook("Intro to Java", "Schildt", "1241241ada", 5, 2017);
        assertEquals(5, library.getStore().size());
    }

    @Test
    public void buyBookTestCatalog() {
        library.buyBook("Intro in Java", "Schildt", "123456abc", 5, 2017);
        assertEquals(5, library.getStore().size());

        Book book = new Book("Schildt", "Intro in Java", 2017, "123456abc");
        for (BookInstance b : library.getStore()) {
            Book storeBook = b.getBook();

            assertTrue( book.getTitle().equals( storeBook.getTitle() ) );
            assertTrue( book.getAuthor().equals( storeBook.getAuthor() ) );
            assertTrue( book.getIsbn().equals( storeBook.getIsbn() ) );
            assertTrue(book.getYear() == storeBook.getYear() );
        }
    }

    @Test
    public void takeBookTestReaders() {
        library.buyBook("Intro to Java", "Schildt", "1241241ada", 5, 2017);
        Reader reader = new Reader("John", "Connor", "Androidovich", 12345678);
        library.takeBook("John", "Connor", "Androidovich", 12345678, "Intro to Java");
        assertEquals(1,library.getReaders().size());
        assertTrue(library.getReaders().contains(reader));
    }
    @Test
    public void takeBookTestBooking() {
        library.buyBook("Intro to Java", "Schildt", "1241241ada", 5, 2017);
        library.takeBook("John", "Connor", "Androidovich", 12345678, "Intro to Java");
        assertEquals(1,library.getBookings().size());
    }
    @Test
    public void takeBookTestStore() {
        library.buyBook("Intro to Java", "Schildt", "1241241ada", 5, 2017);
        HashSet<BookInstance> bkins = new HashSet<>();
        for(BookInstance b : library.getStore())
        {
            bkins.add(b);
        }
        library.takeBook("John", "Connor", "Androidovich", 12345678, "Intro to Java");
        for(BookInstance b : library.getStore())
        {
            assertTrue(bkins.contains(b));
        }
        assertEquals(4,library.getStore().size());
    }

    @Test
    public void returnBookTestStore() {
        library.buyBook("Intro to Java", "Schildt", "1241241ada", 5, 2017);
        library.takeBook("John", "Connor", "Androidovich", 12345678, "Intro to Java");
        library.returnBook("John", "Connor", "Androidovich", 12345678, "Intro to Java");
        assertEquals(5,library.getStore().size());
    }
    @Test
    public void returnBookTestBookings() {
        library.buyBook("Intro to Java", "Schildt", "1241241ada", 5, 2017);
        library.takeBook("John", "Connor", "Androidovich", 12345678, "Intro to Java");
        library.returnBook("John", "Connor", "Androidovich", 12345678, "Intro to Java");
        assertEquals(0,library.getBookings().size());
    }
}*/
