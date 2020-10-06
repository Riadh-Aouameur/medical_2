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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ControllerMModify implements Initializable {
    public ToggleGroup group;
    public TextField fFirstName;
    public TextField fLastName;
    public TextField fPhone;
    public RadioButton iFemale;
    public RadioButton iMale;
    public AnchorPane root;
    public AnchorPane paneMassage;
    public Label message;
    private double x;
    private double y;
    int index;
    public DatePicker fAppointment;

    String gender ;
    Db db = new Db();

    ObservableList<PatientForAppointment> observableList;
 PatientForAppointment patientForAppointment;

    public ControllerMModify(ObservableList<PatientForAppointment> observableList, PatientForAppointment patientForAppointment,int index) {
        this.observableList = observableList;
        this.patientForAppointment = patientForAppointment;
        this.index = index;
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
        group = new ToggleGroup();

        if(patientForAppointment!=null){
            fFirstName.setText(patientForAppointment.getFirstName());
            fLastName.setText(patientForAppointment.getLastName());
            fPhone.setText(patientForAppointment.getPhone());
            fAppointment.setValue(patientForAppointment.getDateOne());



            if(patientForAppointment.getGender().equals("Female")){
                iFemale.fire();
                gender = "Female";
            }else  {
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
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();

    }


    public void onADDList(ActionEvent actionEvent) {


        if (fAppointment.getValue() != null&& !fFirstName.getText().isEmpty()&& !fLastName.getText().isEmpty()){
            if (fAppointment.getValue().isAfter(LocalDate.now())){
                Boolean a=db.updateTestAppointment(fAppointment.getValue());
                if(!a){
                    message.setText("You Reach The Patients Daily Limit");
                    paneMassage.setVisible(true);
                    return;
                }else {

                    patientForAppointment.setDateOne(fAppointment.getValue());
                    patientForAppointment.setFirstName(fFirstName.getText());
                    patientForAppointment.setLastName(fLastName.getText());
                    patientForAppointment.setPhone( fPhone.getText());
                    patientForAppointment.setGender(gender);

                    db.updateAppointment(patientForAppointment);
                    observableList.remove(patientForAppointment);
                    observableList.add(index,patientForAppointment);
                    message.setText("Successful");
                }

            }
            else {
                message.setText("This Date Not Available");
                paneMassage.setVisible(true);
                return;

            }
        }else {
            message.setText("Must Fill Empty Fields");
            paneMassage.setVisible(true);

        }






    }
}
