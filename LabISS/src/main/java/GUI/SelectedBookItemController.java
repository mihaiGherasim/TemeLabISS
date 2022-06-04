package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class SelectedBookItemController {
    @FXML
    private Label nrExemplareAlese;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label titluCarteSelectata;

    public void setNrExemplareAlese(int nr){
        this.nrExemplareAlese.setText(String.valueOf(nr));
    }

    public void setTitluCarteSelectata(String titlu){
        this.titluCarteSelectata.setText(titlu);
    }

    public String getNrExemplare(){
        return this.nrExemplareAlese.getText();
    }

    public String getTitlu(){
        return this.titluCarteSelectata.getText();
    }
}
