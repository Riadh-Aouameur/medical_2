package medical;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class MyListCell_1 extends ListCell<PatientForWaitingRoom> {
    static int a = 0;
    @Override
    protected void updateItem(PatientForWaitingRoom item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null ) {
            setGraphic(null);
        } else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listitem.fxml"));
            loader.setControllerFactory(e->{

                return new ControllerListItem(item);
            });


            try {
                setGraphic(loader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        }





}
