package medical;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import medical.DataBase.Db;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMModify implements Initializable {
    public ToggleGroup group;
    public TextField fFirstName;
    public TextField fLastName;
    public TextField fPhone;
    public RadioButton iFemale;
    public RadioButton iMale;
    public DatePicker fAppointment;

    String gender = "Female";
    Db db = new Db();

    ObservableList<PatientForAppointment> observableList;
 PatientForAppointment patientForAppointment;

    public ControllerMModify(ObservableList<PatientForAppointment> observableList, PatientForAppointment patientForAppointment) {
        this.observableList = observableList;
        this.patientForAppointment = patientForAppointment;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        group = new ToggleGroup();
        iFemale.fire();
        if(patientForAppointment!=null){
            fFirstName.setText(patientForAppointment.getFirstName());
            fLastName.setText(patientForAppointment.getLastName());
            fPhone.setText(patientForAppointment.getPhone());
            fAppointment.setValue(patientForAppointment.getDateTwo());



            if(patientForAppointment.getGender().equals("Female")){
                iFemale.fire();
                gender = "Female";
            }else {
                iMale.fire();
                gender = "Male";
            }

        }



        iFemale.setToggleGroup(group);
        iMale.setToggleGroup(group);
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {

                if (group.getSelectedToggle() != null) {
                    RadioButton button = (RadioButton) group.getSelectedToggle();
                    gender=button.getText();
                    System.out.println(gender);
                }
            }
        });

    }

    public void onExit(ActionEvent actionEvent) {

    }

    public void onADDList(ActionEvent actionEvent) {

            observableList.remove(patientForAppointment);
            patientForAppointment.setFirstName(fFirstName.getText());
            patientForAppointment.setLastName(fLastName.getText());
            patientForAppointment.setPhone( fPhone.getText());
            patientForAppointment.setGender(gender);
            db.updateAppointment(patientForAppointment);
            observableList.add(patientForAppointment);



    }
}
