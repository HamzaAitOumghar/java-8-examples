package j8.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest {

    public static void main(String[] args) {

        try (Connection connection = DbConnector.getLocalConnection()) {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT id,firstName, email FROM contact WHERE firstName LIKE '%yas%'";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "|" + resultSet.getString(2) + "|" + resultSet.getString(3));
            }

            //UPDATE
            resultSet.absolute(1);
            resultSet.updateString("email", "mica@micamica.fr");
            //
            resultSet.updateRow();
            ///
            resultSet.beforeFirst();
            System.out.println("AFTER UPDATE");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "|" + resultSet.getString(2) + "|" + resultSet.getString(3));
            }
        } catch ( SQLException e) {
            e.printStackTrace();
        }
    }

}
