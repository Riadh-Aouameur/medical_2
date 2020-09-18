package medical;

import javafx.beans.property.SimpleStringProperty;

public class Patient {

    SimpleStringProperty firstName;
    SimpleStringProperty lastName;
    SimpleStringProperty gender;
    SimpleStringProperty phone;

    public Patient() {

    }

    public Patient(String firstName, String lastName, String gender, String phone) {
        this.firstName =  new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.gender = new SimpleStringProperty(gender);
        this.phone = new SimpleStringProperty(phone);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getGender() {
        return gender.get();
    }

    public SimpleStringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }
}
