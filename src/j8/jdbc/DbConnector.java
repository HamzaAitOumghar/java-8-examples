package j8.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    public static Connection getLocalConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3308/";
        String database = "ocp";
        String username = "root";
        String password = "";
        return DriverManager.getConnection(url + database, username, password);
    }
}
