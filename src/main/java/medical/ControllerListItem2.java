package medical;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerListItem2 implements Initializable {

    public ImageView img;
    public Label id;
    public Label firstName;
    public Label lastName;
    public Label dateOne;
    public Label dateTwo;
    public Label phone;
    PatientForAppointment patientForAppointment;

    public ControllerListItem2(PatientForAppointment patientForAppointment) {
        this.patientForAppointment = patientForAppointment;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (patientForAppointment.getGender().equals("Female")){
            Image imProfile = new Image(getClass().getResourceAsStream("img/femalepatient.png"));
            img.setImage(imProfile);
        }

        firstName.setText(patientForAppointment.getFirstName());
        lastName.setText(patientForAppointment.getLastName());
        phone.setText(patientForAppointment.getPhone());
        id.setText(String.valueOf(patientForAppointment.getId()));
        dateOne.setText(patientForAppointment.getDateOne().toString());
        dateTwo.setText(patientForAppointment.getDateTwo().toString());


    }
}
