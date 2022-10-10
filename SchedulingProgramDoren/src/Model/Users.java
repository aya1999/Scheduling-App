package Model;

import DAO.CountriesDao;
import DAO.UsersDao;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.util.Calendar;

public class Users {
    private int User_ID;
    private String User_Name;
    private String Password;
    private Timestamp Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;

    /**
     * This is the constructor method for users.
     * @param User_ID
     * @param User_Name
     * @param Password
     * @param Create_Date
     * @param Created_By
     * @param Last_Update
     * @param Last_Updated_By
     */
    public Users (int User_ID, String User_Name, String Password, Timestamp Create_Date, String Created_By, Timestamp Last_Update, String Last_Updated_By){
        this.User_ID = User_ID;
        this.User_Name = User_Name;
        this.Password = Password;
        this.Create_Date = Create_Date;
        this.Created_By = Created_By;
        this.Last_Update = Last_Update;
        this.Last_Updated_By = Last_Updated_By;
    }

    private static ObservableList<Users> allUsers = UsersDao.getAllUsers();

    /**
     *
     * @return allUsers
     */
    public static ObservableList<Users> getAllUsers(){return allUsers;}

    /**
     *
     * @return User_ID
     */
    public int getUser_ID() {
        return User_ID;
    }

    /**
     *
     * @param user_ID
     */
    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }

    /**
     *
     * @return User_Name
     */
    public String getUser_Name() {
        return User_Name;
    }

    /**
     *
     * @param user_Name
     */
    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    /**
     *
     * @return Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        Password = password;
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
     * @return Last_Update
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
     * @return User_ID
     */
    @Override
    public String toString(){
        return String.valueOf((User_ID));
    }
}

