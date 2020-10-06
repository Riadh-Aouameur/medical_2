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
    public AnchorPane paneMassage;
    public Label message;
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
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();

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
            if (!date.isAfter(LocalDate.now())){
                message.setText("This Date Not Available");
                paneMassage.setVisible(true);
                return;
            }
           PatientForAppointment p =new PatientForAppointment(firstName,lastName,gender,phone,date,LocalDate.now(),status);
        if(db.InsertAppointment(p)){
            observableList.add(p);
            message.setText("Successful");
            firstName = null;
            lastName = null;
            phone ="";
            date = null;
            fFirstName.clear();
             fLastName.clear();
             fPhone.clear();
             fAppointment.setValue(null);
        }else {
            message.setText("You Reach The Patients Daily Limit");
            paneMassage.setVisible(true);
            return;
        }


        }
        else {
            message.setText("Must Fill Empty Fields");
            paneMassage.setVisible(true);
            return;

        }

    }
}
