package models;

import exceptions.LibraryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryFunctionalTest {

    Library library;
    Book book1;
    Book book2;
    Book book3;

    @BeforeEach
    void setUp() {
        library = new Library();
        book1 = new Book("1984", "George Orwell", 1949);
        book2 = new Book("Brave New World", "Aldous Huxley", 1931);
        book3 = new Book("Fahrenheit 451", "Ray Bradbury", 1953);
    }

    @Test
    void functionalScenario() throws LibraryException {
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        List<Book> allBooks = library.findAll();
        assertEquals(3, allBooks.size());
        assertTrue(allBooks.contains(book1));
        assertTrue(allBooks.contains(book2));
        assertTrue(allBooks.contains(book3));

        library.removeBook("Brave New World");

        allBooks = library.findAll();
        assertEquals(2, allBooks.size());
        assertTrue(allBooks.contains(book1));
        assertFalse(allBooks.contains(book2));
        assertTrue(allBooks.contains(book3));

        Book foundBook = library.findByTitle("1984");
        assertEquals(book1, foundBook);

        System.out.println("Livres restants dans la bibliothèque :");
        allBooks.forEach(book -> System.out.println(book.title));
    }
}
