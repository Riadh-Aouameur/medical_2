package medical;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import medical.DataBase.Db;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ControllerAppointment implements Initializable {



    public ListView <PatientForAppointment>waitingList;
    public RadioButton rPass;
    public RadioButton rWaiting;
    public RadioButton rAll;
    public ToggleGroup group;
    Db db = new Db();

    ObservableList<PatientForAppointment> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        observableList.setAll(db.getAppointmentAll());
        rAll.fire();
        waitingList.setItems(observableList);
        waitingList.setCellFactory(pp->new MyListCell_2());

        group = new ToggleGroup();

        rAll.setToggleGroup(group);
        rPass.setToggleGroup(group);
        rWaiting.setToggleGroup(group);
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {

                if (group.getSelectedToggle() != null) {
                    RadioButton button = (RadioButton) group.getSelectedToggle();
                    String status=button.getText();
                    if(status.equals("All")){
                        observableList.setAll(db.getAppointmentAll());
                    }
                    else if (status.equals("Waiting")){
                        observableList.setAll(db.getAppointmentWaiting());
                    }else {
                        observableList.setAll(db.getAppointmentPass());
                    }


                    System.out.println(LocalDate.now().minusDays(1));

                }
            }
        });

    }

    Stage primaryStage ;
    public void onAdd(ActionEvent actionEvent) throws IOException {
        try {


if (primaryStage==null){
    FXMLLoader loader = new FXMLLoader(getClass().getResource("aAdd.fxml"));
    loader.setControllerFactory(e->{

        return new ControllerAAdd(observableList);
    });


    primaryStage = new Stage();

    primaryStage.initStyle(StageStyle.TRANSPARENT);
    Image icon = new Image(getClass().getResourceAsStream("img/plus.png"));
    primaryStage.getIcons().add(icon);
    primaryStage.setTitle("Add");
    primaryStage.setScene(new Scene(loader.load()));
    primaryStage.show();
}else {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("aAdd.fxml"));
    loader.setControllerFactory(e->{

        return new ControllerAAdd(observableList);
    });


    primaryStage.close();
    primaryStage = new Stage();
    primaryStage.initStyle(StageStyle.TRANSPARENT);
    Image icon = new Image(getClass().getResourceAsStream("img/plus.png"));
    primaryStage.getIcons().add(icon);
    primaryStage.setTitle("Add");
    primaryStage.setScene(new Scene(loader.load()));
    primaryStage.show();
}
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setTitle("ERROR");
            alert.setHeaderText("");
            alert.showAndWait();
        }
    }




    Stage primaryStage4;
     public void onDeletePatient(ActionEvent actionEvent) throws IOException {
         try {


         if (primaryStage4 == null){
             if(!observableList.isEmpty()){
                 PatientForAppointment patient= waitingList.getSelectionModel().getSelectedItem();
                 waitingList.getSelectionModel().clearSelection();
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("ddelete.fxml"));
                 loader.setControllerFactory(e->{

                     return new ControllerDDelete(observableList,patient);
                 });



                  primaryStage4= new Stage();
                 primaryStage4.initStyle(StageStyle.TRANSPARENT);
                 Image icon = new Image(getClass().getResourceAsStream("img/pencil.png"));
                 primaryStage4.getIcons().add(icon);
                 primaryStage4.setTitle("Modify");

                 primaryStage4.setScene(new Scene(loader.load()));
                 primaryStage4.show();
             }
         }else {
             if(!observableList.isEmpty()){
                 PatientForAppointment patient= waitingList.getSelectionModel().getSelectedItem();
                 waitingList.getSelectionModel().clearSelection();
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("ddelete.fxml"));
                 loader.setControllerFactory(e->{

                     return new ControllerDDelete(observableList,patient);
                 });


                 primaryStage4.close();
                 primaryStage4 = new Stage();
                 primaryStage4.initStyle(StageStyle.TRANSPARENT);
                 Image icon = new Image(getClass().getResourceAsStream("img/pencil.png"));
                 primaryStage4.getIcons().add(icon);
                 primaryStage4.setTitle("Modify");

                 primaryStage4.setScene(new Scene(loader.load()));
                 primaryStage4.show();
             }
         }
         }catch (Exception e){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText(e.getMessage());
             alert.setTitle("ERROR");
             alert.setHeaderText("");
             alert.showAndWait();
         }



    }


    Stage primaryStage5;
    public void onOpenModify(ActionEvent actionEvent) throws IOException {
        try {


        if (primaryStage5==null){
            PatientForAppointment patient= waitingList.getSelectionModel().getSelectedItem();
            int index= waitingList.getSelectionModel().getSelectedIndex();
            waitingList.getSelectionModel().clearSelection();
            if(!(observableList.isEmpty()||patient==null)){

                FXMLLoader loader = new FXMLLoader(getClass().getResource("mmodify.fxml"));
                loader.setControllerFactory(e->{

                    return new ControllerMModify(observableList,patient,index);
                });





                primaryStage5 = new Stage();
                primaryStage5.initStyle(StageStyle.TRANSPARENT);
                Image icon = new Image(getClass().getResourceAsStream("img/pencil.png"));
                primaryStage5.getIcons().add(icon);
                primaryStage5.setTitle("Modify");
                primaryStage5.setScene(new Scene(loader.load()));
                primaryStage5.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Select Patient");
                alert.setTitle("INFORMATION");
                alert.setHeaderText("");
                alert.showAndWait();

            }

        }

        else {

            PatientForAppointment patient= waitingList.getSelectionModel().getSelectedItem();
            int index= waitingList.getSelectionModel().getSelectedIndex();
            waitingList.getSelectionModel().clearSelection();
            if(!(observableList.isEmpty()||patient==null)){

                FXMLLoader loader = new FXMLLoader(getClass().getResource("mmodify.fxml"));
                loader.setControllerFactory(e->{

                    return new ControllerMModify(observableList,patient,index);
                });




                primaryStage5.close();
                primaryStage5 = new Stage();
                primaryStage5.initStyle(StageStyle.TRANSPARENT);
                Image icon = new Image(getClass().getResourceAsStream("img/pencil.png"));
                primaryStage5.getIcons().add(icon);
                primaryStage5.setTitle("Modify");
                primaryStage5.setScene(new Scene(loader.load()));
                primaryStage5.show();
            } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Select Patient");
                    alert.setTitle("INFORMATION");
                    alert.setHeaderText("");
                    alert.showAndWait();

                }
            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setTitle("ERROR");
            alert.setHeaderText("");
            alert.showAndWait();
        }
        }


    }

