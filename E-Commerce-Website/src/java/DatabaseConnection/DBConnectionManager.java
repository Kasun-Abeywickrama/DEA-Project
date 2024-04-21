package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {

    private static Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/furniture_shop?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "20021028";

    public Connection getDBConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                // Load database driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Establish connection
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Failed to connect to the database.", e);
        }
    }

    public void closeDBConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database.", e);
        }
    }

}
