package DAO;

import Model.Customers;
import Utilities.DBConnection;
import Utilities.DBQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;


public class CustomersDao {
    /**
     * This method selects all customers in the database.
     * @return returns a custList of all the customers
     */
    public static ObservableList<Customers> getAllCustomers(){
        ObservableList<Customers> custList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID, Active FROM customers";//change to * or get rid of some
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int Customer_ID = rs.getInt("Customer_ID");//need???
                String Customer_Name = rs.getString("Customer_Name");
                String Address = rs.getString("Address");
                String Postal_Code = rs.getString("Postal_Code");
                String Phone = rs.getString("Phone");
                Timestamp Create_Date = rs.getTimestamp("Create_Date");//correct type???
                String Created_By = rs.getString("Created_By");
                Timestamp Last_Update = rs.getTimestamp("Last_Update");//correct type???
                String Last_Updated_By = rs.getString("Last_Updated_By");
                int Division_ID = rs.getInt("Division_ID");
                Boolean Active = rs.getBoolean("Active");
                Customers customers = new Customers(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID, Active);//because type calender in constructor
                custList.add(customers);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return custList;

    }

    /**
     * This method selects a customer from the database based on the ID.
     * @param name
     * @return returns a custList with the selected customer
     */
    public static ObservableList<Customers> getCustomers(String name){
        ObservableList<Customers> custList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID, Active FROM customers WHERE Customer_Name = ?";//change to * or get rid of some
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int Customer_ID = rs.getInt("Customer_ID");//need???
                String Customer_Name = rs.getString("Customer_Name");
                String Address = rs.getString("Address");
                String Postal_Code = rs.getString("Postal_Code");
                String Phone = rs.getString("Phone");
                Timestamp Create_Date = rs.getTimestamp("Create_Date");//correct type???
                String Created_By = rs.getString("Created_By");
                Timestamp Last_Update = rs.getTimestamp("Last_Update");//correct type???
                String Last_Updated_By = rs.getString("Last_Updated_By");
                int Division_ID = rs.getInt("Division_ID");
                Boolean Active = rs.getBoolean("Active");
                Customers customers = new Customers(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID, Active);//because type calender in constructor
                custList.add(customers);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return custList;

    }

    public static ObservableList<Customers> getCustByDivision(int id){
        ObservableList<Customers> custList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID, Active FROM customers WHERE Division_ID = ?";//change to * or get rid of some
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int Customer_ID = rs.getInt("Customer_ID");//need???
                String Customer_Name = rs.getString("Customer_Name");
                String Address = rs.getString("Address");
                String Postal_Code = rs.getString("Postal_Code");
                String Phone = rs.getString("Phone");
                Timestamp Create_Date = rs.getTimestamp("Create_Date");//correct type???
                String Created_By = rs.getString("Created_By");
                Timestamp Last_Update = rs.getTimestamp("Last_Update");//correct type???
                String Last_Updated_By = rs.getString("Last_Updated_By");
                int Division_ID = rs.getInt("Division_ID");
                Boolean Active = rs.getBoolean("Active");
                Customers customers = new Customers(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID, Active);//because type calender in constructor
                custList.add(customers);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return custList;

    }

    /**
     * This method updates a customers information  with the selected ID.
     * @param Customer_ID
     * @param Customer_Name
     * @param Address
     * @param Postal_Code
     * @param Phone
     * @param Created_By
     * @param Last_Updated_By
     * @param Division_ID
     */
    public static void modifyCustomers(int Customer_ID, String Customer_Name, String Address, String Postal_Code, String Phone, String Created_By, String Last_Updated_By, int Division_ID, Boolean Active){//again need em all???
        try{
            String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Create_Date = ?, Created_By = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID =?, Active=? WHERE Customer_ID = ?";
            //NEED TO FIGURE OUT WHERE
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, Customer_Name);
            ps.setString(2, Address);
            ps.setString(3, Postal_Code);
            ps.setString(4, Phone);
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(6, Created_By);
            ps.setString(7, LocalDateTime.now().toString());
            ps.setString(8, Last_Updated_By);
            ps.setInt(9, Division_ID);
            ps.setBoolean(10, Active);
            ps.setInt(11, Customer_ID);

            ps.executeUpdate();


        }
        catch (SQLException ex){
            ex.printStackTrace();
        }

    }

    /**
     * This method inserts a new customer into the database.
     * @param Customer_Name
     * @param Address
     * @param Postal_Code
     * @param Phone
     * @param Created_By
     * @param Last_Updated_By
     * @param Division_ID
     */
    public static void insertCustomers(String Customer_Name, String Address, String Postal_Code, String Phone, String Created_By, String Last_Updated_By, int Division_ID, Boolean Active){
        Connection conn = DBConnection.getConnection();
        String insertStatement = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID, Active) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            DBQuery.setPreparedStatement(conn, insertStatement);
            PreparedStatement ps = (PreparedStatement) DBQuery.getPreparedStatement();
            //i believe customer id auto
            ps.setString(1, Customer_Name);
            ps.setString(2, Address);
            ps.setString(3, Postal_Code);
            ps.setString(4, Phone);
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(6, Created_By);
            ps.setString(7, LocalDateTime.now().toString());
            ps.setString(8, Last_Updated_By);
            ps.setInt(9, Division_ID);
            ps.setBoolean(10, Active);
            ps.executeUpdate();
        }
        catch (SQLException ex){
            ex.printStackTrace();

        }

    }

    /**
     * This method deletes a customer from the database based on selected ID.
     * @param Customer_ID
     */
    public static void deleteCustomers(int Customer_ID){
        try {
            String sql = "DELETE FROM customers WHERE Customer_ID = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, Customer_ID);
            ps.execute();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

    }
}