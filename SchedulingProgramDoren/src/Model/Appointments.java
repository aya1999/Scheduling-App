package Model;

import DAO.AppointmentsDao;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Appointments {
    private int Appointment_ID;
    private String Title;
    private String Description;
    private String Type;
    private String Customer_Name;
    private LocalDateTime Start;
    private LocalDateTime End;
    private Timestamp Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;



    /**
     * This is the constructor method for appointments.
     * @param Appointment_ID
     * @param Title
     * @param Description
     * @param Type
     * @param Start
     * @param End
     * @param Create_Date
     * @param Created_By
     * @param Last_Update
     * @param Last_Updated_By
     * @param Customer_Name
     */
    public Appointments(int Appointment_ID, String Title, String Description, String Type, String Customer_Name, LocalDateTime Start, LocalDateTime End, Timestamp Create_Date, String Created_By, Timestamp Last_Update, String Last_Updated_By) {
        this.Appointment_ID = Appointment_ID;
        this.Title = Title;
        this.Description = Description;
        this.Type = Type;
        this.Customer_Name = Customer_Name;
        this.Start = Start;
        this.End = End;
        this.Create_Date = Create_Date;
        this.Created_By = Created_By;
        this.Last_Update = Last_Update;
        this.Last_Updated_By = Last_Updated_By;
    }

    private static ObservableList<Appointments> allAppointments = AppointmentsDao.getAllAppointments();

    /**
     *
     * @return allAppointments
     */
    public static ObservableList<Appointments> getAllAppointments(){return allAppointments;}


    /**
     *
     * @return Appointment_ID
     */
    public int getAppointment_ID() {
        return Appointment_ID;
    }

    /**
     *
     * @param appointment_ID
     */
    public void setAppointment_ID(int appointment_ID) {
        Appointment_ID = appointment_ID;
    }

    /**
     *
     * @return Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        Title = title;
    }

    /**
     *
     * @return Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        Description = description;
    }

    /**
     *
     * @return Type
     */

    public String getType() {
        return Type;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        Type = type;
    }

    /**
     *
     * @return Start
     */
    public LocalDateTime getStart() {
        return Start;
    }

    /**
     *
     * @param start
     */
    public void setStart(LocalDateTime start) {
        Start = start;
    }

    /**
     *
     * @return End
     */
    public LocalDateTime getEnd() {
        return End;
    }

    /**
     *
     * @param end
     */
    public void setEnd(LocalDateTime end) {
        End = end;
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
     * @return Customer_ID
     */
    public String  getCustomer_Name() {
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
     * @return User_ID
     */

}


