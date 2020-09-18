package medical;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.net.URL;
import java.util.ResourceBundle;

public class ControllerListItem implements Initializable {

    public Label lastName;
    public Label phone;
    public Label status;
    public Label firstName;
    public ImageView img;
    public Label number;

    PatientForWaitingRoom patientForWaitingRoom;

    public ControllerListItem(PatientForWaitingRoom patientForWaitingRoom) {
        this.patientForWaitingRoom = patientForWaitingRoom;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (patientForWaitingRoom.getGender().equals("Female")){
            Image imProfile = new Image(getClass().getResourceAsStream("img/femalepatient.png"));
            img.setImage(imProfile);
        }

        firstName.setText(patientForWaitingRoom.getFirstName());
        lastName.setText(patientForWaitingRoom.getLastName());
        phone.setText(patientForWaitingRoom.getPhone());
        number.setText(String.valueOf(patientForWaitingRoom.getNumber()));
        status.setText(patientForWaitingRoom.getStatus());

    }
}
