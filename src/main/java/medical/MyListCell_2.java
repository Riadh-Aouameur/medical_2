package medical;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class MyListCell_2 extends ListCell<PatientForAppointment> {
    static int a = 0;
    @Override
    protected void updateItem(PatientForAppointment item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null ) {
            setGraphic(null);
        } else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listitem2.fxml"));
            loader.setControllerFactory(e->{

                return new ControllerListItem2(item);
            });


            try {
                setGraphic(loader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        }





}
