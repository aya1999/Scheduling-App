package View_Controller;

import DAO.AppointmentsDao;
import DAO.CountriesDao;
import DAO.CustomersDao;
import DAO.FirstLevelDivisionDao;
import Model.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {


    public ComboBox countryCB;
    public ComboBox stateCB;
    public TextField phoneNumberField;
    public TextField postalCodeField;
    public TextField addressField;
    public TextField nameField;
    public TableView customerTable;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colPostal;
    public TableColumn colPhone;
    public TableColumn colCreateDate;
    public TableColumn colCreatedBy;
    public TableColumn colLastUpdate;
    public TableColumn colUpdateBy;
    public TableColumn colDivisionID;
    public TableColumn colActive;
    public TextField custField;
    public TextField custSearchField;
    public Button updateButton;

    boolean action = true;


    //get first level division list based on country selected

    /**
     * This method filters the first division combo box based on the country selected
     * @param actionEvent on country combo box used
     */
    public void onCountryCB(ActionEvent actionEvent) {
        if (action)
        {
            Countries selectedCountry = (Countries) countryCB.getSelectionModel().getSelectedItem();

            if (selectedCountry != null) {
                int countryID = selectedCountry.getCountry_ID();
                ObservableList<FirstLevelDivision> fldid = FirstLevelDivisionDao.getAllFLDById(countryID);
                stateCB.setItems(fldid);

            }
        }





    }

    /**
     * This function clears all of the fields on the form.
     */
    public void clear()
    {
        custField.clear();
        nameField.clear();
        addressField.clear();
        postalCodeField.clear();
        phoneNumberField.clear();
        stateCB.setValue(null);
        countryCB.setValue(null);


    }
    public void onStateCB(ActionEvent actionEvent) {


    }

    public void onPhoneNumberField(ActionEvent actionEvent) {
    }

    public void onPostalCodeField(ActionEvent actionEvent) {
    }

    public void onAddressField(ActionEvent actionEvent) {
    }

    public void onNameField(ActionEvent actionEvent) {
    }

    //populate country combo box
    /**
     * This method initializes the form. Fills the combo boxes and table with data.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Countries> countries = Countries.getAllCountries();//maybe shoyld be from countries and not country dao
        countryCB.setItems(countries);
        countryCB.setPromptText("Choose Country");
        ObservableList<FirstLevelDivision> fld = FirstLevelDivision.getAllFirstLevelDivisions();
        stateCB.setItems(fld);
        stateCB.setPromptText("Choose State");



        customerTable.setItems(Customers.getAllCustomers());
        colID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colPostal.setCellValueFactory(new PropertyValueFactory<>("Postal_Code"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        colCreateDate.setCellValueFactory(new PropertyValueFactory<>("Create_Date"));
        colCreatedBy.setCellValueFactory(new PropertyValueFactory<>("Created_By"));
        colLastUpdate.setCellValueFactory(new PropertyValueFactory<>("Last_Update"));
        colUpdateBy.setCellValueFactory(new PropertyValueFactory<>("Last_Updated_By"));
        colDivisionID.setCellValueFactory(new PropertyValueFactory<>("Division_ID"));
        colActive.setCellValueFactory(new PropertyValueFactory<>("Active"));


        customerTable.setItems(CustomersDao.getAllCustomers());



    }

    /**
     * This method adds a new customer to the database by collecting the data entered into fields.
     * @param actionEvent on add button pressed
     * @throws IOException
     */
    public void onAddCust(ActionEvent actionEvent) {
        try {


            FirstLevelDivision selectedFLD = (FirstLevelDivision) stateCB.getSelectionModel().getSelectedItem();
            String name = nameField.getText();
            String address = addressField.getText();
            String postCode = postalCodeField.getText();
            String phoneNumber = phoneNumberField.getText();
            String createdBy = LogInController.currentUser;
            String updatedBy = LogInController.currentUser;
            Boolean active = true;

            if(selectedFLD != null) {
                int divisionId = selectedFLD.getDivision_ID();


                CustomersDao.insertCustomers(name, address, postCode, phoneNumber, createdBy, updatedBy, divisionId, active );
                customerTable.setItems(CustomersDao.getAllCustomers());
            }
            clear();
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
        catch (NullPointerException e)
        {
            System.out.println("Null point");//set alert
            e.printStackTrace();
        }

    }

    /**
     * This method opens the appointment form.
     * @param actionEvent on appointments button pressed
     * @throws IOException
     */
    public void onAppointments(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/AddAppointment.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1050, 600 );
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method deletes a selected customer from the database.
     * @param actionEvent on delete button pressed
     */
    public void onDelCust(ActionEvent actionEvent) {
        try {
            Customers selectedCustomer = (Customers) customerTable.getSelectionModel().getSelectedItem();
            String custname = selectedCustomer.getCustomer_Name();
            int custID = selectedCustomer.getCustomer_ID();
            ObservableList<Appointments> findApptmt = Appointments.getAllAppointments();
            int a;
            for (Appointments i : findApptmt) {
                if (i.getCustomer_Name() == custname) {
                    a = i.getAppointment_ID();
                    AppointmentsDao.deleteAppointments(a);
                }
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Clicking OK will delete customer " + custname + ". Do you wish to proceed?");
            alert.setTitle("Delete Customer");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                CustomersDao.deleteCustomers(custID);

            }
            customerTable.setItems(CustomersDao.getAllCustomers());
        }
        catch (NullPointerException e)
        {
            System.out.println("Null");
        }
    }






     /** This method executes the clear function.
      * @param actionEvent clears fields when clear button pressed
     */
    public void onClear(ActionEvent actionEvent) {
        clear();
    }




    public void onCustField(ActionEvent actionEvent) {
    }


      /** This method populates the fields with the data of the selected customer on the update customer screen.
       * @param actionEvent on update button pressed
      */
    public void onUpdateCust(ActionEvent actionEvent){
        try {
            Customers selectedCustomer = (Customers) customerTable.getSelectionModel().getSelectedItem();
            if (selectedCustomer != null) {
                String ID = String.valueOf(selectedCustomer.getCustomer_ID());
                String name = selectedCustomer.getCustomer_Name();
                String address = selectedCustomer.getAddress();
                String code = selectedCustomer.getPostal_Code();
                String phone = selectedCustomer.getPhone();
                //to set value of combo box
                int contID = 0;
                String country = "";
                String division = "";
                ObservableList<FirstLevelDivision> fld1 = FirstLevelDivision.getAllFirstLevelDivisions();
                for (FirstLevelDivision i : fld1) {
                    if (selectedCustomer.getDivision_ID() == i.getDivision_ID()) {
                        division = i.getDivision();
                        contID = i.getCountry_ID();
                    }
                }
                ObservableList<Countries> countries1 = Countries.getAllCountries();
                for (Countries i : countries1) {
                    if (i.getCountry_ID() == contID) {
                       country = i.getCountry();

                    }
                }

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/UpdateCustomer.fxml"));
                Parent root = loader.load();

                //The following both lines are the only addition we need to pass the arguments
                UpdateCustomer UpdateCustomer = loader.getController();
                UpdateCustomer.setLabels(ID,name,address,code,phone,division,country);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Update Customer");
                stage.show();

                Stage stage2 = (Stage) updateButton.getScene().getWindow();
                stage2.close();

            }
        }
        catch (NumberFormatException e)
        {
            System.out.println("Please choose a customer to update first.");
        }

        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onCustSearchField(ActionEvent actionEvent) {
        String text = custSearchField.getText();
        if (custSearchField.getText().isEmpty()) {
            customerTable.setItems(CustomersDao.getAllCustomers());
        }
        else {
            customerTable.setItems(CustomersDao.getCustomers(text));
        }
    }

    public void onCancel(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/AddCustomer.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1200, 600 );
        stage.setTitle("Home Screen");
        stage.setScene(scene);
        stage.show();
    }


}
