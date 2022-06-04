package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import service.SuperService;

public class ReturnBooksController {

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField isbnTextField;

    private SuperService service;

    public void handlerButton(){
        service.removeImprumut(isbnTextField.getText(), emailTextField.getText());
    }

    public void setService(SuperService service) {
        this.service = service;
    }
}
