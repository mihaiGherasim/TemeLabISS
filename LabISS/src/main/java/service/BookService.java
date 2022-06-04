package service;

import javafx.scene.control.TextField;
import model.Book;
import persistence.BookRepository;

import java.util.List;

public class BookService {
    BookRepository repository;
    public BookService(BookRepository repository){
        this.repository = repository;
    }
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Book findById(int id) {
        return repository.findById(id);
    }

    public void updateAvailableBooks(int id, String newNumber) {
        repository.updateBooks(id, newNumber);
    }

    public void addBooks(String id, String cantitate) {
        repository.addBooks(id, cantitate);
    }

    public void addBook(String titlu, String autor, String nr) {
        repository.addBook(new Book(titlu, autor, Integer.parseInt(nr)));
    }
}
