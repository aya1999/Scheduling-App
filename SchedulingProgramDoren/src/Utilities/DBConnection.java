package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {




    private static final String protocol="jdbc";
    private static final String vendorName=":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcURL = protocol + vendorName + location + databaseName  + "?connectionTimeZone = SERVER"; // LOCAL;


    private static final String username="sqlUser";
    private static final String password="Passw0rd!";

    private static final String DRIVER="com.mysql.cj.jdbc.Driver";
    private static Connection conn = null;

    /**
     * This method opens a connection with the database.
     * @return the connection conn
     */
    public static Connection startConnection() {
        try{
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(jdbcURL, username, password);

            System.out.println("Connection Successful");
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return conn;
    }

    //use get method to get connection instead of starting it each time, keeps connection open throughout program

    /**
     * This method gets the current open connection to the database.
     * @return the current connection conn
     */
    public static Connection getConnection()
    {
        return conn;
    }

    /**
     * This connection closes the connection to the database.
     */
    public static void closeConnection() {
        try {
            conn.close();
        } catch (Exception e) {

        }
    }
}

