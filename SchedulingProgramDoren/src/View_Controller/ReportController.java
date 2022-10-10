package View_Controller;

import DAO.AppointmentsDao;
import DAO.CustomersDao;
import Model.Appointments;
import Model.Contacts;
import Model.Customers;
import Model.FirstLevelDivision;
import Utilities.DBConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ReportController implements Initializable {


    public TableView tableview;
    public TableColumn colMonth;
    public TableColumn colType;
    public TableColumn colCount;
    public TableView contactTable;
    public ComboBox contactCB;
    public TableColumn colApptmtID;
    public TableColumn colTitle;
    public TableColumn colDesc;
    public TableColumn colStart;
    public TableColumn colEnd;
    public TableColumn colCustID;
    public ComboBox DivIDCB;
    public TableView customerTable;
    public TableColumn colID;
    public TableColumn colAddress;
    public TableColumn colPostal;
    public TableColumn colPhone;
    public TableColumn colCreateDate;
    public TableColumn colCreatedBy;
    public TableColumn colLastUpdate;
    public TableColumn colUpdateBy;
    public TableColumn colDivisionID;
    public TableColumn colName;

    /**
     * LAMBDA: A lambda is used to create the columns of the table used to diplay the results.
     * This method gets the count of appointments grouped by type and month and then populates a table with that data.
     */
    public void getCount() {
        ObservableList<ObservableList> List = FXCollections.observableArrayList();
        Connection c;
        try {
            c = DBConnection.getConnection();
            String sql = "select count(Appointment_ID), month(Start), Type FROM appointments group by month(Start), Type;";
            ResultSet rs = c.createStatement().executeQuery(sql);
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                tableview.getColumns().addAll(col);
            }
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                List.add(row);

            }

            //Add to TableView
            tableview.setItems(List);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method closes the report screen and brings the user to the home screen.
     *
     * @param actionEvent on close button pressed
     * @throws IOException
     */
    public void onClose(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/AddAppointment.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1200, 650);
        stage.setTitle("Home Screen");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method initializes the report form and fills the combo boxes with data.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getCount();
        ObservableList<Customers> cust = Customers.getAllCustomers();
        ObservableList<Integer> custDivID = FXCollections.observableArrayList();

        for (Customers i : cust) {
            custDivID.add(i.getDivision_ID());
        }
        DivIDCB.setItems(custDivID);


//        ObservableList<Contacts> contacts = Contacts.getAllContacts();
//        contactCB.setItems(contacts);
//        colApptmtID.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
//        colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
//        colDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));
//        colType.setCellValueFactory(new PropertyValueFactory<>("Type"));
//        colStart.setCellValueFactory(new PropertyValueFactory<>("Start"));
//        colEnd.setCellValueFactory(new PropertyValueFactory<>("End"));
//        colCustID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));

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


    }

    /**
     * This method populates the table with appointments based on contact selected.
     *
     * @param actionEvent on contact combo box used
     */


    /**
     * This method populates the table with customers based on the division ID selected.
     *
     * @param actionEvent on division combo box used
     */
    public void onDivCB(ActionEvent actionEvent) {
        String div = DivIDCB.getValue().toString();
        int id = Integer.valueOf(div);
        ObservableList<Customers> list = CustomersDao.getCustByDivision(id);
        customerTable.setItems(list);
    }
}

