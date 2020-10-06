package medical;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import medical.DataBase.Db;


import java.net.URL;
import java.util.ResourceBundle;

public class ControllerDelete  implements Initializable {
    public AnchorPane paneMassage;
    public Label message;
    @FXML
   Label fPatient;




    public ImageView img;

    public AnchorPane root;
    private double x;
    private double y;

    public Label fStats;
    public TextField fnb;
    public Label fPhone;


    ObservableList<PatientForWaitingRoom> observableList;
    PatientForWaitingRoom patientForWaitingRoom;
    Db db ;

    public ControllerDelete(ObservableList<PatientForWaitingRoom> observableList ,PatientForWaitingRoom patientForWaitingRoom) {
        this.observableList = observableList;
        this.patientForWaitingRoom=patientForWaitingRoom;
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
        db = new Db();
        if (patientForWaitingRoom!=null){
            fPatient.setText("Patient : "+patientForWaitingRoom.getFirstName()+" "+patientForWaitingRoom.getLastName());
            fPhone.setText("Phone : "+patientForWaitingRoom.getPhone());
            fStats.setText("Status : "+patientForWaitingRoom.getStatus());
            if (patientForWaitingRoom.getGender().equals("Female")){
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
        if (patientForWaitingRoom!=null){
            observableList.remove(patientForWaitingRoom);
            db.clearWaiting(patientForWaitingRoom.getNumber());

            for (int i = patientForWaitingRoom.getNumber()-1;i<observableList.size();i++){
                observableList.get(i).setNumber(observableList.get(i).getNumber()-1);
                    db.update(observableList.get(i));
            }
            message.setText("Successful");
            db.clearWaiting(observableList.size()+1);

            patientForWaitingRoom = null;
        }else{
            message.setText("No Patient Selected");
            paneMassage.setVisible(true);
        }

    }

    public void onExit(ActionEvent actionEvent) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();

    }

    public void onSelect(ActionEvent actionEvent) {
        int a;
        if(fnb.getText().isEmpty()){
            message.setText("Enter Your Patient ID");
            paneMassage.setVisible(true);
        }
        else{
            try {
                a = Integer.parseInt(fnb.getText());
                this.patientForWaitingRoom =  observableList.get(a-1);
                fPatient.setText("Patient : "+patientForWaitingRoom.getFirstName()+" "+patientForWaitingRoom.getLastName());
                fPhone.setText("Phone : "+patientForWaitingRoom.getPhone());
                fStats.setText("Status : "+patientForWaitingRoom.getStatus());
                if (patientForWaitingRoom.getGender().equals("Female")){
                    Image imProfile = new Image(getClass().getResourceAsStream("img/femalepatient.png"));
                    img.setImage(imProfile);
                }else {
                    Image imProfile = new Image(getClass().getResourceAsStream("img/patient.png"));
                    img.setImage(imProfile);
                }


            }catch (Exception e){
                message.setText("Out Of Rang");
                paneMassage.setVisible(true);

            }

        }
    }


}
