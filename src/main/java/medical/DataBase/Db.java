package medical.DataBase;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import medical.Assistant;
import medical.MemberLogin;
import medical.PatientForAppointment;
import medical.PatientForWaitingRoom;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author Riadh
 */
public class Db {
    Integer limit;
    private Connection con;
    private Statement st;
    private ResultSet rs;

    public Db() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3305/medical", "root", "1234");
            st = con.createStatement();

        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("");
            alert.setContentText("Database Connection Time Out");
            alert.setTitle("Connection Error");
            alert.showAndWait();


        }


    }

    public boolean memberLogin(MemberLogin memberLogin) {
        PreparedStatement ps;
        ResultSet rs;
        Boolean r = false;
        String query = "SELECT * FROM `assistant` WHERE `firstname` =? AND `lastname`=? AND `pass` =?";

        try {
            ps = con.prepareStatement(query);
            System.out.println(memberLogin.getFirstName());
            System.out.println(memberLogin.getPassword());
            System.out.println(memberLogin.getLastName());


            ps.setString(1, memberLogin.getFirstName());
            ps.setString(2, memberLogin.getLastName());
            ps.setString(3, memberLogin.getPassword());

            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println(rs.getInt(1));
                memberLogin.setAssistantID(rs.getInt(1));
                r = true;
            } else {
                System.out.println("er");
                r = false;
            }

        } catch (SQLException ex) {

        }

        return r;
    }

    public ObservableList<PatientForAppointment> getAppointmentAll() {

        ObservableList<PatientForAppointment> observableList = FXCollections.observableArrayList();
        try {

            System.out.println("records from Db");
            String query = "select * from  appointment";

            rs = st.executeQuery(query);
            while (rs.next()) {
                int appointmentID = rs.getInt("appointmentID");
                String firstNName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String phone = rs.getString("phone");
                String gender = rs.getString("gender");
                String dateOne = rs.getString("dateOne");
                String dateTwo = rs.getString("dateTwo");
                String status = rs.getString("pstatus");

                PatientForAppointment patientForAppointment = new PatientForAppointment(appointmentID, firstNName, lastName, gender, phone, LocalDate.parse(dateOne), LocalDate.parse(dateTwo), status);
                observableList.add(patientForAppointment);


            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        System.out.println("records from Db");

        return observableList;


    }

    public ObservableList<PatientForAppointment> getAppointmentWaiting() {

        ObservableList<PatientForAppointment> observableList = FXCollections.observableArrayList();
        try {

            System.out.println("records from Db");
            String query = "select * from  appointment where pstatus = 'Waiting'";

            rs = st.executeQuery(query);
            while (rs.next()) {
                int appointmentID = rs.getInt("appointmentID");
                String firstNName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String phone = rs.getString("phone");
                String gender = rs.getString("gender");
                String dateOne = rs.getString("dateOne");
                String dateTwo = rs.getString("dateTwo");
                String status = rs.getString("pstatus");

                PatientForAppointment patientForAppointment = new PatientForAppointment(appointmentID, firstNName, lastName, gender, phone, LocalDate.parse(dateOne), LocalDate.parse(dateTwo), status);
                observableList.add(patientForAppointment);


            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        System.out.println("records from Db");

        return observableList;


    }

    public ObservableList<PatientForAppointment> getAppointmentPass() {

        ObservableList<PatientForAppointment> observableList = FXCollections.observableArrayList();
        try {

            System.out.println("records from Db");
            String query = "select * from  appointment where pstatus = 'Pass'";

            rs = st.executeQuery(query);
            while (rs.next()) {
                int appointmentID = rs.getInt("appointmentID");
                String firstNName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String phone = rs.getString("phone");
                String gender = rs.getString("gender");
                String dateOne = rs.getString("dateOne");
                String dateTwo = rs.getString("dateTwo");
                String status = rs.getString("pstatus");

                PatientForAppointment patientForAppointment = new PatientForAppointment(appointmentID, firstNName, lastName, gender, phone, LocalDate.parse(dateOne), LocalDate.parse(dateTwo), status);
                observableList.add(patientForAppointment);


            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        System.out.println("records from Db");

        return observableList;


    }

    public ObservableList<PatientForAppointment> getAppointmentToday() {

        ObservableList<PatientForAppointment> observableList = FXCollections.observableArrayList();
        try {

            System.out.println("records from Db");
            String query = "select * from  appointment where dateOne =" + "'" + LocalDate.now() + "'";

            rs = st.executeQuery(query);
            while (rs.next()) {
                int appointmentID = rs.getInt("appointmentID");
                String firstNName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String phone = rs.getString("phone");
                String gender = rs.getString("gender");
                String dateOne = rs.getString("dateOne");
                String dateTwo = rs.getString("dateTwo");
                String status = rs.getString("pstatus");

                PatientForAppointment patientForAppointment = new PatientForAppointment(firstNName, lastName, gender, phone, LocalDate.parse(dateOne), LocalDate.parse(dateTwo), status);
                observableList.add(patientForAppointment);


            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        System.out.println("records from Db");

        return observableList;


    }

    public void setStatus() {

        String sql = "UPDATE appointment SET pstatus = 'Pass' WHERE dateOne <" + "'" + LocalDate.now() + "'";
        try {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean updateTestAppointment(LocalDate localDate) {
        boolean b = true;
        int a = getTestAppointment(localDate);
        b = a < 5;
        return b;

    }

    public void updateAppointment(PatientForAppointment patientForAppointment) {
        boolean b = true;

        String sql = "UPDATE appointment SET firstname ='" + patientForAppointment.getFirstName() + "'" + ",dateOne ='" + patientForAppointment.getDateOne() + "',lastname = '" + patientForAppointment.getLastName() + "',phone='" + patientForAppointment.getPhone() + "',gender= '" + patientForAppointment.getGender() + "',pstatus = '" + patientForAppointment.getStatus() + "' WHERE appointmentID =" + patientForAppointment.getId();
        try {
            st.executeUpdate(sql);
        } catch (SQLException e) {

        }


    }

    public int getTestAppointment(LocalDate date) {

        int i = 0;
        try {

            System.out.println("records from Db");
            String query = "select * from  appointment where dateOne =" + "'" + date + "'";

            rs = st.executeQuery(query);

            while (rs.next()) {

                i++;

            }
        } catch (Exception ex) {
            System.out.println(ex);
        }


        return i;


    }

    public void deleteAppointment(int id) {
        System.out.println("test");
        String sql = "DELETE FROM appointment WHERE appointmentID=" + id;
        try {

            st.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error");

        }
    }

    public int InsertData(Assistant assistant) {
        PreparedStatement pstmt;
        String query = "insert into assistant(firstname,lastname,birthday,address,phone,pass,gender)values (?,?,?,?,?,?,?)";
        try {
            pstmt = (PreparedStatement) con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, assistant.getFirstName());
            pstmt.setString(2, assistant.getLastName());
            pstmt.setObject(3, assistant.getBirthday());
            pstmt.setString(4, assistant.getAddress());
            pstmt.setString(5, assistant.getPhone());
            pstmt.setString(6, assistant.getPass());
            pstmt.setString(7, assistant.getGender());


            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs != null && rs.next()) {
                System.out.println("Generated Emp Id: " + rs.getInt(1));
                assistant.setId(rs.getInt(1));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;

    }

    public boolean InsertAppointment(PatientForAppointment patient) {
        PreparedStatement pstmt;
        String query = "insert into appointment(firstname,lastname,pstatus,dateOne,dateTwo,phone,gender)values (?,?,?,?,?,?,?)";
        try {
            if (getTestAppointment(patient.getDateOne()) < 5) {
                pstmt = (PreparedStatement) con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, patient.getFirstName());
                pstmt.setString(2, patient.getLastName());
                pstmt.setString(3, patient.getStatus());
                pstmt.setObject(4, patient.getDateOne());
                pstmt.setObject(5, patient.getDateTwo());
                pstmt.setString(6, patient.getPhone());
                pstmt.setString(7, patient.getGender());


                pstmt.executeUpdate();
                rs = pstmt.getGeneratedKeys();
                if (rs != null && rs.next()) {
                    System.out.println("Generated Emp Id: " + rs.getInt(1));
                    patient.setId(rs.getInt(1));

                }
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return true;


    }

    public void deleteFromAppointment(int id) {
        System.out.println("test");
        String sql = "DELETE FROM appointment WHERE appointmentID=" + id;
        try {

            st.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error");

        }


    }


    public boolean update(PatientForWaitingRoom patientForWaitingRoom) {
         boolean r = true;
        if(patientForWaitingRoom.getNumber()<=4){
            String sql = "UPDATE waiting SET firstname ='" + patientForWaitingRoom.getFirstName() + "',lastname = '" + patientForWaitingRoom.getLastName() + "',phone='" + patientForWaitingRoom.getPhone() + "',gender= '" + patientForWaitingRoom.getGender() + "',pstatus = '" + patientForWaitingRoom.getStatus() + "' WHERE waitingID =" + patientForWaitingRoom.getNumber();
            try {
                st.executeUpdate(sql);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }else {
            r= false;
        }
            return r;

    }

    public void updateStatus(PatientForWaitingRoom patientForWaitingRoom) {
        System.out.println("test");
        String sql = "UPDATE waiting SET pstatus = '" + patientForWaitingRoom.getStatus() + "' WHERE waitingID =" + patientForWaitingRoom.getNumber();
        try {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error");

        }

    }

    public void clearWaiting(int id) {
        System.out.println("test");
        String sql = "UPDATE waiting SET firstname ='',lastname = '',phone='',gender= '',pstatus = '' WHERE waitingID =" + id;
        try {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error");

        }

    }


    public void superClear(int max) {
        if (max != 0) {
            for (int i = 1; i <= max; i++) {

                clearWaiting(i);

            }

        }

    }


    public void superUpdate(ObservableList<PatientForWaitingRoom> observableList) {
        if (observableList != null) {
            for (PatientForWaitingRoom patientForWaitingRoom : observableList) {

                update(patientForWaitingRoom);

            }

        }


    }


    public ObservableList<PatientForWaitingRoom> getPatientData() {

        ObservableList<PatientForWaitingRoom> observableList = FXCollections.observableArrayList();
        try {
            System.out.println("records from Db");
            String query = "select*from  waiting where firstname != \"\"and  lastname!=\"\" ;";
            rs = st.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("waitingID");
                String lastName = rs.getString("lastname");
                String firstName = rs.getString("firstname");
                String gender = rs.getString("gender");
                String phone = rs.getString("phone");
                String pstatus = rs.getString("pstatus");
                PatientForWaitingRoom p = new PatientForWaitingRoom(firstName, lastName, gender, phone, id, pstatus);
                observableList.add(p);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        System.out.println("records from Db");

        return observableList;


    }


    public ObservableList<String> getDoctorData(int x) {

        ObservableList<String> observableList = FXCollections.observableArrayList();
        try {
            System.out.println("records from Db");
            String query = "select*from  assistant where assistantID =" + x;
            rs = st.executeQuery(query);
            while (rs.next()) {

                String lastName = rs.getString("lastname");
                String firstName = rs.getString("firstname");
                String birthday = rs.getString("birthday");
                String gender = rs.getString("gender");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                observableList.addAll(firstName, lastName, birthday, gender, phone, address);


            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        System.out.println("records from Db");

        return observableList;


    }


    public void updateLimitWaiting(int i) {

        String sql = "UPDATE limite SET limiteWait ="+i;
        try {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public int getLimitWaiting() {

        int limiteWait = 0;
        try {


            String query = "SELECT * FROM limite";

            rs = st.executeQuery(query);
            while (rs.next()) {
                limiteWait = rs.getInt("limiteWait");
            }
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.setTitle("ERROR");
            alert.setHeaderText("");
            alert.showAndWait();

        }

            return limiteWait;



    }  public void updateLimitApp(int i) {

        String sql = "UPDATE limite SET limiteApp ="+i;
        try {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public int getLimitApp() {

        int limiteWait = 0;
        try {


            String query = "SELECT * FROM limite";

            rs = st.executeQuery(query);
            while (rs.next()) {
                limiteWait = rs.getInt("limiteApp");
            }
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.setTitle("ERROR");
            alert.setHeaderText("");
            alert.showAndWait();

        }

            return limiteWait;



    }

    public String getLicenses() {

        String limiteWait = null;
        try {


            String query = "SELECT * FROM limite";

            rs = st.executeQuery(query);
            while (rs.next()) {
                limiteWait = rs.getString("licenses");
            }
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.setTitle("ERROR");
            alert.setHeaderText("");
            alert.showAndWait();

        }

            return limiteWait;



    }
    //licenses,limiteApp,limiteWait
}

