package dao.postgresdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {
    private static Connection connection;

    public static Connection getInstance() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "postgres");
            } catch (SQLException e) {
                throw new RuntimeException("Connection failed", e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Driver not found", e);
            }
        }
        return connection;
    }
}