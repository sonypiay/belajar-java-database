package programmer.zaman.now;

import org.junit.jupiter.api.Test;

import javax.xml.transform.Result;
import java.sql.*;

public class DateTimeTest {

    @Test
    void testInsert() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        String sql = "INSERT INTO sample_time (sample_date, sample_time, sample_timestamp) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setDate(1, new Date(System.currentTimeMillis()));
        statement.setTime(2, new Time(System.currentTimeMillis()));
        statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
        statement.executeUpdate();

        statement.close();
        connection.close();
    }

    @Test
    void testQuery() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        String sql = "SELECT * FROM sample_time";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Date date = resultSet.getDate("sample_date");
            System.out.println("Date: " + date);

            Time time = resultSet.getTime("sample_time");
            System.out.println("Time: " + time);

            Timestamp timestamp = resultSet.getTimestamp("sample_timestamp");
            System.out.println("Timestamp: " + timestamp);
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
