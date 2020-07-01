package j8.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDelete {

    public static void main(String[] args) throws SQLException {

        try (Connection connection = DbConnector.getLocalConnection()) {

            String sql1 = "SELECT * FROM contact WHERE firstName LIKE '%yas%'";
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(sql1);
            while (rs.next()) {
                rs.deleteRow();
            }
            rs.close();
            String sql2 = "SELECT * FROM contact";

            System.out.println("AFTER : ");
            try (ResultSet rs2 = statement.executeQuery(sql2)) {

                while (rs2.next()) {
                    System.out.println(rs2.getInt("id") + "|" + rs2.getString("email") + "|" + rs2.getString("firstName"));
                }
            }

        }


    }
}
