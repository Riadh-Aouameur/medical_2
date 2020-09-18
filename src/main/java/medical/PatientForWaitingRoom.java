package medical;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PatientForWaitingRoom  extends  Patient{
    SimpleIntegerProperty number;
     SimpleStringProperty status;



    public PatientForWaitingRoom(String firstName, String lastName, String gender, String phone, Integer number, String status) {
        super(firstName, lastName, gender, phone);
        this.number = new SimpleIntegerProperty(number);
        this.status = new SimpleStringProperty(status);
    }

    public int getNumber() {
        return number.get();
    }

    public SimpleIntegerProperty numberProperty() {
        return number;
    }

    public void setNumber(int number) {
        this.number.set(number);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}
