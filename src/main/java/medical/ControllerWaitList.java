package medical;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerWaitList implements Initializable {

    public Label tPatient;
    public Label tPhone;
    public Label tNb;
    public ImageView iStatus;
    public ImageView img;
    public AnchorPane root;
    PatientForAppointment patientForAppointment;

    public ControllerWaitList(PatientForAppointment patientForAppointment) {
        this.patientForAppointment = patientForAppointment;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (patientForAppointment.getGender().equals("Female")){
            Image imProfile = new Image(getClass().getResourceAsStream("img/femalepatient.png"));
            img.setImage(imProfile);
        }
        if (patientForAppointment.getStatus().equals("Waiting")){
            Image imProfile = new Image(getClass().getResourceAsStream("img/dot2.png"));
            iStatus.setImage(imProfile);
        }


        tPatient.setText("Patient : "+ patientForAppointment.getFirstName()+" "+ patientForAppointment.getLastName());
        tPhone.setText("Phone : "+ patientForAppointment.getPhone());
        tNb.setText("Number : "+String.valueOf(patientForAppointment.getId()));


    }
}
