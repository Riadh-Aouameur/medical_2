package medical;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;
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
    Stage primaryStage = new Stage();
    Stage primaryStage2 = new Stage();
    Stage primaryStage3 = new Stage();
    Stage primaryStage4 = new Stage();
    Stage primaryStage5 = new Stage();
        Db db = new Db();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    public void onAdd(ActionEvent actionEvent) throws IOException {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("add.fxml"));
            loader.setControllerFactory(e->{

                return new ControllerAdd(observableList);
            });





            primaryStage3.setTitle("Hello World!");
            primaryStage3.setScene(new Scene(loader.load()));
            primaryStage3.show();






    }

    public void onDeletePatient(ActionEvent actionEvent) throws IOException {
        if(!observableList.isEmpty()){
            PatientForWaitingRoom patient= wattingList.getSelectionModel().getSelectedItem();
            wattingList.getSelectionModel().clearSelection();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("delete.fxml"));
            loader.setControllerFactory(e->{

                return new ControllerDelete(observableList,patient);
            });





            primaryStage4.setTitle("Hello World!");
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

    public void onAppointment(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("appointment .fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();
    }

    public void onOpenModify(ActionEvent actionEvent) throws IOException {

        if(!observableList.isEmpty()){
            PatientForWaitingRoom patient= wattingList.getSelectionModel().getSelectedItem();
            wattingList.getSelectionModel().clearSelection();

            if (patient==null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("");
                alert.setTitle("");
                alert.setHeaderText("");
                alert.showAndWait();
            }else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("modify.fxml"));

                loader.setControllerFactory(e->{
                    return new ControllerModify(observableList,patient);
                });





                primaryStage.setTitle("Hello World!");
                primaryStage.setScene(new Scene(loader.load()));
                primaryStage.show();
            }

        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("");
            alert.setTitle("");
            alert.setHeaderText("");
            alert.showAndWait();
        }


    }

    public void onConsult(ActionEvent actionEvent) throws IOException {
        if(!observableList.isEmpty()){
            PatientForWaitingRoom patient= wattingList.getSelectionModel().getSelectedItem();
            wattingList.getSelectionModel().clearSelection();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("consult.fxml"));
            loader.setControllerFactory(e->{

                return new ControllerConsult(observableList,patient);
            });





            primaryStage2.setTitle("Hello World!");
            primaryStage2.setScene(new Scene(loader.load()));
            primaryStage2.show();
        }else  {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("");
            alert.setTitle("");
            alert.setHeaderText("");
            alert.showAndWait();
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

    public void onOpenListPatientOfWaitingRoom(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("A1.fxml"));
        loader.setControllerFactory(e->{

            return new ControllerA1(observableList);
        });





        primaryStage5.setTitle("Hello World!");
        primaryStage5.setScene(new Scene(loader.load()));
        primaryStage5.show();




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
