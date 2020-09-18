package medical;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import medical.DataBase.Db;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerDDelete implements Initializable {
    public Label fPatient;
    public Label lab1;

    public ImageView img;
    public Label text;

    public Label fDateTwo;
    public Label fDateOne;
    public Label fPhone;
    public Label fStats;
    Db db = new Db();
    public TextField fnb;

    ObservableList<PatientForAppointment> observableList;
    PatientForAppointment patientForAppointment;

    public ControllerDDelete(ObservableList<PatientForAppointment> observableList , PatientForAppointment patientForAppointment) {
        this.observableList = observableList;
        this.patientForAppointment =patientForAppointment;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (patientForAppointment!=null){
            fPatient.setText("Patient : "+patientForAppointment.getFirstName()+" "+patientForAppointment.getLastName());
            fPhone.setText("Phone : "+patientForAppointment.getPhone());
            fStats.setText("Status : "+patientForAppointment.getStatus());
            fDateOne.setText("Day : "+patientForAppointment.getDateOne());
            fDateTwo.setText("Day Appointment : "+patientForAppointment.getDateTwo());
            if (patientForAppointment.getGender().equals("Female")){
                Image imProfile = new Image(getClass().getResourceAsStream("img/femalepatient.png"));
                img.setImage(imProfile);
            }else {
                Image imProfile = new Image(getClass().getResourceAsStream("img/patient.png"));
                img.setImage(imProfile);
            }
        }
        fnb.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    // Check if the new character is greater than LIMIT
                    if (fnb.getText().length() >= 2) {

                        // if it's 11th character then just setText to previous
                        // one
                        fnb.setText(fnb.getText().substring(0, 2));
                    }
                }
            }
        });

    }


    public void onDelete(ActionEvent actionEvent) {
        if (patientForAppointment !=null){
            observableList.remove(patientForAppointment);
            db.deleteAppointment(patientForAppointment.getId());
            text.setVisible(true);

        }

    }

    public void onExit(ActionEvent actionEvent) {
        System.exit(0);

    }

    public void onSelect(ActionEvent actionEvent) {
        int id;
        if(fnb.getText().isEmpty()){
            lab1.setText("Enter your patient number");
        }
        else{
            try {
                 id = Integer.parseInt(fnb.getText());
                 for (PatientForAppointment p :observableList){
                     if(p.getId()==id){
                         patientForAppointment = p;
                         fPatient.setText("Patient : "+patientForAppointment.getFirstName()+" "+patientForAppointment.getLastName());
                         fPhone.setText("Phone : "+patientForAppointment.getPhone());
                         fStats.setText("Status : "+patientForAppointment.getStatus());
                         fDateOne.setText("Day : "+patientForAppointment.getDateOne());
                         fDateTwo.setText("Day Appointment : "+patientForAppointment.getDateTwo());
                         if (patientForAppointment.getGender().equals("Female")){
                             Image imProfile = new Image(getClass().getResourceAsStream("img/femalepatient.png"));
                             img.setImage(imProfile);
                         }else {
                             Image imProfile = new Image(getClass().getResourceAsStream("img/patient.png"));
                             img.setImage(imProfile);
                         }
                         return;
                     }
                 }




            }catch (Exception e){
               // lab1.setText("Something is wrong");
            }

        }
    }


}
