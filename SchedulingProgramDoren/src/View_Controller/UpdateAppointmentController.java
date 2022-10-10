package View_Controller;

import DAO.AppointmentsDao;
import Model.Appointments;
import Model.Contacts;
import Model.Customers;
import Model.Users;
import javafx.collections.FXCollections;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class UpdateAppointmentController implements Initializable {
    public TableView appointmentTable;
    public TableColumn colApptmtID;
    public TableColumn colTitle;
    public TableColumn colDesc;
    public TableColumn colType;
    public TableColumn colStart;
    public TableColumn colEnd;
    public TableColumn colCust;
    public ComboBox typeCB;
    public ComboBox custNameCB;
    public DatePicker startDP;
    public DatePicker endDP;
    public TextField apptmtIDField;
    public TextField titleField;
    public TextField descField;
    public ComboBox startHourCB;
    public ComboBox startMinCB;
    public ComboBox endHourCB;
    public ComboBox endMinCB;
    public Button updateButton;
    ObservableList<String> hours = FXCollections.observableArrayList();
    ObservableList<String> minutes = FXCollections.observableArrayList();

    /**
     * This method initializes the form. Fills the combo boxes and table with data.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> types =
                FXCollections.observableArrayList("Cleaning",
                        "Fillings",
                        "Extraction",
                        "Root Canal"
                );
        typeCB.setItems(types);
        typeCB.setPromptText("Type");//changed from contacts
        ObservableList<Customers> customers = Customers.getAllCustomers();//need to change to names(maybe is??)
        ObservableList<String> customer = FXCollections.observableArrayList();
        for (Customers cust : customers){
            customer.add(cust.getCustomer_Name());
        }
        custNameCB.setItems(customer);
        custNameCB.setPromptText("Customer");//changed from id
        hours.addAll(new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"});
        minutes.addAll(new String[]{"00", "15", "30", "45"});
        startHourCB.setItems(hours);
        startMinCB.setItems(minutes);
        endHourCB.setItems(hours);
        endMinCB.setItems(minutes);


        appointmentTable.setItems(AppointmentsDao.getAllAppointments());
        colApptmtID.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        colStart.setCellValueFactory(new PropertyValueFactory<>("Start"));
        colEnd.setCellValueFactory(new PropertyValueFactory<>("End"));
        colCust.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));


    }

    /**
     * This function clears all of the fields on the form.
     */
    public void clear() {
        typeCB.setValue(null);
        custNameCB.setValue(null);
        startDP.setValue(null);
        endDP.setValue(null);
        apptmtIDField.clear();
        titleField.clear();
        descField.clear();
        startHourCB.setValue(null);
        startMinCB.setValue(null);
        endHourCB.setValue(null);
        endMinCB.setValue(null);

    }

    public void setLabels(String ID, String title, String desc, String startdp, String starthour, String startmin, String enddp, String endhour, String endmin, String type, String customer) {
        custNameCB.setValue(customer);//name
        startDP.setValue(LocalDate.parse(startdp));
        endDP.setValue(LocalDate.parse(enddp));
        apptmtIDField.setText(ID);
        titleField.setText(title);
        descField.setText(desc);
        typeCB.setValue(type);
        startHourCB.setValue(starthour);
        startMinCB.setValue(startmin);
        endHourCB.setValue(endhour);
        endMinCB.setValue(endmin);
    }

    /**
     * This method executes the clear function.
     *
     * @param actionEvent clears fields when clear button pressed
     */
    public void onClear(ActionEvent actionEvent) {
        clear();
    }

    /**
     * This method brings the user back to the home screen
     *
     * @param actionEvent on back button clicked
     * @throws IOException
     */
    public void onCancel(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/AddAppointment.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1200, 650);
        stage.setTitle("Home Screen");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method saves the updates made to an appointment to the database.
     *
     * @param actionEvent on save update button clicked
     */
    public void onUpdateApp(ActionEvent actionEvent) {
//        try {
//            LocalDate startDate = startDP.getValue();
//            String startHour = (String) startHourCB.getValue();
//            String startMinute = (String) startMinCB.getValue();
//            LocalDateTime startLdt = LocalDateTime.of(startDate.getYear(), startDate.getMonthValue(), startDate.getDayOfMonth(), Integer.parseInt(startHour), Integer.parseInt(startMinute));
//            LocalDate endDate = endDP.getValue();
//            String endHour = (String) endHourCB.getValue();
//            String endMinute = (String) endMinCB.getValue();
//            LocalDateTime endLdt = LocalDateTime.of(endDate.getYear(), endDate.getMonthValue(), endDate.getDayOfMonth(), Integer.parseInt(endHour), Integer.parseInt(endMinute));
//
//            System.out.println(startLdt + " " + endLdt);
//
//            int apptmtID = Integer.parseInt(apptmtIDField.getText());
//            String title = titleField.getText();
//            String desc = descField.getText();
//            String location = locationField.getText();
//            String type = typeField.getText();
//            String createdBy = LogInController.currentUser;
//            String updatedBy = LogInController.currentUser;
//
//
//            int custID = ((Integer.parseInt(custIDCB.getSelectionModel().getSelectedItem().toString())));
//            ObservableList<Customers> selectedCustID = Customers.getAllCustomers();
//            int customerId = 0;
//            for (Customers i : selectedCustID) {
//                if (i.getCustomer_ID() == custID) {
//                    customerId = i.getCustomer_ID();
//                    break;
//                }
//            }
//
//            int userID = (Integer.parseInt(UserIDCB.getSelectionModel().getSelectedItem().toString()));
//            ObservableList<Users> selectedUserID = Users.getAllUsers();
//            int userId = 0;
//            for (Users i : selectedUserID) {
//                if (i.getUser_ID() == userID) {
//                    userId = i.getUser_ID();
//                    break;
//                }
//            }
//            String contactName = contactCB.getSelectionModel().getSelectedItem().toString(); //string
//            ObservableList<Contacts> selectedContactName = Contacts.getAllContacts();
//            int contactId = 0;
//            for (Contacts i : selectedContactName) {
//                if (i.getContact_Name().equals(contactName)) {
//                    contactId = i.getContact_ID();
//                    break;
//                }
//            }
////add
//            ZoneId z = ZoneId.of("America/New_York");
//            ZoneId oldzid = ZoneId.systemDefault();
//
//            LocalDateTime newDateTime = startLdt.atZone(oldzid)
//                    .withZoneSameInstant(z)
//                    .toLocalDateTime();
//            LocalDateTime newDateTimeEnd = endLdt.atZone(oldzid)
//                    .withZoneSameInstant(z)
//                    .toLocalDateTime();
//            //ZonedDateTime estStart = startLdt.atZone(z);
//            //ZonedDateTime estEnd = endLdt.atZone(z);
//            int sH = newDateTime.getHour();
//            int eH = newDateTimeEnd.getHour();
//
//            boolean a = true;
//            boolean b = false;
//            ObservableList<Appointments> allAppoint = AppointmentsDao.getAllAppointments();
//            for (Appointments i : allAppoint) {
//                if (endLdt.isAfter(i.getStart()) && startLdt.isBefore(i.getEnd())) {
//                    Alert alert = new Alert(Alert.AlertType.WARNING, "Appointment overlaps another appointment. \n Please choose a different time.");
//                    alert.setTitle("Appointment Overlaps");
//                    alert.showAndWait();
//                    a = false;
//                    break;
//                }
//            }
//            System.out.println("sh " + sH + " eh " + eH);
//            if (sH >= 8 && sH <= 22 && eH >= 8 && eH <= 22) {
//                b = true;
//            } else {
//                Alert alert = new Alert(Alert.AlertType.WARNING, "Appointment is outside of business hours. \n The hours are 8am to 10pm EST.");
//                alert.setTitle("Delete Appointment");
//                alert.showAndWait();
//            }
//
//            if (a && b) {//
//                AppointmentsDao.modifyAppointments(title, desc, location, type, startLdt, endLdt, createdBy, updatedBy, customerId, userId, contactId, apptmtID);
//                appointmentTable.setItems(AppointmentsDao.getAllAppointments());
//            }
//        } catch (NullPointerException e) {
//            System.out.println("Null");
//        }


        ////////
        try {
            LocalDate startDate = startDP.getValue();
            String startHour = (String) startHourCB.getValue();
            String startMinute = (String) startMinCB.getValue();
            LocalDateTime startLdt = LocalDateTime.of(startDate.getYear(), startDate.getMonthValue(), startDate.getDayOfMonth(), Integer.parseInt(startHour), Integer.parseInt(startMinute));
            LocalDate endDate = endDP.getValue();
            String endHour = (String) endHourCB.getValue();
            String endMinute = (String) endMinCB.getValue();
            LocalDateTime endLdt = LocalDateTime.of(endDate.getYear(), endDate.getMonthValue(), endDate.getDayOfMonth(), Integer.parseInt(endHour), Integer.parseInt(endMinute));
            ZoneId z = ZoneId.of("America/New_York");
            ZoneId oldzid = ZoneId.systemDefault();

            LocalDateTime newDateTime = startLdt.atZone(oldzid)
                    .withZoneSameInstant(z)
                    .toLocalDateTime();
            LocalDateTime newDateTimeEnd = endLdt.atZone(oldzid)
                    .withZoneSameInstant(z)
                    .toLocalDateTime();
            //ZonedDateTime estStart = startLdt.atZone(z);
            //ZonedDateTime estEnd = endLdt.atZone(z);
            int sH = newDateTime.getHour();
            int eH = newDateTimeEnd.getHour();

            System.out.println(newDateTime);
            System.out.println(newDateTimeEnd);

            int apptmtID = Integer.parseInt(apptmtIDField.getText());
            String title = titleField.getText();
            String desc = descField.getText();
            String createdBy = LogInController.currentUser;
            String updatedBy = LogInController.currentUser;
            //Customers selectedCust = (Customers) custNameCB.getSelectionModel().getSelectedItem();
            //String custName = selectedCust.getCustomer_Name();
            String custName = (String) custNameCB.getSelectionModel().getSelectedItem();
            String selectedType = (String) typeCB.getSelectionModel().getSelectedItem();//works??
            boolean a = true;
            boolean b = false;
            ObservableList<Appointments> allAppoint = AppointmentsDao.getAllAppointments();
            for (Appointments i : allAppoint) {
                if ((Integer.valueOf(apptmtIDField.getText()) != i.getAppointment_ID())) {
                    if (endLdt.isAfter(i.getStart()) && startLdt.isBefore(i.getEnd())) {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Appointment overlaps another appointment. \n Please choose a different time.");
                        alert.setTitle("Appointment Overlaps");
                        alert.showAndWait();
                        a = false;
                        break;
                    }
                }
            }



            if (sH >= 8 && sH <= 22 && eH >= 8 && eH <= 22) {
                b = true;
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Appointment is outside of business hours. \n The hours are 8am to 10pm EST.");
                alert.setTitle("Delete Appointment");
                alert.showAndWait();
            }

            if (a && b) {
                AppointmentsDao.modifyAppointments(title, desc, selectedType, custName, startLdt, endLdt, createdBy, updatedBy, apptmtID);//for database
                appointmentTable.setItems(AppointmentsDao.getAllAppointments());
            }

        } catch (NullPointerException e) {
            System.out.println("Null");
        } catch (NumberFormatException e) {
            System.out.println("Number");
        }


    }


    public void onBackApp(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/AddAppointment.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1200, 650 );
        stage.setTitle("Home Screen");
        stage.setScene(scene);
        stage.show();
    }
}

