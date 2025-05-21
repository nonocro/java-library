package models;

import exceptions.LibraryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    Library library;
    Book sampleBook;

    @BeforeEach
    void setUp() {
        library = new Library();
        sampleBook = new Book("Les autodafeurs", "Marine Carteron", 2014);
    }

    @Nested
    class AddBookTest {
        @Test
        void shouldAddValidBook() throws LibraryException {
            library.addBook(sampleBook);
            assertTrue(library.findAll().contains(sampleBook));
        }

        @Test
        void shouldThrowWhenAddingNullBook() {
            assertThrows(LibraryException.class, () -> library.addBook(null));
        }
    }

    @Nested
    class RemoveBookTest {
        @BeforeEach
        void setupRemove() throws LibraryException {
            library.addBook(sampleBook);
        }

        @Test
        void shouldRemoveExistingBook() throws LibraryException {
            library.removeBook("Les autodafeurs");
            assertThrows(LibraryException.class, () -> library.findByTitle("Les autodafeurs"));
        }

        @Test
        void shouldThrowWhenLibraryIsEmpty() throws LibraryException {
            library.removeBook("Les autodafeurs");
            assertThrows(LibraryException.class, () -> library.removeBook("Les autodafeurs"));
        }

        @Test
        void shouldThrowWhenTitleIsEmpty() {
            assertThrows(LibraryException.class, () -> library.removeBook(""));
        }

        @Test
        void shouldThrowWhenBookDoesNotExist() {
            assertThrows(LibraryException.class, () -> library.removeBook("Nonexistent"));
        }
    }

    @Nested
    class FindByTitleTest {
        @BeforeEach
        void setupFind() throws LibraryException {
            library.addBook(sampleBook);
        }

        @Test
        void shouldFindBookByTitle() throws LibraryException {
            Book found = library.findByTitle("Les autodafeurs");
            assertEquals(sampleBook, found);
        }

        @Test
        void shouldThrowWhenTitleIsEmpty() {
            assertThrows(LibraryException.class, () -> library.findByTitle(""));
        }

        @Test
        void shouldThrowWhenBookDoesNotExist() {
            assertThrows(LibraryException.class, () -> library.findByTitle("Nonexistent"));
        }

        @Test
        void shouldThrowWhenLibraryIsEmpty() throws LibraryException {
            library.removeBook("Les autodafeurs");
            assertThrows(LibraryException.class, () -> library.findByTitle("Les autodafeurs"));
        }
    }

    @Nested
    class FindAllTest {
        @Test
        void shouldReturnAllBooks() throws LibraryException {
            library.addBook(sampleBook);
            List<Book> all = library.findAll();
            assertEquals(1, all.size());
            assertTrue(all.contains(sampleBook));
        }

        @Test
        void shouldThrowWhenLibraryIsEmpty() {
            assertThrows(LibraryException.class, () -> library.findAll());
        }
    }
}
