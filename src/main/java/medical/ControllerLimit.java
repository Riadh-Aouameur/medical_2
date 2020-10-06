package medical;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import medical.DataBase.Db;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLimit implements Initializable {


   int a=-1;
   int b=0;
    public TextField f1;
    public TextField f2;
    Db db = new Db();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

       a=  db.getLimitWaiting();

        f1.setText(a+"");
        f2.setText(b+"");
    }

    public void onPlus(ActionEvent actionEvent) {
        if(a!=50){

            a++;
            f1.setText(a+"");
            db.updateLimitWaiting(a);

        }

    }

    public void OnMuns(ActionEvent actionEvent) {
        if(a!=0){
            a--;
            f1.setText(a+"");
            db.updateLimitWaiting(a);
        }
    }
    public void onPlus1(ActionEvent actionEvent) {
        if(b!=50){
            b++;
            f2.setText(b+"");
        }

    }

    public void OnMuns1(ActionEvent actionEvent) {
        if(b!=0){
            b--;
            f2.setText(b+"");
        }
    }

}
