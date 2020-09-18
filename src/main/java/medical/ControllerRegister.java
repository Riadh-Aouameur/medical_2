package medical;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import medical.DataBase.Db;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ControllerRegister implements Initializable {
    public PasswordField password;



    public PasswordField iPassword;
    public TextField iAddress;
    public TextField iLastName;
    public TextField iFirstName;

    public TextField iEmailOrPhone;
    public DatePicker iBirthday;
    public PasswordField iConfirmPassword;

    public AnchorPane anchorPaneRoot;
    public AnchorPane anchor_2;
    public AnchorPane anchor_1;
    public AnchorPane anchor_3;
    public ImageView i1;
    public ImageView i2;
    public ImageView i3;
    public ImageView i4;
    public ImageView i5;
    public ImageView i6;
    public ImageView i7;
    public ImageView i8;
    public ImageView i9;
    public ImageView i10;
    public RadioButton ima;
    public RadioButton ife;
    public ToggleGroup group;
    public AnchorPane anchorError;
    public Label labErorr;
    public Label text;
    public TextField tLastName;
    public TextField tFirsstName;
    BorderPane root;


    Integer id ;
    String firstName;
    String lastName;
    LocalDate birthday ;

    String address ;
    String phone;
    String pass ;
    String cpass ;

    String gender = "Male";
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        group = new ToggleGroup();
        ima.fire();
        ife.setToggleGroup(group);
        ima.setToggleGroup(group);
        iEmailOrPhone.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    // Check if the new character is greater than LIMIT
                    if (iEmailOrPhone.getText().length() >= 10) {

                        // if it's 11th character then just setText to previous
                        // one
                        iEmailOrPhone.setText(iEmailOrPhone.getText().substring(0, 10));
                    }
                }
            }
        });

        iEmailOrPhone.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    iEmailOrPhone.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        iFirstName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                iFirstName.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        iLastName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                iLastName.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });


        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {

                if (group.getSelectedToggle() != null) {
                    RadioButton button = (RadioButton) group.getSelectedToggle();
                    gender=button.getText();
                }
            }
        });
        iFirstName.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {
                    System.out.println("Textfield on focus");
                    i1.setImage(null);
                }

            }
        });
        iLastName.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {
                    System.out.println("Textfield on focus");
                    i2.setImage(null);
                }

            }
        });
        iPassword.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {

                    i3.setImage(null);
                }

            }
        });
        iConfirmPassword.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                { i4.setImage(null);
                }

            }
        });
        iBirthday.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {
                    i6.setImage(null);
                }

            }
        });
        iAddress.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {
                    i7.setImage(null);
                }

            }
        });
        iEmailOrPhone.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {
                    i8.setImage(null);
                }

            }
        });


        iConfirmPassword.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String character=event.getCharacter();

                System.out.println(character);

                iConfirmPassword.setText(iConfirmPassword.getText()+character);
                iConfirmPassword.positionCaret((iConfirmPassword.getText()+character).length());
                if(iConfirmPassword.getText().equals(iPassword.getText())&&!iPassword.getText().isEmpty()){
                    i4.setImage(new Image(getClass().getResourceAsStream("img/f.png")));
                    i3.setImage(new Image(getClass().getResourceAsStream("img/f.png")));
                }else if (!iConfirmPassword.getText().isEmpty()){
                    i4.setImage(new Image(getClass().getResourceAsStream("img/eror.png")));
                }
                System.out.println(iConfirmPassword.getText());

                event.consume();
            }});



        iPassword.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String character=event.getCharacter();

                System.out.println(character);

                iPassword.setText(iPassword.getText()+character);
                iPassword.positionCaret((iPassword.getText()+character).length());
                if(iPassword.getText().equals(iConfirmPassword.getText())&&!iConfirmPassword.getText().isEmpty()){
                    i4.setImage(new Image(getClass().getResourceAsStream("img/f.png")));
                    i3.setImage(new Image(getClass().getResourceAsStream("img/f.png")));
                }else if (!iConfirmPassword.getText().isEmpty()){
                    i3.setImage(new Image(getClass().getResourceAsStream("img/eror.png")));
                }

                System.out.println(iPassword.getText());

                event.consume();
            }});



    }

    public void onLogin(ActionEvent actionEvent) {
        MemberLogin memberLogin;
        if (tFirsstName.getText().isEmpty()||tLastName.getText().isEmpty()||tFirsstName.getText()== null||tLastName.getText()== null||password.getText().isEmpty()||password.getText()== null){
            labErorr.setText("Must specify an username and password");
            anchorError.setVisible(true);
        }
        else
        {
            memberLogin=new MemberLogin(tFirsstName.getText().toLowerCase(),tLastName.getText().toLowerCase(),password.getText());
            Db db = new Db();

            if (db.memberLogin(memberLogin)){
                System.out.println("iddddddd "+memberLogin.getAssistantID());


                AssistantSingle.getInstance(memberLogin.getAssistantID());
                System.out.println("this doctor Name "+AssistantSingle.getInstance(0).getFirstName());
                try {
                    root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage primaryStage = new Stage();
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.initStyle(StageStyle.UNDECORATED);
                primaryStage.setMaximized(true);

                Image icon = new Image(getClass().getResourceAsStream("img/care.png"));
                primaryStage.getIcons().add(icon);

                primaryStage.show();
                anchorPaneRoot.getScene().getWindow().hide();
            }else{

                labErorr.setText("The username or password you entered is invalid");
                anchorError.setVisible(true);
            }
        }
    }

    public void onSetPaneLogin(ActionEvent actionEvent) {
        anchor_1.toFront();
    }

    public void onSetPaneRegister(ActionEvent actionEvent) {
        anchor_2.toFront();
    }

    public void onRegister(ActionEvent actionEvent) {

        if (iFirstName.getText()== null||iFirstName.getText().isEmpty()){
            i1.setImage(new Image(getClass().getResourceAsStream("img/eror.png")));

            firstName = null;
        }else {
            firstName = iFirstName.getText();
        }
        if (iLastName.getText()== null||iLastName.getText().isEmpty()){
            i2.setImage(new Image(getClass().getResourceAsStream("img/eror.png")));
            lastName= null;
        }else {
            lastName= iLastName.getText();
        }

        if (iPassword.getText()== null||iPassword.getText().isEmpty()){
            i3.setImage(new Image(getClass().getResourceAsStream("img/eror.png")));
            pass = null;

        }else {
            pass =iPassword.getText();
        }

        if (iConfirmPassword.getText()== null||iConfirmPassword.getText().isEmpty()){
            i4.setImage(new Image(getClass().getResourceAsStream("img/eror.png")));
            cpass = null;
        }else {
            cpass = iConfirmPassword.getText();
        }


        if (iAddress.getText()== null||iAddress.getText().isEmpty()){
            i7.setImage(new Image(getClass().getResourceAsStream("img/eror.png")));
            address= null;
        }else {
            address= iAddress.getText();
        }
        if (iBirthday.getValue()== null){
            i6.setImage(new Image(getClass().getResourceAsStream("img/eror.png")));
            birthday =null;
        }else{
            birthday =iBirthday.getValue();
        }


        if (iEmailOrPhone.getText()== null||iEmailOrPhone.getText().isEmpty()){
            i8.setImage(new Image(getClass().getResourceAsStream("img/eror.png")));
            phone =null;
        }else{
            phone =iEmailOrPhone.getText();
        }

        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(birthday);

        System.out.println(address);
        System.out.println(phone);
        System.out.println(pass);

        if (firstName != null && lastName != null
                && birthday != null
                && address != null && phone != null
               && cpass != null && cpass.equals(pass)){
            System.out.println("done");

            Assistant assistant = new Assistant(firstName,lastName,birthday,address, phone,pass,gender);
            Db db= new Db();
            db.InsertData(assistant);
            firstName=null;
            lastName=null;
            birthday=null;

            address=null;
            phone=null;
            pass=null;


            password.clear();


            iPassword.clear();
            iAddress.clear();
            iLastName.clear();
            iFirstName.clear();

            iEmailOrPhone.clear();
            iBirthday.setValue(null);
            iConfirmPassword.clear();
            anchor_3.toFront();

        }else
        {

        }












    }

}
