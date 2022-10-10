package View_Controller;

import DAO.CustomersDao;
import DAO.FirstLevelDivisionDao;
import Model.Countries;
import Model.Customers;
import Model.FirstLevelDivision;
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
import java.util.ResourceBundle;

public class UpdateCustomer implements Initializable {
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
    public RadioButton ActiveRB;
    public ToggleGroup Group2;
    public RadioButton InactiveRB;

    /**
     * This method filters the first division combo box based on the country selected
     * @param actionEvent on country combo box used
     */
    public void onCountryCB(ActionEvent actionEvent) {
        Countries selectedCountry = (Countries) countryCB.getSelectionModel().getSelectedItem();

        if (selectedCountry != null) {
            int countryID = selectedCountry.getCountry_ID();
            ObservableList<FirstLevelDivision> fldid = FirstLevelDivisionDao.getAllFLDById(countryID);
            stateCB.setItems(fldid);

        }
    }

    public void onStateCB(ActionEvent actionEvent) {
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

    /**
     * This method executes the clear function.
     * @param actionEvent clears fields when clear button pressed
     */
    public void onClear(ActionEvent actionEvent) {
        clear();
    }




    public void setLabels(String ID,String name,String address,String code,String phone,String division,String country){
        custField.setText(ID);
        nameField.setText(name);
        addressField.setText(address);
        postalCodeField.setText(code);
        phoneNumberField.setText(phone);
        countryCB.setValue(country);
        stateCB.setValue(division);
    }

    /**
     * This method brings the user back to the home screen
     * @param actionEvent on back button clicked
     * @throws IOException
     */
    public void onBack(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/AddCustomer.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1200, 650 );
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method saves the updates made to a customer to the database.
     * @param actionEvent on save button clicked
     */
    public void onSave(ActionEvent actionEvent) {
        try {
            String state = stateCB.getSelectionModel().getSelectedItem().toString();
            ObservableList<FirstLevelDivision> selectedFLD = FirstLevelDivision.getAllFirstLevelDivisions();
            int divisionId = 0;
            for(FirstLevelDivision i: selectedFLD)
            {
                if (i.getDivision().equals(state))
                {
                    divisionId = i.getDivision_ID();
                    break;
                }
            }
            int custID = Integer.valueOf(custField.getText());
            String name = nameField.getText();
            String address = addressField.getText();
            String postCode = postalCodeField.getText();
            String phoneNumber = phoneNumberField.getText();
            String createdBy = LogInController.currentUser;
            String updatedBy = LogInController.currentUser;
            Boolean Active;
            if(ActiveRB.isSelected())
            {
                Active = true;
            }
            else
            {
                Active = false;
            }


            CustomersDao.modifyCustomers(custID, name, address, postCode, phoneNumber, createdBy, updatedBy, divisionId, Active);
            customerTable.setItems(CustomersDao.getAllCustomers());

        }
        catch (NumberFormatException e)
        {
            System.out.println("Please choose a customer to update first.");
        }


    }


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


    public void onActiveRB(ActionEvent actionEvent) {
    }

    public void onInactiveRB(ActionEvent actionEvent) {
    }
}
