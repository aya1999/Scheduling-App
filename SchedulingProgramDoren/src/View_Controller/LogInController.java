package View_Controller;

import DAO.AppointmentsDao;
import DAO.UsersDao;
import Model.Appointments;
import Model.Users;
import View_Controller.AddAppointmentController;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogInController implements Initializable {

    public TextField userNameField;
    public TextField passwordField;
    public Label UNLabel;
    public Button loginButton;
    public Label PWLabel;
    public Label loginLabel;
    public String lang;
    public static String currentUser;

    /**
     * This method checks for a valid username and password to log into the program. An error is shown if a username or password is invalid.
     * @param actionEvent on login button pressed
     * @throws IOException
     */
    public void onLogin(ActionEvent actionEvent) throws IOException {


        ObservableList<Users> usersList = UsersDao.getAllUsers();
        String userName = userNameField.getText();
        String password = passwordField.getText();
        boolean loginSuc = true;
        String word = "";
        for(Users user: usersList){
            //alert shows up twice beacuase it loops through the two users in database
            //may have issue with type calender timestamp
            if(userName.equals(user.getUser_Name()) && password.equals(user.getPassword())){
                currentUser = user.getUser_Name();
                Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/AddAppointment.fxml"));
                Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1200, 600 );
                stage.setTitle("Home Screen");
                stage.setScene(scene);
                stage.show();
                loginSuc = true;
                word = "successfully";
                //for 15 min alert
                ObservableList<Appointments> allAppointments = AppointmentsDao.getAllAppointments();
                ZoneId newzid = ZoneId.systemDefault();
                ZonedDateTime now = ZonedDateTime.now();
                //LocalDateTime nownow = now.toLocalDateTime();
                //LocalDateTime nowPlus15 = now.plusMinutes(15);
                boolean alertPos = false;
                for(Appointments i : allAppointments){

                    long timeDiff = ChronoUnit.MINUTES.between(now, i.getStart().atZone(newzid));
                    if(timeDiff > 0 && timeDiff <= 15)
                    {
                        alertPos = true;
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "You have appointment " + i.getAppointment_ID() + " at " + i.getStart() + " in " + timeDiff + " minute(s)");
                        alert.setTitle("Upcoming Appointment");
                        alert.showAndWait();
                        break;
                    }

                }
                if (alertPos == false){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "You do not have any upcoming appointments");
                    alert.setTitle("Upcoming Appointment");
                    alert.showAndWait();
                }
                break;
            }
            else {
                loginSuc = false;
                word = "unsuccessfully";
            }
            




        }
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(new File(" login_activity.txt"), true));
            pw.append("User " + userName + " " + word + " logged in at: " + LocalDateTime.now() + " UTC \n");
            pw.close();
        } catch (FileNotFoundException var2) {
            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, (String)null, var2);
        }
        if (loginSuc == false)

        {
            if (lang == "fr") {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Le nom d'utilisateur ou le mot de passe que vous avez entré n'existe pas. Veuillez réessayer!");
                alert.setTitle("ne peut pas se connecter");
                alert.showAndWait();
            }
            if (lang == "en")
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "The username or password you entered does not match. Please try again!");
                alert.setTitle("Cannot Login");
                alert.showAndWait();
            }
        }




    }

    /**
     * This method initializes the login form based on the user's location.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResourceBundle rb = ResourceBundle.getBundle("Utilities/rb", Locale.getDefault());
        ZoneId newzid = ZoneId.systemDefault();
        //zoneIDLabel.setText(String.valueOf(newzid));
        if(Locale.getDefault().getLanguage().equals("fr"))
        {
            this.UNLabel.setText(rb.getString("username"));
            this.PWLabel.setText(rb.getString("password"));
            this.loginButton.setText(rb.getString("button"));
            this.loginLabel.setText(rb.getString("button"));
            lang = "fr";


        }
        if(Locale.getDefault().getLanguage().equals("en"))
            lang = "en";





    }
}
