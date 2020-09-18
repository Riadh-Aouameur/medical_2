package medical;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Assistant {

    SimpleIntegerProperty id;
    SimpleStringProperty firstName;
    SimpleStringProperty lastName;
    SimpleObjectProperty<LocalDate> birthday ;
    SimpleStringProperty address ;
    SimpleStringProperty phone ;
    SimpleStringProperty pass ;
    SimpleStringProperty gender ;
    public Assistant(String firstName, String lastName, LocalDate birthday, String address, String emailOrPhone, String pass, String gender) {
        this.id = new SimpleIntegerProperty();
        this.firstName =new SimpleStringProperty( firstName);
       this.lastName =new SimpleStringProperty( lastName);
        this.birthday =new SimpleObjectProperty<>( birthday);
        this.address = new SimpleStringProperty(address);
        this.phone = new SimpleStringProperty(emailOrPhone);
        this.pass = new SimpleStringProperty(pass);
        this.gender = new SimpleStringProperty(gender);
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

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
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


    public Object getBirthday() {
        return birthday.get();
    }

    public SimpleObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
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

    public String getPass() {
        return pass.get();
    }

    public SimpleStringProperty passProperty() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass.set(pass);
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
}
