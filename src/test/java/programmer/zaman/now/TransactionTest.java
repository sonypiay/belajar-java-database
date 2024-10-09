package programmer.zaman.now;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class TransactionTest {

    @Test
    void testCommit() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);

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
        connection.commit();
        connection.close();
    }

    @Test
    void testRollback() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);

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
        connection.rollback();
        connection.close();
    }
}
