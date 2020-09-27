package medical;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import medical.DataBase.Db;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerModify implements Initializable {
    public ToggleGroup group;
    public AnchorPane root;
    public TextField fFirstName;
    public TextField fLastName;
    public TextField fPhone;
    public RadioButton iFemale;
    public RadioButton iMale;
    public TextField fNumber;
    private double x;
    private double y;

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
        fLastName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                fLastName.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        fFirstName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                fFirstName.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        root.setOnMousePressed(mouseEvent -> {
            x=mouseEvent.getSceneX();
            y=mouseEvent.getSceneY();
        });
        root.setOnMouseDragged(e->{
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setX(e.getScreenX()-x);
            stage.setY(e.getScreenY()-y);
        });
        group = new ToggleGroup();

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
        fPhone.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    // Check if the new character is greater than LIMIT
                    if (fPhone.getText().length() >= 10) {

                        // if it's 11th character then just setText to previous
                        // one
                        fPhone.setText(fPhone.getText().substring(0, 10));
                    }
                }
            }
        });
        fPhone.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    fPhone.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public void onExit(ActionEvent actionEvent) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();

    }

    public void onADDList(ActionEvent actionEvent) {

            observableList.remove(patientForWaitingRoom);
            patientForWaitingRoom.setFirstName(fFirstName.getText());
            patientForWaitingRoom.setLastName(fLastName.getText());
            patientForWaitingRoom.setPhone( fPhone.getText());
            patientForWaitingRoom.setGender(gender);
            observableList.add(patientForWaitingRoom.getNumber()-1,patientForWaitingRoom);
            db.update(patientForWaitingRoom);


//Todo Message Error

    }
}
