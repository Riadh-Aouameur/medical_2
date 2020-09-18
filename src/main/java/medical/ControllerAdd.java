package medical;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import medical.DataBase.Db;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAdd implements Initializable {

    public ToggleGroup group;
    public TextField fFirstName;
    public TextField fLastName;
    public TextField fPhone;
    public RadioButton iFemale;
    public RadioButton iMale;
    public TextField fNumber;

    String firstName;
    String lastName;
    String phone ="";
    String status ="Waiting";
    int nb;
    String gender = "Female";
    Db db = new Db();



    ObservableList<PatientForWaitingRoom> observableList;

    public ControllerAdd(ObservableList<PatientForWaitingRoom> observableList ) {
        this.observableList = observableList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nb = observableList.size();
        System.out.println("nb -= "+nb);
        fNumber.setText(String.valueOf(observableList.size()+1));
        group = new ToggleGroup();
        iFemale.fire();
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

    public void onADDList(ActionEvent actionEvent) {
        nb = observableList.size();
        System.out.println("nb -= "+nb);
        if (!(fFirstName.getText().isEmpty())){
            firstName= fFirstName.getText();
        }
        if (!(fLastName.getText().isEmpty())){
            lastName= fLastName.getText();
        }
        if (!(fPhone.getText().isEmpty())){
            phone= fPhone.getText();
        }


        if (firstName != null && lastName != null){
            PatientForWaitingRoom p =new PatientForWaitingRoom(firstName,lastName,gender,phone,nb+1,status);
            observableList.add(p);
            db.update(p);
            fFirstName.setText("");
            fLastName.setText("");
            fPhone.setText("");
            fNumber.setText(String.valueOf(observableList.size()+1));

            firstName= null;
            lastName= null;
            phone ="";



        }
        else {
            System.out.println("error");

        }

    }

    public void onExit(ActionEvent actionEvent) {
        System.exit(0);

    }
}
