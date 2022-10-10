package DAO;

import Model.Appointments;
import Utilities.DBConnection;
import Utilities.DBQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;

public class AppointmentsDao {
    /**
     * This method selects an appointment from the database based on name.
     * @param name
     * @return returns an apptmtList with the selected appointment
     */
     public static ObservableList<Appointments> getAppointment(String name){//need to change to string for name...
         ObservableList<Appointments> apptmtList = FXCollections.observableArrayList();

         try{
             String sql = "SELECT Appointment_ID, Title, Description, Type, Customer_Name, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By FROM appointments WHERE Customer_Name = ?";//where statement?
             PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
             ps.setString(1, name);//string
             ResultSet rs = ps.executeQuery();
             while (rs.next()){
                 int Appointment_ID = rs.getInt("Appointment_ID");
                 String Title = rs.getString("Title");
                 String Description = rs.getString("Description");
                 String Type = rs.getString("Type");
                 String Customer_Name = rs.getString("Customer_Name");
                 LocalDateTime Start = rs.getTimestamp("Start").toLocalDateTime();
                 LocalDateTime End = rs.getTimestamp("End").toLocalDateTime();
                 Timestamp Create_Date = rs.getTimestamp("Create_Date");
                 String Created_By = rs.getString("Created_By");
                 Timestamp Last_Update = rs.getTimestamp("Last_Update");
                 String Last_Updated_By = rs.getString("Last_Updated_By");



                 Appointments appointments = new Appointments(Appointment_ID, Title, Description, Type, Customer_Name, Start, End, Create_Date, Created_By,  Last_Update, Last_Updated_By);
                 apptmtList.add(appointments);
             }

         } catch (SQLException ex) {
             ex.printStackTrace();
         }

         return apptmtList;

     }

    /**
     * This method selects all appointments from the database.
     * @return returns an apptmtList with all the appointments
     */
    public static ObservableList<Appointments> getAllAppointments(){
        ObservableList<Appointments> apptmtList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT Appointment_ID, Title, Description, Type, Customer_Name, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By FROM appointments";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int Appointment_ID = rs.getInt("Appointment_ID");
                String Title = rs.getString("Title");
                String Description = rs.getString("Description");
                String Type = rs.getString("Type");
                String Customer_Name = rs.getString("Customer_Name");
                LocalDateTime Start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime End = rs.getTimestamp("End").toLocalDateTime();
                Timestamp Create_Date = rs.getTimestamp("Create_Date");
                String Created_By = rs.getString("Created_By");
                Timestamp Last_Update = rs.getTimestamp("Last_Update");
                String Last_Updated_By = rs.getString("Last_Updated_By");


                Appointments appointments = new Appointments(Appointment_ID, Title, Description, Type, Customer_Name, Start, End, Create_Date, Created_By,  Last_Update, Last_Updated_By);
                apptmtList.add(appointments);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return apptmtList;

    }


    /**
     * This method deletes appointments from the database based on ID.
     * @param Appointment_ID
     */
    public static void deleteAppointments(int Appointment_ID){
        try {
            String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, Appointment_ID);
            ps.execute();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

    }

    /**
     * This method inserts a new appointment with the following parameters into the database.
     * @param Title
     * @param Description
     * @param Type
     * @param Customer_Name
     * @param Start
     * @param End
     * @param Created_By
     * @param Last_Updated_By
     */
    public static void insertAppointments(String Title, String Description, String Type, String Customer_Name, LocalDateTime Start, LocalDateTime End,  String Created_By, String Last_Updated_By){
        Connection conn = DBConnection.getConnection();
        String insertStatement = "INSERT INTO appointments(Title, Description, Type, Customer_Name, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            DBQuery.setPreparedStatement(conn, insertStatement);
            PreparedStatement ps = (PreparedStatement) DBQuery.getPreparedStatement();
            ps.setString(1, Title);
            ps.setString(2, Description);
            ps.setString(3, Type);
            ps.setString(4, Customer_Name);
            ps.setTimestamp(5, Timestamp.valueOf(Start));
            ps.setTimestamp(6, Timestamp.valueOf(End));
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(8, Created_By);
            ps.setString(9, LocalDateTime.now().toString());
            ps.setString(10, Last_Updated_By);
            ps.executeUpdate();
        }
        catch (SQLException ex){
            ex.printStackTrace();

        }

    }

    /**
     * This method updates an appointment in the database based on the ID.
     * @param Title
     * @param Description
     * @param Type
     * @param Customer_Name
     * @param Start
     * @param End
     * @param Created_By
     * @param Last_Updated_By
     */
    public static void modifyAppointments(String Title, String Description, String Type, String Customer_Name, LocalDateTime Start, LocalDateTime End, String Created_By, String Last_Updated_By, int Appointment_ID){
        Connection conn = DBConnection.getConnection();
        String insertStatement = "UPDATE appointments SET Title=?, Description=?, Type=?,  Customer_Name=?, Start=?, End=?, Create_Date=?, Created_By=?, Last_Update=?, Last_Updated_By=? WHERE Appointment_ID=?";
        try {
            DBQuery.setPreparedStatement(conn, insertStatement);
            PreparedStatement ps = (PreparedStatement) DBQuery.getPreparedStatement();
            ps.setString(1, Title);
            ps.setString(2, Description);
            ps.setString(3, Type);
            ps.setString(4, Customer_Name);
            ps.setTimestamp(5, Timestamp.valueOf(Start));
            ps.setTimestamp(6, Timestamp.valueOf(End));
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(8, Created_By);
            ps.setString(9, LocalDateTime.now().toString());
            ps.setString(10, Last_Updated_By);
            ps.setInt(11, Appointment_ID);
            ps.executeUpdate();


        }
        catch (SQLException ex){
            ex.printStackTrace();

        }

    }
}

