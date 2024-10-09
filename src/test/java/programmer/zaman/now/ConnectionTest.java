package programmer.zaman.now;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

    @BeforeAll
    @Disabled
    static void beforeAll() {
        try {
            Driver driver = new org.mariadb.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch ( SQLException exception) {
            Assertions.fail(exception);
        }
    }

    @Test
    void testConnection() {
        try {
            String url = "jdbc:mariadb://localhost/belajar_java_database";
            String username = "root";
            String password = "12345678";

            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection success");
            connection.close();
        } catch( SQLException exception) {
            Assertions.fail(exception);
        }
    }
}
