package medical;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import medical.DataBase.Db;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Home implements Initializable {



    public BorderPane root;
    public ListView <PatientForWaitingRoom>wattingList;
    ObservableList<PatientForWaitingRoom> observableList = FXCollections.observableArrayList();
    private double x;
    private double y;





        Db db = new Db();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        db.setStatus();
        root.setOnMousePressed(mouseEvent -> {
            x=mouseEvent.getSceneX();
            y=mouseEvent.getSceneY();
        });
        root.setOnMouseDragged(e->{
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setX(e.getScreenX()-x);
            stage.setY(e.getScreenY()-y);
        });
        observableList.setAll(db.getPatientData());
        if (observableList!= null){
            wattingList.setItems(observableList);
            wattingList.setCellFactory(param -> new MyListCell_1());
        }



    }




    public void onMinButton(ActionEvent actionEvent) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setIconified(true);

    }

    public void onMaxButton(ActionEvent actionEvent) {
        Stage stage = (Stage) root.getScene().getWindow();
        if(stage.isMaximized()){
            stage.setMaximized(false);
        }else{
            stage.setMaximized(true);
        }


    }



    public void onClose(ActionEvent actionEvent) {
        System.exit(0);
    }
        public void onCalculater(ActionEvent actionEvent) throws IOException {
        String params ;
        params = "C:\\Windows\\System32\\calc.exe";

        Runtime.getRuntime().exec(params);
    }

    public void onOpenNotes(ActionEvent actionEvent) throws IOException {
        String params ;
        params = "C:\\Program Files\\Notes 0.9.0\\Notes.exe";

        Runtime.getRuntime().exec(params);

    }

    public void onOpenCalender(ActionEvent actionEvent) {

        try {
            String params ;
            params = "C:\\Program Files (x86)\\Calendrier\\Cld2000.exe";


            Runtime.getRuntime().exec(params);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    Stage primaryStage3 ;
    public void onAdd(ActionEvent actionEvent) throws IOException {
            if(primaryStage3==null){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("add.fxml"));
                loader.setControllerFactory(e->{

                    return new ControllerAdd(observableList);
                });


                primaryStage3 = new Stage();
                primaryStage3.initStyle(StageStyle.TRANSPARENT);
                Image icon = new Image(getClass().getResourceAsStream("img/plus.png"));
                primaryStage3.getIcons().add(icon);
                primaryStage3.setTitle("Add");
                primaryStage3.setScene(new Scene(loader.load()));
                primaryStage3.show();
            }else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("add.fxml"));
                loader.setControllerFactory(e->{

                    return new ControllerAdd(observableList);
                });

                primaryStage3.close();
                primaryStage3 = new Stage();
                Image icon = new Image(getClass().getResourceAsStream("img/plus.png"));
                primaryStage3.getIcons().add(icon);
                primaryStage3.setTitle("Add");
                primaryStage3.initStyle(StageStyle.TRANSPARENT);
                primaryStage3.setScene(new Scene(loader.load()));
                primaryStage3.show();
            }







    }
    Stage primaryStage4;
    public void onDeletePatient(ActionEvent actionEvent) throws IOException {

        if(primaryStage4==null){
            if(!observableList.isEmpty()){
                PatientForWaitingRoom patient= wattingList.getSelectionModel().getSelectedItem();
                wattingList.getSelectionModel().clearSelection();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("delete.fxml"));
                loader.setControllerFactory(e->{

                    return new ControllerDelete(observableList,patient);
                });




                primaryStage4 =new Stage();
                primaryStage4.initStyle(StageStyle.TRANSPARENT);
                Image icon = new Image(getClass().getResourceAsStream("img/delete1.png"));
                primaryStage4.getIcons().add(icon);
                primaryStage4.setTitle("Delete");
                primaryStage4.setScene(new Scene(loader.load()));
                primaryStage4.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("");
                alert.setTitle("");
                alert.setHeaderText("");
                alert.showAndWait();
            }
        }else {
            if(!observableList.isEmpty()){
                PatientForWaitingRoom patient= wattingList.getSelectionModel().getSelectedItem();
                wattingList.getSelectionModel().clearSelection();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("delete.fxml"));
                loader.setControllerFactory(e->{

                    return new ControllerDelete(observableList,patient);
                });

                primaryStage4.close();
                primaryStage4 = new Stage();
                primaryStage4.initStyle(StageStyle.TRANSPARENT);
                Image icon = new Image(getClass().getResourceAsStream("img/plus.png"));
                primaryStage4.getIcons().add(icon);
                primaryStage4.setTitle("Delete");
                primaryStage4.setScene(new Scene(loader.load()));
                primaryStage4.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("");
                alert.setTitle("");
                alert.setHeaderText("");
                alert.showAndWait();
            }
        }



    }
    Stage primary ;

    public void onAppointment(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("appointment .fxml"));
         primary = new Stage();
         primary.initStyle(StageStyle.UTILITY);
        Image icon = new Image(getClass().getResourceAsStream("img/appointment.png"));
        primary.getIcons().add(icon);
         primary.setTitle("Appointment");
         primary.setScene(new Scene(loader.load()));
         primary.show();
    }
    Stage primaryStage ;
    public void onOpenModify(ActionEvent actionEvent) throws IOException {
if (primaryStage==null){
    if(!observableList.isEmpty()){
        PatientForWaitingRoom patient= wattingList.getSelectionModel().getSelectedItem();
        wattingList.getSelectionModel().clearSelection();

        if (patient==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("");
            alert.setTitle("");
            alert.setHeaderText("");
            alert.showAndWait();
            //Todo message Error
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modify.fxml"));

            loader.setControllerFactory(e->{
                return new ControllerModify(observableList,patient);
            });





            primaryStage = new Stage();
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            Image icon = new Image(getClass().getResourceAsStream("img/pencil.png"));
            primaryStage.getIcons().add(icon);
            primaryStage.setTitle("Modify");
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.show();
        }

    }else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("");
        alert.setTitle("");
        alert.setHeaderText("");
        //Todo Message Error
        alert.showAndWait();
    }
} else {
    primaryStage.close();
    if(!observableList.isEmpty()){
        PatientForWaitingRoom patient= wattingList.getSelectionModel().getSelectedItem();
        wattingList.getSelectionModel().clearSelection();

        if (patient==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("");
            alert.setTitle("");
            alert.setHeaderText("");
            alert.showAndWait();
            //Todo message Error
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modify.fxml"));

            loader.setControllerFactory(e->{
                return new ControllerModify(observableList,patient);
            });





            primaryStage = new Stage();
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            Image icon = new Image(getClass().getResourceAsStream("img/pencil.png"));
            primaryStage.getIcons().add(icon);
            primaryStage.setTitle("Modify");
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.show();
        }

    }else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("");
        alert.setTitle("");
        alert.setHeaderText("");
        alert.showAndWait();
        //Todo Message Error
    }
}



    }
    Stage primaryStage2;
    public void onConsult(ActionEvent actionEvent) throws IOException {
        if (primaryStage2== null){
            if(!observableList.isEmpty()){
                PatientForWaitingRoom patient= wattingList.getSelectionModel().getSelectedItem();
                wattingList.getSelectionModel().clearSelection();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("consult.fxml"));
                loader.setControllerFactory(e->{

                    return new ControllerConsult(observableList,patient);
                });




                primaryStage2 = new Stage();
                primaryStage2.initStyle(StageStyle.TRANSPARENT);
                Image icon = new Image(getClass().getResourceAsStream("img/login.png"));
                primaryStage2.getIcons().add(icon);
                primaryStage2.setTitle("Consult");
                primaryStage2.setScene(new Scene(loader.load()));
                primaryStage2.show();
            }else  {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("");
                alert.setTitle("");
                alert.setHeaderText("");
                alert.showAndWait();
                //Todo Message Error
            }
        }else {
            if(!observableList.isEmpty()){
                PatientForWaitingRoom patient= wattingList.getSelectionModel().getSelectedItem();
                wattingList.getSelectionModel().clearSelection();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("consult.fxml"));
                loader.setControllerFactory(e->{

                    return new ControllerConsult(observableList,patient);
                });



                primaryStage2.close();
                primaryStage2 = new Stage();
                primaryStage2.initStyle(StageStyle.TRANSPARENT);
                Image icon = new Image(getClass().getResourceAsStream("img/login.png"));
                primaryStage2.getIcons().add(icon);
                primaryStage2.setTitle("Consult");
                primaryStage2.setScene(new Scene(loader.load()));
                primaryStage2.show();
            }else  {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("");
                alert.setTitle("");
                alert.setHeaderText("");
                alert.showAndWait();
                //Todo Message Error
            }
        }


    }

    public void onClear(ActionEvent actionEvent) {
        if(!observableList.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("");
            alert.setTitle("");
            alert.setHeaderText("");

            ButtonType buttonCancel=  new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getDialogPane().getButtonTypes().add(buttonCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
                System.out.println("Clear");

                db.superClear(observableList.size());
                observableList.clear();

            }
        }else  {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("");
            alert.setTitle("");
            alert.setHeaderText("");
            alert.showAndWait();
        }


    }
    Stage primaryStage5 ;
    public void onOpenListPatientOfWaitingRoom(ActionEvent actionEvent) throws IOException {
if(primaryStage == null){
    FXMLLoader loader = new FXMLLoader(getClass().getResource("A1.fxml"));
    loader.setControllerFactory(e->{

        return new ControllerA1(observableList);
    });



    primaryStage5 = new Stage();

    Image icon = new Image(getClass().getResourceAsStream("img/appt.png"));
    primaryStage5.getIcons().add(icon);
    primaryStage5.setTitle("Today's appointments");

    primaryStage5.setScene(new Scene(loader.load()));
    primaryStage5.show();


}
else {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("A1.fxml"));
    loader.setControllerFactory(e->{

        return new ControllerA1(observableList);
    });


    primaryStage5.close();
    primaryStage5 = new Stage();

    Image icon = new Image(getClass().getResourceAsStream("img/login.png"));
    primaryStage5.getIcons().add(icon);
    primaryStage5.setTitle("Consult");
    primaryStage5.setTitle("Hello World!");
    primaryStage5.setScene(new Scene(loader.load()));
    primaryStage5.show();


}



    }
    public void onOpenAbout(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("about.fxml"));


        Stage stage =   new Stage();
        try {
            stage.setScene(new Scene(loader.load()));



        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("About");
        stage.show();

    }
}
