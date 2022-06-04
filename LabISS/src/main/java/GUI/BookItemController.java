package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class BookItemController {
    @FXML
    private Label autorCarte;

    @FXML
    private Label titluCarte;

    @FXML
    private Label nrExemplare;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private CheckBox checkBox;

    public void setTitlu(String titlu) {
        titluCarte.setText(titlu);
    }

    public void setAutor(String autor) {
        autorCarte.setText(autor);
    }

    public void setNrExemplare(String exemplare){nrExemplare.setText(exemplare);}

    @FXML
    public void selectBook(){
        if(checkBox.isSelected()) {
            anchorPane.fireEvent(new CheckBoxSelected(CheckBoxSelected.BOOK_SELECTED, anchorPane.getId()));
        }
    }
}
