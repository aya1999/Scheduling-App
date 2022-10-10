package Model;

import DAO.ContactsDao;
import DAO.CountriesDao;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class Countries {
  private int  Country_ID;
  private String Country;
  private Date Create_Date;
  private String Created_By;
  private Timestamp Last_Update;
  private String Last_Updated_By;

    /**
     * Constructor method for Countries.
     * @param Country_ID
     * @param Country
     * @param Create_Date
     * @param Created_By
     * @param Last_Update
     * @param Last_Updated_By
     */
  public Countries(int  Country_ID, String Country, Date Create_Date, String Created_By, Timestamp Last_Update, String Last_Updated_By)
  {
      this.Country_ID = Country_ID;
      this.Country = Country;
      this.Create_Date = Create_Date;
      this.Created_By = Created_By;
      this.Last_Update = Last_Update;
      this.Last_Updated_By = Last_Updated_By;

  }


    private static ObservableList<Countries> allCountries = CountriesDao.getAllCountries();

    /**
     *
     * @return allCountries
     */
    public static ObservableList<Countries> getAllCountries(){return allCountries;}

    /**
     *
     * @param country_ID
     */
    public void setCountry_ID(int country_ID) {
        Country_ID = country_ID;
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
     * @return Country
     */
    public String getCountry() {
        return Country;
    }

    /**
     *
     * @param country
     */
    public void setCountry(String country) {
        Country = country;
    }

    /**
     *
     * @return Create_Date
     */
    public Date getCreate_Date() {
        return Create_Date;
    }

    /**
     *
     * @param create_Date
     */
    public void setCreate_Date(Date create_Date) {
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
     * @return Country
     */
     @Override
    public String toString(){
      return (Country);
     }
}
