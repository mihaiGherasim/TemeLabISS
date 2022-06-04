package com.example.labiss;

import GUI.LogInController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import persistence.BookRepository;
import persistence.LoanRepository;
import persistence.UserRepository;
import service.BookService;
import service.LoanService;
import service.SuperService;
import service.UserService;

import java.io.IOException;
import java.util.Properties;

public class LogInApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        UserRepository userRepository = new UserRepository();
        BookRepository bookRepository = new BookRepository();
        LoanRepository loanRepository = new LoanRepository();
        UserService userService = new UserService(userRepository);
        BookService bookService = new BookService(bookRepository);
        LoanService loanService = new LoanService(loanRepository);
        SuperService superService = new SuperService(userService, bookService, loanService);
        FXMLLoader fxmlLoader = new FXMLLoader(LogInApplication.class.getResource("logIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 400);
        stage.setTitle("Log In");
        LogInController controller = fxmlLoader.getController();
        controller.setService(superService);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}