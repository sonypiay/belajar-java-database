package programmer.zaman.now;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolTest {

    @Test
    void testHikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.mariadb.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mariadb://localhost/belajar_java_database");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("12345678");

        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(5);;
        hikariConfig.setIdleTimeout(60_000);
        hikariConfig.setMaxLifetime(10 * 60_000);

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        try {
            Connection connection = dataSource.getConnection();
            connection.close();
            dataSource.close();
            System.out.println("Connection success with hikari CP");
        } catch( SQLException exception) {
            Assertions.fail(exception);
        }
    }

    @Test
    void testConnectionUtil() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.close();
        ConnectionUtil.closeDataSource();
    }
}
