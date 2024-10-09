package programmer.zaman.now;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutoIncrementTest {

    @Test
    void testAutoIncrement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        String email = "sony@eannovate.com";
        String comment = "hi";
        String sql = "INSERT INTO comments (email, comment) VALUES(?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, email);
        statement.setString(2, comment);
        statement.executeUpdate();

        ResultSet resultSet = statement.getGeneratedKeys();

        if( resultSet.next() ) {
            int id = resultSet.getInt(1);
            System.out.println("ID Comment: " + id);
        }

        statement.close();
        connection.close();
    }
}
