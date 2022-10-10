package DAO;

import Model.Countries;
import Utilities.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;


public class CountriesDao {
    /**
     * This method selects all countries from the database.
     * @return returns a countryList with all the countries
     */
    public static ObservableList<Countries> getAllCountries(){
        ObservableList<Countries> countryList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT Country_ID, Country, Create_Date, Created_By, Last_Update, Last_Updated_By FROM countries";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int Country_ID = rs.getInt("Country_ID");
                String Country = rs.getString("Country");
                Date Create_Date = rs.getDate("Create_Date");
                String Created_By = rs.getString("Created_By");
                Timestamp Last_Update = rs.getTimestamp("Last_Update");
                String Last_Updated_By = rs.getString("Last_Updated_By");
                Countries countries = new Countries(Country_ID, Country, Create_Date, Created_By, Last_Update, Last_Updated_By);
                countryList.add(countries);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return countryList;

    }
}
