package View_Controller;

import DAO.AppointmentsDao;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {
    public TableView appointmentTable;
    public TableColumn colApptmtID;
    public TableColumn colTitle;
    public TableColumn colDesc;
    public TableColumn colType;
    public TableColumn colStart;
    public TableColumn colEnd;
    public TableColumn colCust;
    public RadioButton monthRB;
    public RadioButton weekRB;
    public RadioButton allRB;
    public ComboBox typeCB;
    public ComboBox custNameCB;
    public DatePicker startDP;
    public DatePicker endDP;
    public TextField apptmtIDField;
    public TextField apptmtSearchField;
    public TextField titleField;
    public TextField descField;
    public ComboBox startHourCB;
    public ComboBox startMinCB;
    public ComboBox endHourCB;
    public ComboBox endMinCB;
    public Button updateButton;
    ObservableList<String> hours = FXCollections.observableArrayList();
    ObservableList<String> minutes = FXCollections.observableArrayList();
    //ObservableList<String> week = FXCollections.observableArrayList();
    //ObservableList<String> month = FXCollections.observableArrayList();

    /**
     * This function clears all of the fields on the form.
     */
    public void clear()
    {
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

    /**
     * This method executes the clear function.
     * @param actionEvent clears fields when clear button pressed
     */
    public void onClear(ActionEvent actionEvent) {
        clear();
    }




    /**
     * This method brings the user back to the home screen
     * @param actionEvent on cancel button clicked
     * @throws IOException
     */
//    public void onCancel(ActionEvent actionEvent) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/AddCustomer.fxml"));
//        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root, 1200, 600 );
//        stage.setTitle("Home Screen");
//        stage.setScene(scene);
//        stage.show();
//    }

    /**
     * This method initializes the form. Fills the combo boxes and table with data.
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
     * LAMBDA: A lambda is used in this section to loop through each row and checks to see if that appointments month is equal to the current month.
     * This method filters the appointments by current month.
     * @param actionEvent on month radio button pressed
     */
    public void onMonthRB(ActionEvent actionEvent) {

        ObservableList<Appointments> allAppointments = AppointmentsDao.getAllAppointments();
        LocalDateTime now = LocalDateTime.now();
        int month = now.getMonthValue();

        FilteredList<Appointments> filteredData = new FilteredList<>(allAppointments);
        filteredData.setPredicate(row -> { //lambda

            LocalDateTime rowDate = row.getStart();
            int rowMonth = rowDate.getMonthValue();

            return rowDate.isAfter(now) && rowMonth == month;
        });
        appointmentTable.setItems(filteredData);
    }

    /**
     * LAMBDA: A lambda is used in this section to loop through each row and checks to see if that appointments week is equal to the current week.
     * This method filters the appointments by current week.
     * @param actionEvent on week radio button pressed
     */
    public void onWeekRB(ActionEvent actionEvent) {
        ObservableList<Appointments> allAppointments = AppointmentsDao.getAllAppointments();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowPlus7 = now.plusDays(7);
        FilteredList<Appointments> filteredData = new FilteredList<>(allAppointments);
        filteredData.setPredicate(row -> { //lambda

            LocalDateTime rowDate = row.getStart();

            return rowDate.isAfter(now) && rowDate.isBefore(nowPlus7);
        });
        appointmentTable.setItems(filteredData);
    }
    /**
     * This method shows all the appointments.
     * @param actionEvent on all radio button pressed
     */
    public void onAllRB(ActionEvent actionEvent) {
        appointmentTable.setItems(AppointmentsDao.getAllAppointments());

    }

    /**
     * This method adds a new appointment to the database by collecting data entered into fields.
     * @param actionEvent on add button pressed
     * @throws IOException
     */
    public void onAddApp(ActionEvent actionEvent) throws IOException {
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

            String title = titleField.getText();
            String desc = descField.getText();
            String createdBy = LogInController.currentUser;
            String updatedBy = LogInController.currentUser;
            //Customers selectedCust = (Customers) custNameCB.getSelectionModel().getSelectedItem();
            String custName = (String) custNameCB.getSelectionModel().getSelectedItem();
            String selectedType = (String) typeCB.getSelectionModel().getSelectedItem();//works??
            boolean a = true;
            boolean b = false;
            ObservableList<Appointments> allAppoint = AppointmentsDao.getAllAppointments();
            for (Appointments i : allAppoint) {
                if (endLdt.isAfter(i.getStart()) && startLdt.isBefore(i.getEnd())) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Appointment overlaps another appointment. \n Please choose a different time.");
                    alert.setTitle("Appointment Overlaps");
                    alert.showAndWait();
                    a = false;
                    break;
                }
            }
            System.out.println("sh " + sH + " eh " + eH);
            if (sH >= 8 && sH <= 22 && eH >= 8 && eH <= 22) {
                b = true;
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Appointment is outside of business hours. \n The hours are 8am to 10pm EST.");
                alert.setTitle("Delete Appointment");
                alert.showAndWait();
            }

            if (a && b) {
                AppointmentsDao.insertAppointments(title, desc, selectedType, custName, startLdt, endLdt, createdBy, updatedBy);//for database
                appointmentTable.setItems(AppointmentsDao.getAllAppointments());
            }

        }
        catch (NullPointerException e)
        {
            System.out.println("Null");
        }
        catch (NumberFormatException e)
        {
            System.out.println("Number");
        }

    }

    /**
     * This method populates the fields with the data of the selected appointment.
     * @param actionEvent on update button pressed
     */
    public void onUpdateApp(ActionEvent actionEvent) {
        try {

            //work on this
            Appointments selectedAppointment = (Appointments) appointmentTable.getSelectionModel().getSelectedItem();
            String ID = String.valueOf(selectedAppointment.getAppointment_ID());
            String title = selectedAppointment.getTitle();
            String desc = selectedAppointment.getDescription();
            LocalDateTime start = selectedAppointment.getStart();
            LocalDateTime end = selectedAppointment.getEnd();

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH");
            DateTimeFormatter minuteFormatter = DateTimeFormatter.ofPattern("mm");
            String startdp = String.valueOf(LocalDate.parse(dateFormatter.format(start)));
            String starthour = hourFormatter.format(start);
            String startmin = minuteFormatter.format(start);

            String enddp = String.valueOf(LocalDate.parse(dateFormatter.format(end)));
            String endhour = hourFormatter.format(end);
            String endmin = minuteFormatter.format(end);
            String type = selectedAppointment.getType();//change??
            String customer = selectedAppointment.getCustomer_Name();//name
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("/View_Controller/UpdateAppointment.fxml"));
//            loader.load();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/UpdateAppointment.fxml"));
            Parent root = loader.load();

            //The following both lines are the only addition we need to pass the arguments
            UpdateAppointmentController UpdateAppointment = loader.getController();
            UpdateAppointment.setLabels(ID, title, desc, startdp, starthour, startmin, enddp, endhour, endmin, type, customer); //is this for update

            Stage stage = new Stage();
            //stage.setScene(new Scene(loader.getRoot()));
            stage.setScene(new Scene(root));
            stage.setTitle("Update Appointment");
            stage.show();

            Stage stage2 = (Stage) updateButton.getScene().getWindow();
            stage2.close();


        }

        catch (NullPointerException | IOException e)
        {
            System.out.println("Null");
        }

    }

    /**
     * This method deletes a selected appointment from the database.
     * @param actionEvent on delete button pressed
     */
    public void onDelApp(ActionEvent actionEvent) {
        try {
            Appointments selectedAppointment = (Appointments) appointmentTable.getSelectionModel().getSelectedItem();
            int apptmtID = selectedAppointment.getAppointment_ID();
            String type = selectedAppointment.getType();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Clicking OK will delete appointment " + apptmtID + " of type " + type + "\n  Do you wish to proceed?");
            alert.setTitle("Delete Appointment");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                AppointmentsDao.deleteAppointments(apptmtID);
            }
            appointmentTable.setItems(AppointmentsDao.getAllAppointments());
        }
        catch (NullPointerException e)
        {
            System.out.println("Choose item to delete");
        }
    }

    public void onSaveUpdate(ActionEvent actionEvent) {
    }

    public void onReports(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/Report.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 750, 600 );
        stage.setTitle("Reports");
        stage.setScene(scene);
        stage.show();
    }

    public void onApptmtSearchField(ActionEvent actionEvent) {
        String text = apptmtSearchField.getText();
        if (apptmtSearchField.getText().isEmpty()) {
            appointmentTable.setItems(AppointmentsDao.getAllAppointments());
        }
        else {
            appointmentTable.setItems(AppointmentsDao.getAppointment(text));
        }




    }

    public void onCustView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/AddCustomer.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1150, 600 );
        stage.setTitle("Customer");
        stage.setScene(scene);
        stage.show();
    }

    public void onActiveRB(ActionEvent actionEvent) {
    }

    public void onInactiveRB(ActionEvent actionEvent) {
    }
}
