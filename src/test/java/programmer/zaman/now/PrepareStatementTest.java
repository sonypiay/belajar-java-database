package programmer.zaman.now;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class PrepareStatementTest {

    @Test
    void testPrepareStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        String username = "admin";
        String password = "admin";
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);

        ResultSet resultSet = statement.executeQuery();

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
