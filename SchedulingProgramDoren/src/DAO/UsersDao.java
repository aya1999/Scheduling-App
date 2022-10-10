package DAO;

import Model.FirstLevelDivision;
import Model.Users;
import Utilities.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;


public class UsersDao {
    /**
     * This method creates a prepared statement to select all the users from the database.
     * @return returns a userList of all the users in the database
     */
    public static ObservableList<Users> getAllUsers(){
        ObservableList<Users> userList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT User_ID, User_Name, Password, Create_Date, Created_By, Last_Update, Last_Updated_By FROM users";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int User_ID = rs.getInt("User_ID");
                String User_Name = rs.getString("User_Name");
                String Password = rs.getString("Password");
                Timestamp Create_Date = rs.getTimestamp("Create_Date");
                String Created_By = rs.getString("Created_By");
                Timestamp Last_Update = rs.getTimestamp("Last_Update");
                String Last_Updated_By = rs.getString("Last_Updated_By");
                Users users = new Users(User_ID, User_Name, Password, Create_Date, Created_By, Last_Update, Last_Updated_By);
                userList.add(users);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return userList;

    }
}


