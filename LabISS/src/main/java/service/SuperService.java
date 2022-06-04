package service;

import GUI.MainWindowController;
import javafx.scene.control.TextField;
import model.Book;
import model.Loan;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SuperService {
    UserService userService;
    BookService bookService;
    LoanService loanService;
    ArrayList<MainWindowController> controllers;

    public SuperService(UserService userService, BookService bookService, LoanService loanService) {
        this.userService = userService;
        this.bookService = bookService;
        this.loanService = loanService;
        controllers = new ArrayList<>();
    }

    public void logIn(String email, String password) throws LibraryException {
        if(userService.logIn(email, password) == null){
            throw new LibraryException("User does not exist!!!");
        };
    }

    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    public Book selectedBook(int id, String nr) {
        bookService.updateAvailableBooks(id, nr);
        notifyController();
        return bookService.findById(id);
    }

    private void notifyController() {
        for(MainWindowController controller:controllers){
            controller.refresh();
        }
    }

    public void addBooks(String id, String cantitate) {
        bookService.addBooks(id, cantitate);
        notifyController();
    }

    public void addImprumut(LocalDateTime now, String email, String nume, String cantitate, String id) {
        loanService.addImprumut(new Loan(Integer.parseInt(id), Integer.parseInt(cantitate), nume, email, now.getDayOfMonth(), now.getMonthValue(), now.getYear()));
    }

    public void addObserver(MainWindowController mainWindowController) {
        controllers.add(mainWindowController);
    }

    public void addBook(String titlu, String autor, String nr) {
        bookService.addBook(titlu, autor, nr);
        notifyController();
    }

    public void removeImprumut(String isbn, String email) {
        loanService.removeImprumut(isbn, email);
    }
}
