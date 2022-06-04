package GUI;

import com.example.labiss.LogInApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;
import service.LibraryException;
import service.SuperService;

import java.io.IOException;

public class LogInController {
    private SuperService service;
    private String cnp;
    private String password;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button logInButton;

    public void setService(SuperService service){
        this.service = service;
    }

    @FXML
    private void handlerLogInButton() throws IOException{
        String email = emailField.getText();
        String password = passwordField.getText();

        if(!email.equals("") && !password.equals("")) {
            if (email.equals("123") && password.equals("admin")) {
                try {
                    FXMLLoader loader = new FXMLLoader(LogInApplication.class.getResource("addBooksView.fxml"));
                    Stage mainWindowStage = new Stage();
                    AnchorPane mainAnchorPane = loader.load();
                    Scene scene = new Scene(mainAnchorPane, 602, 450);
                    AddBooksController addBooksController = loader.getController();
                    addBooksController.setService(service);
                    service.logIn(email, password);
                    mainWindowStage.setScene(scene);
                    mainWindowStage.show();
                } catch (LibraryException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(e.getMessage());
                    alert.show();
                }
            }
            else if(email.equals("123") && password.equals("return")){
                try {
                    FXMLLoader loader = new FXMLLoader(LogInApplication.class.getResource("returnBooksView.fxml"));
                    Stage mainWindowStage = new Stage();
                    AnchorPane mainAnchorPane = loader.load();
                    Scene scene = new Scene(mainAnchorPane, 602, 450);
                    ReturnBooksController returnBooksController = loader.getController();
                    returnBooksController.setService(service);
                    service.logIn(email, password);
                    mainWindowStage.setScene(scene);
                    mainWindowStage.show();
                } catch (LibraryException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(e.getMessage());
                    alert.show();
                }
            }
            else {
                try {
                    FXMLLoader loader = new FXMLLoader(LogInApplication.class.getResource("mainWindow.fxml"));
                    Stage mainWindowStage = new Stage();
                    AnchorPane mainAnchorPane = loader.load();
                    Scene scene = new Scene(mainAnchorPane, 602, 450);
                    MainWindowController mainWindowController = loader.getController();
                    mainWindowController.setService(service);
                    service.logIn(email, password);
                    mainWindowController.loadBooks();
                    mainWindowController.load();
                    mainWindowStage.setScene(scene);
                    mainWindowStage.show();
                    //Stage logInStage = (Stage) logInButton.getScene().getWindow();
                    //logInStage.close();
                } catch (LibraryException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(e.getMessage());
                    alert.show();
                }
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Fill all fields!!!");
            alert.show();
        }
    }
}
