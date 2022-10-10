package Model;

import java.sql.Timestamp;

public class ActiveCustomer extends Customers{

    /**
     * Constructor method for customers.
     *
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
     */
    public ActiveCustomer(int Customer_ID, String Customer_Name, String Address, String Postal_Code, String Phone, Timestamp Create_Date, String Created_By, Timestamp Last_Update, String Last_Updated_By, int Division_ID, Boolean Active) {
        super(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID, Active);
    }

    public String isActive(){
        return ("The patient is active!");
    }
}
