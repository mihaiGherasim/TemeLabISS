package GUI;

import javafx.event.Event;
import javafx.event.EventType;


public class CheckBoxSelected extends Event {
    private static final long serialVersionUID= 6481283618293L;
    private String selectedItemId;

    public static final EventType<CheckBoxSelected> BOOK = new EventType<>("ITEM");
    public static final EventType<CheckBoxSelected> ANY = BOOK;
    public static final EventType<CheckBoxSelected> BOOK_SELECTED= new EventType<>(CheckBoxSelected.ANY,"CHECKbOX_SELECTED");

    public CheckBoxSelected(EventType<? extends Event> eventType, String flightId) {
        super(eventType);
        selectedItemId =flightId;
    }

    public String getSelectedItemId() {
        return selectedItemId;
    }
}
