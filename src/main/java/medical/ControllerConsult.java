package medical;

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


public class ControllerConsult  implements Initializable {
    public TextField fnb;
    public Label fPhone;
    public ImageView img;
    public Label fPatient;
    public Label fStats;
    public Label lab1;
    ObservableList<PatientForWaitingRoom> observableList;
    PatientForWaitingRoom patientForWaitingRoom;

    public ControllerConsult(ObservableList<PatientForWaitingRoom> observableList, PatientForWaitingRoom patientForWaitingRoom) {
        this.observableList = observableList;
        this.patientForWaitingRoom = patientForWaitingRoom;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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


    }

    public void onConsultPatient(ActionEvent actionEvent) {

        if (patientForWaitingRoom!=null){
            for (int i = 0;i<observableList.size();i++){
                if(observableList.get(i).getStatus().equals("Consult")){
                    observableList.get(i).setStatus("Pass");
                }
            }
            observableList.remove(patientForWaitingRoom);
            patientForWaitingRoom.setStatus("Consult");
            observableList.add(patientForWaitingRoom.getNumber()-1,patientForWaitingRoom);
            fStats.setText("Status : "+patientForWaitingRoom.getStatus());
            Db db = new Db();
            db.superUpdate(observableList);

        }

    }

    public void onExit(ActionEvent actionEvent) {
    }

    public void onSelect(ActionEvent actionEvent) {
        int a;
        if(fnb.getText().isEmpty()){
            lab1.setText("Enter your patient number");
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
                lab1.setText("Something is wrong");
            }

        }
    }
}
