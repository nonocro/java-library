package models;

import exceptions.LibraryException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Library {

    List<Book> books = new ArrayList<>();

    public void addBook(Book book) throws LibraryException {
        if(book == null){
            throw new LibraryException("Impossible d'ajouter un livre null");
        }

        books.add(book);
    }

    public void removeBook(String title) throws LibraryException {
        if (books.isEmpty()) {
            throw new LibraryException("La bibliothèque est vide.");
        }else if(Objects.equals(title, "")){
            throw new LibraryException("Impossible de supprimer avec un titre vide.");
        }

        boolean hasFound = books.removeIf(book -> Objects.equals(book.title, title));

        if(!hasFound){
            throw new LibraryException("Impossible de supprimer le livre " + title + ". Il n'existe pas dans la bibliothèque.");
        }
    }

    public Book findByTitle(String title) throws LibraryException {
        if (books.isEmpty()) {
            throw new LibraryException("La bibliothèque est vide.");
        }else if(Objects.equals(title, "")){
            throw new LibraryException("Impossible de récupérer avec un titre vide.");
        }

        Optional<Book> book = books.stream().filter(b -> Objects.equals(b.title, title)).findFirst();

        if(book.isPresent()){
            return book.get();
        }else
            throw new LibraryException("Impossible de récupérer le livre " + title + ". Il n'existe pas dans la bibliothèque.");
        }

    public List<Book> findAll() throws LibraryException{
        if (books.isEmpty()) {
            throw new LibraryException("La bibliothèque est vide.");
        }

        return books;
    }
}
