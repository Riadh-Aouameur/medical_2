package medical;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import medical.DataBase.Db;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ControllerA1 implements Initializable {
    public ListView <PatientForAppointment> list;
    ObservableList<PatientForAppointment> observableList;
    ObservableList<PatientForWaitingRoom> observableList1;

    public ControllerA1(ObservableList<PatientForWaitingRoom> observableList1) {
        this.observableList1 = observableList1;
    }
Db db =new Db();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         System.out.println(LocalDate.now());

        observableList = FXCollections.observableArrayList(db.getAppointmentToday());
        list.setItems(observableList);
        list.setCellFactory(param -> new MyListCell_3());
    }

    public void onSet(ActionEvent actionEvent) {
       PatientForAppointment p =  list.getSelectionModel().getSelectedItem();
       if(p!=null){
         PatientForWaitingRoom pp=  new PatientForWaitingRoom(p.getFirstName(), p.getLastName(), p.getGender(), p.getPhone(),observableList1.size()+1, p.getStatus());

           for (PatientForWaitingRoom patientForWaitingRoom : observableList1) {
               if (patientForWaitingRoom.getFirstName().equals(pp.getFirstName())) {
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setContentText("");
                   alert.setTitle("");
                   alert.setHeaderText("");
                   alert.showAndWait();
                   return;
               }
           }
           observableList1.add(pp);
           db.update(pp);

       }
    }

    public void onSetAll(ActionEvent actionEvent) {
        if(!observableList1.isEmpty()){
            for (PatientForAppointment p : observableList){
                PatientForWaitingRoom pp=  new PatientForWaitingRoom(p.getFirstName(), p.getLastName(), p.getGender(), p.getPhone(),observableList1.size()+1, p.getStatus());
                for (PatientForWaitingRoom patientForWaitingRoom : observableList1) {
                    if (patientForWaitingRoom.getFirstName().equals(pp.getFirstName())) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("");
                        alert.setTitle("");
                        alert.setHeaderText("");
                        alert.showAndWait();
                    }
                    else {
                        observableList1.add(pp);
                        db.update(pp);
                    }
                }
            }
        }else {

            for (PatientForAppointment p : observableList){
                PatientForWaitingRoom pp=  new PatientForWaitingRoom(p.getFirstName(), p.getLastName(), p.getGender(), p.getPhone(),observableList1.size()+1, p.getStatus());
                observableList1.add(pp);
            }

        }

    }
}
