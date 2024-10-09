package programmer.zaman.now;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;

public class BatchProcessTest {

    @Test
    void testStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        String sql = "INSERT INTO comments (email, comment) VALUES('sony@eannovate.com', 'hi')";
        Statement statement = connection.createStatement();

        for ( int i = 0; i < 1000; i++) {
            statement.addBatch(sql);
        }

        statement.executeBatch();
        statement.close();
        connection.close();
    }

    @Test
    void testPrepareStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        String sql = "INSERT INTO comments (email, comment) VALUES(?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        for ( int i = 0; i < 1000; i++) {
            statement.clearParameters();
            statement.setString(1, "sony@eannovate.com");
            statement.setString(2, "hello");
            statement.addBatch();
        }

        statement.executeBatch();
        statement.close();
        connection.close();
    }
}
