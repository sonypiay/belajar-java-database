package programmer.zaman.now;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLInjectionTest {

    @Test
    void testSqlInjectionTest() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String username = "admin'; #";
        String password = "salah";
        String sql = "SELECT * FROM admin WHERE username = '" + username + "' AND password = '" + password + "'";
        ResultSet resultSet = statement.executeQuery(sql);

        System.out.println(sql);

        if( resultSet.next() ) {
            System.out.println("Login success. Username: " + resultSet.getString("username"));
        } else {
            System.out.println("Login failed");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
