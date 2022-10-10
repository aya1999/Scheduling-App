//driver  8.0.22
package View_Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import Utilities.DBConnection;

import java.sql.SQLException;
import java.util.Locale;


public class Main extends Application {
/**
 * This method loads the login form.
 */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        primaryStage.setScene(new Scene(root, 400, 450));
        //primaryStage.setTitle(rb.getString("Title"));
        primaryStage.show();

    }

    /**
     * The main method. Starts the connection to the database and starts the program.
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        //Locale.setDefault(new Locale("fr"));
        DBConnection.startConnection();
        launch(args);
        DBConnection.closeConnection();
    }
}
