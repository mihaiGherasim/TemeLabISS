package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import service.SuperService;

public class AddBooksController {
    private SuperService service;
    private String cnpLoggedUser;

    private String passwordLoggedUser;

    @FXML
    private Button adaugaButton;

    @FXML
    private TextField autorTextField;

    @FXML
    private TextField isbnTextField;

    @FXML
    private TextField nrTextField;

    @FXML
    private TextField titluTextField;

    @FXML
    public void addBook(){
        service.addBook(titluTextField.getText(), autorTextField.getText(), nrTextField.getText());
        autorTextField.clear();
        titluTextField.clear();
        nrTextField.clear();
    }

    public void setService(SuperService service) {
        this.service = service;
    }
}
