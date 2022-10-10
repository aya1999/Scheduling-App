package Model;

import DAO.FirstLevelDivisionDao;
import javafx.collections.ObservableList;

import java.sql.Timestamp;

public class FirstLevelDivision {
    private int Division_ID;
    private String Division;
    private Timestamp Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;
    private int Country_ID;
    static int id;


    /**
     * This is the constructor method for first level divisions.
     * @param Division_ID
     * @param Division
     * @param Create_Date
     * @param Created_By
     * @param Last_Update
     * @param Last_Updated_By
     * @param Country_ID
     */
    public FirstLevelDivision(int Division_ID, String Division, Timestamp Create_Date, String Created_By, Timestamp Last_Update, String Last_Updated_By, int Country_ID){
        this.Division_ID = Division_ID;
        this.Division = Division;
        this.Create_Date = Create_Date;
        this.Created_By = Created_By;
        this.Last_Update = Last_Update;
        this.Last_Updated_By = Last_Updated_By;
        this.Country_ID = Country_ID;

    }
    private static ObservableList<FirstLevelDivision> allFirstLevelDivisions = FirstLevelDivisionDao.getAllFLDivision();

    /**
     *
     * @return allFirstLevelDivisions
     */
    public static ObservableList<FirstLevelDivision> getAllFirstLevelDivisions(){return allFirstLevelDivisions;}

    private static ObservableList<FirstLevelDivision> allFirstLevelDivisionsByID = FirstLevelDivisionDao.getAllFLDById(id);

    /**
     *
     * @param id
     * @return allFirstLevelDivisionsByID
     */
    public static ObservableList<FirstLevelDivision> getAllFirstLevelDivisionsByID(int id){return allFirstLevelDivisionsByID;}

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
     * @return Division
     */
    public String getDivision() {
        return Division;
    }

    /**
     *
     * @param division
     */
    public void setDivision(String division) {
        Division = division;
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
     * @return Country_ID
     */
    public int getCountry_ID() {
        return Country_ID;
    }

    /**
     *
     * @param country_ID
     */
    public void setCountry_ID(int country_ID) {
        Country_ID = country_ID;
    }


    /**
     *
     * @return Division
     */
    @Override
    public String toString(){
        return (Division);
    }
}

