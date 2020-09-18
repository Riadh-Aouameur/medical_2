package medical;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class MyListCell_3 extends ListCell<PatientForAppointment> {

    @Override
    protected void updateItem( PatientForAppointment item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null ) {
            setGraphic(null);
        } else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("A2.fxml"));


            loader.setControllerFactory(e->{

                return new ControllerWaitList(item);
            });
            try {
                setGraphic(loader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        }





}
