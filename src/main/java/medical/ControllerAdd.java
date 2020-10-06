package medical;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import medical.DataBase.Db;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAdd implements Initializable {

    public ToggleGroup group;
    public TextField fFirstName;
    public TextField fLastName;
    public TextField fPhone;
    public AnchorPane root;
    public RadioButton iFemale;
    public RadioButton iMale;
    public TextField fNumber;
    public AnchorPane paneMassage;
    public Label message;


    String firstName;
    String lastName;
    String phone ="";
    String status ="Waiting";
    int nb;
    String gender = "Female";
    Db db = new Db();
    private double x;
   private double y;



    ObservableList<PatientForWaitingRoom> observableList;

    public ControllerAdd(ObservableList<PatientForWaitingRoom> observableList ) {
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

            if(db.update(p)){
                observableList.add(p);
                fFirstName.setText("");
                fLastName.setText("");
                fPhone.setText("");
                fNumber.setText(String.valueOf(observableList.size()+1));
                message.setText("Successful");

                firstName= null;
                lastName= null;
                phone ="";
            }else {
                message.setText("You Reach The Patients Daily Limit");
                paneMassage.setVisible(true);
            }

        }
        else {
            message.setText("Must Fill Empty Fields");
            paneMassage.setVisible(true);

        }

    }

    public void onExit(ActionEvent actionEvent) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();

    }
}
