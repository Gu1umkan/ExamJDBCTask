package peaksoft.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:postgresql://localhost:5432/cars_db";
    private static final String username = "postgres";
    private static final String password = "1234";
    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,username,password);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } return connection;
    }
}
