package programmer.zaman.now;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtil {
    private static HikariDataSource dataSource;

    static {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.mariadb.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mariadb://localhost/belajar_java_database");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("12345678");

        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(5);;
        hikariConfig.setIdleTimeout(60_000);
        hikariConfig.setMaxLifetime(10 * 60_000);

        dataSource = new HikariDataSource(hikariConfig);
    }

    public static HikariDataSource getDataSource() {
        return dataSource;
    }

    public static void closeDataSource() {
        dataSource.close();
    }
}
