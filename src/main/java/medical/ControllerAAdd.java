package medical;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import medical.DataBase.Db;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ControllerAAdd implements Initializable {
    private double x;
    private double y;
    public AnchorPane root;
    public TextField fFirstName;
    public TextField fLastName;
    public TextField fPhone;
    public RadioButton iMale;
    public RadioButton iFemale;

    public DatePicker fAppointment;

    public ToggleGroup group;
    String firstName;
    String lastName;
    String phone ="";
    LocalDate date;
    String status ="Waiting";
    int nb;
    String gender = "Female";
    ObservableList<PatientForAppointment> observableList;
    Db db = new Db();

    public ControllerAAdd(ObservableList<PatientForAppointment> observableList ) {
        this.observableList = observableList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        root.setOnMousePressed(mouseEvent -> {
            x=mouseEvent.getSceneX();
            y=mouseEvent.getSceneY();
        });
        root.setOnMouseDragged(e->{
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setX(e.getScreenX()-x);
            stage.setY(e.getScreenY()-y);
        });
        nb = observableList.size();


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
    }

    public void onExit(ActionEvent actionEvent) {
    }

    public void onADDList(ActionEvent actionEvent) {
        if (!(fFirstName.getText().isEmpty())){
            firstName= fFirstName.getText();
        }
        if (!(fLastName.getText().isEmpty())){
            lastName= fLastName.getText();
        }
        if (!(fPhone.getText().isEmpty())){
            phone= fPhone.getText();
        }
        if (!(fAppointment.getValue()== null)){

                date= fAppointment.getValue();

        }
        if (firstName != null && lastName != null&& date != null){
           PatientForAppointment p =new PatientForAppointment(firstName,lastName,gender,phone,date,LocalDate.now(),status);
        if(  db.InsertAppointment(p)){
            observableList.add(p);
            firstName = null;
            lastName = null;
            phone ="";
            date = null;
            fFirstName.clear();
             fLastName.clear();
             fPhone.clear();
             fAppointment.setValue(null);
        }


        }
        else {
            System.out.println("error");

        }

    }
}
