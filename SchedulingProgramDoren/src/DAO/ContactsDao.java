package DAO;

import Model.Contacts;
import Utilities.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ContactsDao {
    /**
     * This method selects all contacts from the database.
     * @return returns a contactList with all the contacts
     */
    public static ObservableList<Contacts> getAllContacts(){
        ObservableList<Contacts> contactList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT Contact_ID, Contact_Name, Email FROM contacts";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int Contact_ID = rs.getInt("Contact_ID");//need???
                String Contact_Name = rs.getString("Contact_Name");
                String Email = rs.getString("Email");

                Contacts contacts = new Contacts(Contact_ID, Contact_Name, Email);
                contactList.add(contacts);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return contactList;

    }
}
