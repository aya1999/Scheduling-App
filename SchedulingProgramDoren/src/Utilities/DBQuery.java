package Utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery {
  //Statement referance
  private static PreparedStatement statement;

  //creating statement object, getter
  public static void setPreparedStatement(Connection conn, String sqlStatement) throws SQLException {//i believe sql statement is the insertstatement
   statement = conn.prepareStatement(sqlStatement);

  }

  //setter, return Statement object
  public static Statement getPreparedStatement()
  {
    return statement;
  }
}
