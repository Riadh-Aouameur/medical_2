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

public class ControllerModify implements Initializable {
    public ToggleGroup group;
    public TextField fFirstName;
    public TextField fLastName;
    public TextField fPhone;
    public RadioButton iFemale;
    public RadioButton iMale;
    public TextField fNumber;
    String gender = "Female";
    Db db = new Db();

    ObservableList<PatientForWaitingRoom> observableList;
    PatientForWaitingRoom patientForWaitingRoom;

    public ControllerModify(ObservableList<PatientForWaitingRoom> observableList ,PatientForWaitingRoom patientForWaitingRoom) {
        this.observableList = observableList;
        this.patientForWaitingRoom=patientForWaitingRoom;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        group = new ToggleGroup();
        iFemale.fire();
        if(patientForWaitingRoom!=null){
            fFirstName.setText(patientForWaitingRoom.getFirstName());
            fLastName.setText(patientForWaitingRoom.getLastName());
            fPhone.setText(patientForWaitingRoom.getPhone());
            fNumber.setText(String.valueOf(patientForWaitingRoom.getNumber()));
            fNumber.setEditable(false);
            if(patientForWaitingRoom.getGender().equals("Female")){
                iFemale.fire();
                gender = "Female";
            }
            else {

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

            observableList.remove(patientForWaitingRoom);
            patientForWaitingRoom.setFirstName(fFirstName.getText());
            patientForWaitingRoom.setLastName(fLastName.getText());
            patientForWaitingRoom.setPhone( fPhone.getText());
            patientForWaitingRoom.setGender(gender);
            observableList.add(patientForWaitingRoom.getNumber()-1,patientForWaitingRoom);
            db.update(patientForWaitingRoom);




    }
}
