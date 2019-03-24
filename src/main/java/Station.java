import javafx.beans.property.StringProperty;

public class Station {
    Slot[] slots;
    StringProperty LCD;

    public String getLCD() {
        return LCD.get();
    }

    public void setLCD(String LCD) {
        this.LCD.set(LCD);
    }
}