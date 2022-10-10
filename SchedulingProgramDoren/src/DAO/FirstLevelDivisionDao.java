package DAO;

import Model.FirstLevelDivision;
import Utilities.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;



public class FirstLevelDivisionDao {
    /**
     * This method selects all first level divisions in database.
     * @return returns a FLDList with all the first level divisions
     */
    public static ObservableList<FirstLevelDivision> getAllFLDivision(){
        ObservableList<FirstLevelDivision> FLDList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT Division_ID, Division, Create_Date, Created_By, Last_Update, Last_Updated_By, Country_ID FROM first_level_divisions";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int Division_ID = rs.getInt("Division_ID");
                String Division = rs.getString("Division");
                Timestamp Create_Date = rs.getTimestamp("Create_Date");
                String Created_By = rs.getString("Created_By");
                Timestamp Last_Update = rs.getTimestamp("Last_Update");
                String Last_Updated_By = rs.getString("Last_Updated_By");
                int Country_ID = rs.getInt("Country_ID");
                FirstLevelDivision firstLevelDivision = new FirstLevelDivision(Division_ID, Division, Create_Date, Created_By, Last_Update, Last_Updated_By, Country_ID);
                FLDList.add(firstLevelDivision);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return FLDList;

    }

    //so state/province list is narrowed down based on country selected

    /**
     * This method selects a first level division from the database by an ID.
     * @param id
     * @return returns a FLDListID with the selected first level division
     */
    public static ObservableList<FirstLevelDivision> getAllFLDById(int id){
        ObservableList<FirstLevelDivision> FLDListID = FXCollections.observableArrayList();

        try{
            String sql = "SELECT Division_ID, Division, Create_Date, Created_By, Last_Update, Last_Updated_By, Country_ID FROM first_level_divisions WHERE Country_ID = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int Division_ID = rs.getInt("Division_ID");
                String Division = rs.getString("Division");
                Timestamp Create_Date = rs.getTimestamp("Create_Date");
                String Created_By = rs.getString("Created_By");
                Timestamp Last_Update = rs.getTimestamp("Last_Update");
                String Last_Updated_By = rs.getString("Last_Updated_By");
                int Country_ID = rs.getInt("Country_ID");
                FirstLevelDivision firstLevelDivision = new FirstLevelDivision(Division_ID, Division, Create_Date, Created_By, Last_Update, Last_Updated_By, Country_ID);
                FLDListID.add(firstLevelDivision);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return FLDListID;

    }
}
