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
    private Connection con;
    private Statement st;
    private ResultSet rs;

    public Db() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3305/medical", "root", "1234");
            st = con.createStatement();

        } catch (Exception ex) {
            System.out.println("Errer : " + ex);

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

    public ObservableList <PatientForAppointment>getAppointmentAll(){

        ObservableList <PatientForAppointment>observableList = FXCollections.observableArrayList();
        try {

            System.out.println("records from Db");
            String query = "select * from  appointment";

            rs = st.executeQuery(query);
            while(rs.next()){
                int appointmentID= rs.getInt("appointmentID");
                String firstNName= rs.getString("firstname");
                String lastName= rs.getString("lastname");
                String phone= rs.getString("phone");
                String gender= rs.getString("gender");
                String dateOne= rs.getString("dateOne");
                String dateTwo= rs.getString("dateTwo");
                String  status= rs.getString("pstatus");

                PatientForAppointment patientForAppointment = new PatientForAppointment(appointmentID,firstNName,lastName,gender,phone,LocalDate.parse(dateOne),LocalDate.parse(dateTwo),status);
                observableList.add(patientForAppointment);



            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        System.out.println("records from Db");

        return  observableList ;



    }
    public ObservableList <PatientForAppointment>getAppointmentWaiting(){

        ObservableList <PatientForAppointment>observableList = FXCollections.observableArrayList();
        try {

            System.out.println("records from Db");
            String query = "select * from  appointment where pstatus = 'Waiting'";

            rs = st.executeQuery(query);
            while(rs.next()){
                int appointmentID= rs.getInt("appointmentID");
                String firstNName= rs.getString("firstname");
                String lastName= rs.getString("lastname");
                String phone= rs.getString("phone");
                String gender= rs.getString("gender");
                String dateOne= rs.getString("dateOne");
                String dateTwo= rs.getString("dateTwo");
                String  status= rs.getString("pstatus");

                PatientForAppointment patientForAppointment = new PatientForAppointment(appointmentID,firstNName,lastName,gender,phone,LocalDate.parse(dateOne),LocalDate.parse(dateTwo),status);
                observableList.add(patientForAppointment);



            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        System.out.println("records from Db");

        return  observableList ;



    }
    public ObservableList <PatientForAppointment>getAppointmentPass(){

        ObservableList <PatientForAppointment>observableList = FXCollections.observableArrayList();
        try {

            System.out.println("records from Db");
            String query = "select * from  appointment where pstatus = 'Pass'";

            rs = st.executeQuery(query);
            while(rs.next()){
                int appointmentID= rs.getInt("appointmentID");
                String firstNName= rs.getString("firstname");
                String lastName= rs.getString("lastname");
                String phone= rs.getString("phone");
                String gender= rs.getString("gender");
                String dateOne= rs.getString("dateOne");
                String dateTwo= rs.getString("dateTwo");
                String  status= rs.getString("pstatus");

                PatientForAppointment patientForAppointment = new PatientForAppointment(appointmentID,firstNName,lastName,gender,phone,LocalDate.parse(dateOne),LocalDate.parse(dateTwo),status);
                observableList.add(patientForAppointment);



            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        System.out.println("records from Db");

        return  observableList ;



    }
    public ObservableList <PatientForAppointment>getAppointmentToday(){

        ObservableList <PatientForAppointment>observableList = FXCollections.observableArrayList();
        try {

            System.out.println("records from Db");
            String query = "select * from  appointment where dateOne ="+"'"+LocalDate.now()+"'";

            rs = st.executeQuery(query);
            while(rs.next()){
                int appointmentID= rs.getInt("appointmentID");
                String firstNName= rs.getString("firstname");
                String lastName= rs.getString("lastname");
                String phone= rs.getString("phone");
                String gender= rs.getString("gender");
                String dateOne= rs.getString("dateOne");
                String dateTwo= rs.getString("dateTwo");
                String  status= rs.getString("pstatus");

                PatientForAppointment patientForAppointment = new PatientForAppointment(firstNName,lastName,gender,phone,LocalDate.parse(dateOne),LocalDate.parse(dateTwo),status);
                observableList.add(patientForAppointment);


//                appointmentID INT(11) AUTO_INCREMENT not null,
//                        firstname VARCHAR(32) NOT NULL,
//                lastname VARCHAR(32) NOT NULL,
//                phone VARCHAR(32) ,
//                        gender VARCHAR(10) ,
//                        pstatus VARCHAR(10) NOT NULL,
//                dateOne date NOT NULL,
//                dateTwo date NOT NULL,
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        System.out.println("records from Db");

        return  observableList ;



    }
    public void setStatus() {

        String sql = "UPDATE appointment SET pstatus = 'Pass' WHERE dateOne <"+"'"+LocalDate.now()+"'";
        try {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Erororrrrrrrrrrr");
        }

    }
    public void updateAppointment(PatientForAppointment patientForAppointment) {
        System.out.println("test");
        String sql = "UPDATE appointment SET firstname ='" + patientForAppointment.getFirstName() +"'"+ ",dateOne ='"+ patientForAppointment.getDateOne() +"',lastname = '" + patientForAppointment.getLastName() + "',phone='" + patientForAppointment.getPhone() + "',gender= '" + patientForAppointment.getGender() + "',pstatus = '" + patientForAppointment.getStatus() + "' WHERE appointmentID =" + patientForAppointment.getId();
        try {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error");

        }

    }
    public int getTestAppointment(LocalDate date){

        int i =0;
        try {

            System.out.println("records from Db");
            String query = "select * from  appointment where dateOne ="+"'"+date+"'";

            rs = st.executeQuery(query);

            while(rs.next()){

        i++;

            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        System.out.println("records from Db");

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
    //
//    public ObservableList <Patient>getPatientData(){
//
//        ObservableList <Patient>observableList = FXCollections.observableArrayList();
//        try {
//            System.out.println("records from Db");
//            String query = "SELECT * FROM patient";
//            rs = st.executeQuery(query);
//            while(rs.next()){
//                int id = rs.getInt("patientID");
//               Integer children = rs.getInt("children");
//               String lastName= rs.getString("lastname");
//               String firstName = rs.getString("firstname") ;
//               String birthday= rs.getString("birthday");
//               String gender= rs.getString("gender");
//               String profession= rs.getString("Profession") ;
//               String phone = rs.getString("phone");
//               String marritalStatus = rs.getString("MarritalStatus");
//               Patient patient = new Patient(id,lastName,firstName,children,LocalDate.parse(birthday),gender,profession,phone,marritalStatus);
//               observableList.add(patient);
//            }
//        }catch(Exception ex){
//            System.out.println(ex);
//        }
//        System.out.println("records from Db");
//
//            return  observableList ;
//
//
//
//    }
//
//
//
//
//
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

    //
//
//    public int InsertConsultation(Consultation consultation, int patientID,int doctorID)  {
//        PreparedStatement pstmt;
//        String query = "insert into consultation(patientID,historyOfTheIllness,dateofconsultation,physicalActivity,addictions,diagnosis,treatment,reasons,clinicalExamination,resultsOfTest,diet,familyHistory,surgicalHistory,medicalHistory,allergies,doctorID)values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//        try {
//            pstmt = (PreparedStatement) con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
//            pstmt.setInt(1, patientID);
//            pstmt.setString(2, consultation.getHistoryOfTheIllness());
//            pstmt.setObject(3, consultation.getDate());
//            pstmt.setString(4,consultation.getPhysicalActivity());
//            pstmt.setString(5,consultation.getAddictions());
//            pstmt.setString(6, consultation.getDiagnosis());
//            pstmt.setString(7, consultation.getTreatment());
//            pstmt.setString(8, consultation.getReasons());
//            pstmt.setString(9,consultation.getClinicalExamination());
//            pstmt.setString(10,consultation.getResultsOfTest());
//            pstmt.setString(11,consultation.getDiet());
//            pstmt.setString(12,consultation.getFamilyHistory());
//            pstmt.setString(13,consultation.getSurgicalHistory());
//            pstmt.setString(14,consultation.getMedicalHistory());
//            pstmt.setString(15,consultation.getAllergies());
//            pstmt.setInt(16,doctorID);
//
//            pstmt.executeUpdate();
//            rs = pstmt.getGeneratedKeys();
//            if(rs != null && rs.next()){
//                System.out.println("Generated Emp Id: "+rs.getInt(1));
//                consultation.setId(rs.getInt(1));
//
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//
//
//        return 0;
//
//    }
//

    Integer limit;
    public boolean InsertAppointment(PatientForAppointment patient)  {
        PreparedStatement pstmt;
        String query = "insert into appointment(firstname,lastname,pstatus,dateOne,dateTwo,phone,gender)values (?,?,?,?,?,?,?)";
        try {
            if(getTestAppointment(patient.getDateOne())<5) {
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
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("");
                alert.setTitle("");
                alert.setHeaderText("");
                alert.showAndWait();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return true;



    }
    public void deleteFromAppointment(int id){
        System.out.println("test");
        String sql = "DELETE FROM appointment WHERE appointmentID="+id;
        try {

            st.executeUpdate(sql);
        }catch(SQLException e) {
            System.out.println("Error");

        }



    }

    //
//    public int Insert(Document document , int id,int dotorID)  {
//        PreparedStatement pstmt;
//        String query = "insert into document(typeofdocument,patientID,content,dateofdocumentdate,doctorID)values (?,?,?,?,?)";
//        try {
//            pstmt = (PreparedStatement) con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
//            pstmt.setString(1, document.getType());
//            pstmt.setInt(2, id);
//
//            pstmt.setString(3, document.getContent());
//            pstmt.setObject(4, document.getDate());
//            pstmt.setInt(5, dotorID);
//
//
//
//
//            pstmt.executeUpdate();
//            rs = pstmt.getGeneratedKeys();
//            if(rs != null && rs.next()){
//                System.out.println("Generated Emp Id: "+rs.getInt(1));
//                document.setId(rs.getInt(1));
//
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//
//
//        return 0;
//
//    }
//
//    public int InsertMedicament(Prescription prescription, int id,int doctorID)  {
//        PreparedStatement pstmt;
//        String query = "insert into Prescription(dateofprescriptiondate,patientID,doctorID)values (?,?,?)";
//        PreparedStatement pstmt1;
//        String query1 = "insert into Medicament(dosage,entityNumber,qsp,nameMedicament,prescriptionID)values (?,?,?,?,?)";
//        try {
//            pstmt = (PreparedStatement) con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
//            pstmt.setObject(1, prescription.getDate());
//            pstmt.setInt(2,id);
//            pstmt.setInt(3,doctorID);
//            pstmt.executeUpdate();
//            rs = pstmt.getGeneratedKeys();
//            if(rs != null && rs.next()){
//                System.out.println("Generated Emp Id: "+rs.getInt(1));
//                prescription.setId(rs.getInt(1));
//            }
//            pstmt1 = (PreparedStatement) con.prepareStatement(query1);
//
//            int i = 0;
//            while (i<prescription.getObservableList().size()) {
//                System.out.println("hhhhhhhhhhhhhhh");
//
//                pstmt1.setString(1, prescription.getObservableList().get(i).getDosage());
//                pstmt1.setInt(2, prescription.getObservableList().get(i).getEntityNumber());
//                pstmt1.setString(3, prescription.getObservableList().get(i).getQsp());
//                pstmt1.setString(4, prescription.getObservableList().get(i).getNameMedicament());
//                pstmt1.setInt(5,prescription.getId());
//                pstmt1.executeUpdate();
//                i++;
//            }
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//
//
//        return 0;
//
//    }
//
//
//
//
//
    public void update(PatientForWaitingRoom patientForWaitingRoom) {
        System.out.println("test");
        String sql = "UPDATE waiting SET firstname ='" + patientForWaitingRoom.getFirstName() + "',lastname = '" + patientForWaitingRoom.getLastName() + "',phone='" + patientForWaitingRoom.getPhone() + "',gender= '" + patientForWaitingRoom.getGender() + "',pstatus = '" + patientForWaitingRoom.getStatus() + "' WHERE waitingID =" + patientForWaitingRoom.getNumber();
        try {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error");

        }

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
        String sql = "UPDATE waiting SET firstname ='',lastname = '',phone='',gender= '',pstatus = '' WHERE waitingID ="+id;
        try {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error");

        }

    }



    public void superClear(int max) {
        if (max != 0) {
            for (int i = 1; i<=max;i++) {

                clearWaiting(i);

            }

        }
            System.out.println("clera");

    }



    public void superUpdate(ObservableList<PatientForWaitingRoom> observableList) {
        if (observableList != null) {
            for (PatientForWaitingRoom patientForWaitingRoom : observableList) {

                update(patientForWaitingRoom);

            }

        }


    }


    public ObservableList <PatientForWaitingRoom>getPatientData() {

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

//
//
    }

    //
//    public int getNb() throws SQLException {
//        System.out.println("records from Db");
//        String query = "SELECT * FROM settings";
//
//        rs = st.executeQuery(query);
//
//
//        int  nb = 0;
//        while(rs.next()){
//            nb  = rs.getInt("setting");
//        }
//
//        return nb ;
//    }
//
//
//
//    public ArrayList<Image> getSuperData() throws SQLException {
//        PreparedStatement pstm = con.prepareStatement("SELECT * FROM bookphoto");
//        ArrayList<Image> list = new ArrayList<>();
//        rs =  pstm.executeQuery();
//
//        while(rs.next()){
//            String s =rs.getString("name");
//            System.out.println(s);
//
//
//            InputStream inputStream = rs.getBinaryStream("img");
//            Image image = new Image(inputStream);
//            list.add(image);
//
//        }
//
//        return list;
//
//
//
//    }
//    public ObservableList <Prescription>getPatientDaa(int id){
//
//        ObservableList <Prescription>observableList = FXCollections.observableArrayList();
//        try {
//             ResultSet resultSet ;
//            Statement stt;
//           stt = con.createStatement();
//            System.out.println("records from Db");
//            String query = "select * from  Prescription where patientID ="+id;
//
//            rs = st.executeQuery(query);
//            while(rs.next()){
//                int prescriptionID = rs.getInt("prescriptionID");
//                int doctorID = rs.getInt("doctorID");
//                System.out.println(prescriptionID);
//                String date= rs.getString("dateofprescriptiondate");
//
//                Prescription prescription = new Prescription(prescriptionID,LocalDate.parse(date),doctorID,id);
//
//
//                String query2 = "select * from  Medicament where PrescriptionID  ="+prescription.getId();
//                resultSet = stt.executeQuery(query2);
//                while(resultSet.next()){
//                    String dosage= resultSet.getString("dosage");
//                    int  entityNumber=resultSet.getInt("entityNumber");
//                    String qsp= resultSet.getString("qsp");
//                    String nameMedicament= resultSet.getString("nameMedicament");
//                    prescription.getObservableList().add(new Medicament(nameMedicament,dosage,entityNumber,qsp));
//                }
//                observableList.add(prescription);
//
//            }
//        }catch(Exception ex){
//            System.out.println(ex);
//        }
//        System.out.println("records from Db");
//
//        return  observableList ;
//
//
//
//    }
//
//
//
//    public ObservableList <String>getHistoryOfThell(int id){
//
//        ObservableList <String>observableList = FXCollections.observableArrayList();
//        try {
//
//
//            System.out.println("records from Db");
//            String query = "SELECT historyOfTheIllness FROM  consultation  where patientID ="+id;
//
//            rs = st.executeQuery(query);
//            while(rs.next()){
//                String theIllness= rs.getString("historyOfTheIllness");
//                observableList.add(theIllness);
//            }
//
//
//        }catch(Exception ex){
//            System.out.println(ex);
//        }
//        System.out.println("records from Db");
//
//        return  observableList ;
//
//
//
//    }
//
//
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
//
//    public DoctorInformation getDoctor(int x){
//
//        DoctorInformation doctorInformation = null;
//        try {
//            System.out.println("records from Db");
//            String query = "select*from  doctor where doctorID ="+x;
//            rs = st.executeQuery(query);
//            while(rs.next()){
//
//                String lastName= rs.getString("lastname");
//                String firstName = rs.getString("firstname") ;
//                String birthday= rs.getString("birthday");
//                String gender= rs.getString("gender");
//                String idOfApproval= rs.getString("idofapproval") ;
//                String emailOrPhone = rs.getString("emailorphone");
//                String specialty = rs.getString("specialty");
//                String address= rs.getString("address");
//            doctorInformation = new DoctorInformation(firstName,lastName,LocalDate.parse(birthday),specialty,address,emailOrPhone,x,gender,idOfApproval);
//
//
//            }
//        }catch(Exception ex){
//            System.out.println(ex);
//        }
//        System.out.println("records from Db");
//
//        return  doctorInformation ;
//
//
//
//    }
}

