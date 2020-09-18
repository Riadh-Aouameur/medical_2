package medical;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import medical.DataBase.Db;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAppointment implements Initializable {



    public ListView <PatientForAppointment>waitingList;
    Db db = new Db();

    ObservableList<PatientForAppointment> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        observableList.setAll(db.getAppointmentAll());
        waitingList.setItems(observableList);
        waitingList.setCellFactory(pp->new MyListCell_2());
        
    }

    Stage primaryStage = new Stage();;
    public void onAdd(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("aAdd.fxml"));
        loader.setControllerFactory(e->{

            return new ControllerAAdd(observableList);
        });





        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();
    }
    Stage primaryStage4= new Stage();
     public void onDeletePatient(ActionEvent actionEvent) throws IOException {
        if(!observableList.isEmpty()){
            PatientForAppointment patient= waitingList.getSelectionModel().getSelectedItem();
           waitingList.getSelectionModel().clearSelection();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ddelete.fxml"));
            loader.setControllerFactory(e->{

                return new ControllerDDelete(observableList,patient);
            });





            primaryStage4.setTitle("Hello World!");
            primaryStage4.setScene(new Scene(loader.load()));
            primaryStage4.show();
        }


    }
    Stage primaryStage5= new Stage();
    public void onOpenModify(ActionEvent actionEvent) throws IOException {
        PatientForAppointment patient= waitingList.getSelectionModel().getSelectedItem();
        waitingList.getSelectionModel().clearSelection();
        if(!(observableList.isEmpty()||patient==null)){

            FXMLLoader loader = new FXMLLoader(getClass().getResource("mmodify.fxml"));
            loader.setControllerFactory(e->{

                return new ControllerMModify(observableList,patient);
            });





            primaryStage5.setTitle("Hello World!");
            primaryStage5.setScene(new Scene(loader.load()));
            primaryStage5.show();
        }

    }
}
