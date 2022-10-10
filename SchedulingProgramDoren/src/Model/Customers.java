package Model;

import DAO.CustomersDao;
import javafx.collections.ObservableList;

import java.sql.Timestamp;

public class Customers {
    private int Customer_ID;
    private String Customer_Name;
    private String Address;
    private String Postal_Code;
    private String Phone;
    private Timestamp Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;
    private int Division_ID;
    private boolean Active;
    /**
     * Constructor method for customers.
     * @param Customer_ID
     * @param Customer_Name
     * @param Address
     * @param Postal_Code
     * @param Phone
     * @param Create_Date
     * @param Created_By
     * @param Last_Update
     * @param Last_Updated_By
     * @param Division_ID
     * @param Active
     */
    public Customers(int Customer_ID, String Customer_Name, String Address, String Postal_Code, String Phone, Timestamp Create_Date, String Created_By, Timestamp Last_Update, String Last_Updated_By, int Division_ID, Boolean Active)
    {
        this.Customer_ID = Customer_ID;
        this.Customer_Name = Customer_Name;
        this.Address = Address;
        this.Postal_Code = Postal_Code;
        this.Phone = Phone;
        this.Create_Date = Create_Date;
        this.Created_By = Created_By;
        this.Last_Update = Last_Update;
        this.Last_Updated_By = Last_Updated_By;
        this.Division_ID = Division_ID;
        this.Active = Active;
    }

    private static ObservableList<Customers> allCustomers = CustomersDao.getAllCustomers();

    /**
     *
     * @return allCustomers
     */
    public static ObservableList<Customers> getAllCustomers(){return allCustomers;}

    /**
     *
     * @return Customer_ID
     */
    public int getCustomer_ID() {
        return Customer_ID;
    }

    /**
     *
     * @param customer_ID
     */
    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }

    /**
     *
     * @return Customer_Name
     */
    public String getCustomer_Name() {
        return Customer_Name;
    }

    /**
     *
     * @param customer_Name
     */
    public void setCustomer_Name(String customer_Name) {
        Customer_Name = customer_Name;
    }

    /**
     *
     * @return Address
     */
    public String getAddress() {
        return Address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        Address = address;
    }

    /**
     *
     * @return Postal_Code
     */
    public String getPostal_Code() {
        return Postal_Code;
    }

    /**
     *
     * @param postal_Code
     */
    public void setPostal_Code(String postal_Code) {
        Postal_Code = postal_Code;
    }

    /**
     *
     * @return Phone
     */
    public String getPhone() {
        return Phone;
    }

    /**
     *
     * @param phone
     */
    public void setPhone(String phone) {
        Phone = phone;
    }

    /**
     *
     * @return Create_Date
     */
    public Timestamp getCreate_Date() {
        return Create_Date;
    }

    /**
     *
     * @param create_Date
     */
    public void setCreate_Date(Timestamp create_Date) {
        Create_Date = create_Date;
    }

    /**
     *
     * @return Created_By
     */
    public String getCreated_By() {
        return Created_By;
    }

    /**
     *
     * @param created_By
     */
    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }

    /**
     *
     * @return Last_Update;
     */
    public Timestamp getLast_Update() {
        return Last_Update;
    }

    /**
     *
     * @param last_Update
     */
    public void setLast_Update(Timestamp last_Update) {
        Last_Update = last_Update;
    }

    /**
     *
     * @return Last_Updated_By
     */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /**
     *
     * @param last_Updated_By
     */
    public void setLast_Updated_By(String last_Updated_By) {
        Last_Updated_By = last_Updated_By;
    }

    /**
     *
     * @return Division_ID
     */
    public int getDivision_ID() {
        return Division_ID;
    }

    /**
     *
     * @param division_ID
     */
    public void setDivision_ID(int division_ID) {
        Division_ID = division_ID;
    }

    /**
     *
     * @return Customer_ID
     */
    /**
     *
     * @return Active
     */
    public boolean getActive() {
        return Active;
    }

    /**
     *
     * @param active
     */
    public void setActive(boolean active) {
        Active = active;
    }

    /**
     *
     * @return Customer_ID
     */
    @Override
    public String toString(){
        return String.valueOf((Customer_ID));
    }
}
