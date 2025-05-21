package models;

import exceptions.LibraryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

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

    @Nested
    class AddBookTest {
        @Test
        void shouldAddValidBook() throws LibraryException {
            library.addBook(book1);
            assertTrue(library.findAll().contains(book1));
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
            library.addBook(book1);
        }

        @Test
        void shouldRemoveExistingBook() throws LibraryException {
            library.removeBook("1984");
            assertThrows(LibraryException.class, () -> library.findByTitle("1984"));
        }

        @Test
        void shouldThrowWhenLibraryIsEmpty() throws LibraryException {
            library.removeBook("1984");
            assertThrows(LibraryException.class, () -> library.removeBook("1984"));
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
            library.addBook(book1);
        }

        @Test
        void shouldFindBookByTitle() throws LibraryException {
            Book found = library.findByTitle("1984");
            assertEquals(book1, found);
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
            library.removeBook("1984");
            assertThrows(LibraryException.class, () -> library.findByTitle("1984"));
        }
    }

    @Nested
    class FindAllTest {
        @Test
        void shouldReturnAllBooks() throws LibraryException {
            library.addBook(book1);
            List<Book> all = library.findAll();
            assertEquals(1, all.size());
            assertTrue(all.contains(book1));
        }

        @Test
        void shouldThrowWhenLibraryIsEmpty() {
            assertThrows(LibraryException.class, () -> library.findAll());
        }
    }

    @Nested
    class SortMethodTests {

        @Test
        void shouldSortBooksAlphabeticallyByTitleIgnoringCase() throws LibraryException {
            library.addBook(book3);
            library.addBook(book1);
            library.addBook(book2);
            library.sort();
            List<Book> sortedBooks = library.findAll();
            assertEquals(book1, sortedBooks.get(0));
            assertEquals(book2, sortedBooks.get(1));
            assertEquals(book3, sortedBooks.get(2));
        }

        @Test
        void shouldThrowWhenSortingEmptyLibrary() {
            assertThrows(LibraryException.class, () -> library.sort());
        }
    }

    @Nested
    class FindPartialByTitleTests {

        @Test
        void shouldThrowIfLibraryIsEmpty() {
            Library emptyLibrary = new Library();
            assertThrows(LibraryException.class,
                    () -> emptyLibrary.findPartialByTitle("1984"),
                    "La bibliothèque est vide."
            );
        }

        @Test
        void shouldThrowIfTitleIsEmpty() throws LibraryException {
            library.addBook(book1);
            assertThrows(LibraryException.class,
                    () -> library.findPartialByTitle(""),
                    "Impossible de récupérer avec un titre vide."
            );
        }

        @Test
        void shouldReturnBookIfPartialTitleMatches() throws LibraryException {
            library.addBook(book1);
            library.addBook(book2);
            library.addBook(book3);

            Book found = library.findPartialByTitle("New");
            assertEquals(book2, found);

            Book found2 = library.findPartialByTitle("84");
            assertEquals(book1, found2);
        }

        @Test
        void shouldThrowIfNoBookMatchesPartialTitle() throws LibraryException {
            library.addBook(book1);
            library.addBook(book2);
            assertThrows(LibraryException.class,
                    () -> library.findPartialByTitle("foo"),
                    "Impossible de récupérer le livre foo. Il n'existe pas dans la bibliothèque."
            );
        }
    }
}
