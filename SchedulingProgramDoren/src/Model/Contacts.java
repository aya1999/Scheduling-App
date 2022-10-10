package Model;

import DAO.ContactsDao;
import javafx.collections.ObservableList;

public class Contacts {
    private int Contact_ID;
    private String Contact_Name;
    private String Email;


    /**
     * The constructor method for contacts.
     * @param Contact_ID
     * @param Contact_Name
     * @param Email
     */
    public Contacts(int Contact_ID, String Contact_Name, String Email){
        this.Contact_ID = Contact_ID;
        this.Contact_Name = Contact_Name;
        this.Email = Email;
    }


    private static ObservableList<Contacts> allContacts = ContactsDao.getAllContacts();

    /**
     *
     * @return allContacts
     */
    public static ObservableList<Contacts> getAllContacts(){return allContacts;}

    /**
     *
     * @param contact_ID
     */
    public void setContact_ID(int contact_ID) {
        Contact_ID = contact_ID;
    }

    /**
     *
     * @return Contact_ID
     */
    public int getContact_ID() {
        return Contact_ID;
    }

    /**
     *
     * @return Contact_Name
     */
    public String getContact_Name() {
        return Contact_Name;
    }

    /**
     *
     * @param contact_Name
     */
    public void setContact_Name(String contact_Name) {
        Contact_Name = contact_Name;
    }

    /**
     *
     * @return Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        Email = email;
    }

    /**
     *
     * @return Contact_Name
     */
    @Override
    public String toString(){
        return (Contact_Name);
    }
}
