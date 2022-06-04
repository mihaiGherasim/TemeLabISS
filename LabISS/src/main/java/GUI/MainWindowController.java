package GUI;

import com.example.labiss.LogInApplication;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Book;
import service.SuperService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class MainWindowController {
    SuperService service;

    int nrSelectedBooks=0;
    @FXML
    private TextField emailTextField;

    @FXML
    private TextField numeTextField;

    @FXML
    private GridPane gridPane;

    @FXML
    private ComboBox<?> comboBox;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private GridPane gridPaneSelectedBooks;

    public void setService(SuperService service){
        this.service = service;
    }

    public void load(){
        service.addObserver(this);
        anchorPane.addEventFilter(CheckBoxSelected.ANY, this::BookSelected);
    }

    private void BookSelected(CheckBoxSelected t) {
        int id = Integer.parseInt(t.getSelectedItemId());
        if(comboBox.getEditor().getText()==""){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Introduceti numarul de exemplare pe care doriti sa le imprumutati!");
            alert.show();
        }
        else {
            try {
                Book book = service.selectedBook(id, comboBox.getEditor().getText());
                addSelectedBook(book);
                populateGridPane(service.getAllBooks());
                nrSelectedBooks++;
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.show();
            }
        }
    }

    public void loadBooks() {
        try{
            List<Book> books = service.getAllBooks();
            populateGridPane(books);
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    public void addSelectedBook(Book book){
        try{
            Pane item = createSelectedBookItem(book);
            gridPaneSelectedBooks.addRow(gridPaneSelectedBooks.getRowCount(), item);
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    private Pane createSelectedBookItem(Book book) throws IOException{
        FXMLLoader loader = new FXMLLoader(LogInApplication.class.getResource("selectedBookItem.fxml"));
        Pane item = loader.load();
        SelectedBookItemController selectedBookItemController = loader.getController();
        selectedBookItemController.setTitluCarteSelectata(book.getTitlu());
        selectedBookItemController.setNrExemplareAlese(Integer.parseInt(comboBox.getEditor().getText()));
        item.setId(String.valueOf(book.getIsbn()));
        return item;
    }

    private Pane createItem(Book book) throws IOException {
        System.out.println(book);
        FXMLLoader loader = new FXMLLoader(LogInApplication.class.getResource("bookItem.fxml"));
        Pane item = loader.load();
        BookItemController bookItemController = loader.getController();
        bookItemController.setTitlu(book.getTitlu());
        bookItemController.setAutor(book.getAutor());
        bookItemController.setNrExemplare(String.valueOf(book.getNrExemplare()));
        item.setId(String.valueOf(book.getIsbn()));
        return  item;
    }

    private void populateGridPane(List<Book> books) {
        gridPane.getChildren().clear();
        System.out.println("Carti_____________________"+books);
        for (Book flight:books){
            try{
                Pane item = createItem(flight);
                gridPane.addRow(gridPane.getRowCount(), item);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void handlerAnuleazaButton(){
        ObservableList<Node> children = gridPaneSelectedBooks.getChildren();
        int a = gridPaneSelectedBooks.getRowCount();
        for(int i=0; i<a; i++) {
            AnchorPane child = (AnchorPane) children.get(i);
            Label labelCantitate = (Label) child.getChildren().get(1);
            String cantitate = labelCantitate.getText();
            String id = child.getId();
            service.addBooks(id, cantitate);
        }
        populateGridPane(service.getAllBooks());
        gridPaneSelectedBooks.getChildren().clear();
    }

    @FXML
    public void handlerConfirmaButton(){
        if(numeTextField.getText().equals("") || emailTextField.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Introduceti numele si emailul!");
            alert.show();
        }
        else{
            String email = emailTextField.getText();
            String nume = numeTextField.getText();
            ObservableList<Node> children = gridPaneSelectedBooks.getChildren();
            int a = gridPaneSelectedBooks.getRowCount();
            for(int i=0; i<a; i++){
                AnchorPane child = (AnchorPane) children.get(i);
                Label labelCantitate = (Label) child.getChildren().get(1);
                String cantitate = labelCantitate.getText();
                String id = child.getId();
                service.addImprumut(LocalDateTime.now(), email, nume, cantitate, id);
            }
            gridPaneSelectedBooks.getChildren().clear();
            emailTextField.clear();
            numeTextField.clear();
        }
    }

    public void refresh() {
        populateGridPane(service.getAllBooks());
    }
}
