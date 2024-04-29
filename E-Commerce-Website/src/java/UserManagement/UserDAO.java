package UserManagement;

/**
 *
 * @author dewmi
 */
import DatabaseConnection.DBConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private static final String GET_ALL_USERS_SQL = "SELECT user_id, username, email, first_name, last_name, phone_number, address, role FROM User";
    private static final String GET_USER_BY_ID_SQL = "SELECT user_id, username, email, first_name, last_name, phone_number, address, role FROM User WHERE user_id = ?";
    private static final String ADD_USER_SQL = "INSERT INTO User (username, email, first_name, last_name, phone_number, address, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER_SQL = "UPDATE User SET username = ?, email = ?, first_name = ?, last_name = ?, phone_number = ?, address = ? WHERE user_id = ?";

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        DBConnectionManager cob = new DBConnectionManager();
        Connection connection = cob.getDBConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(GET_ALL_USERS_SQL);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String phoneNumber = resultSet.getString("phone_number");
                String address = resultSet.getString("address");
                boolean role = resultSet.getBoolean("role");
                users.add(new User(userId, username, email, firstName, lastName, phoneNumber, address, role));
            }
        } catch (SQLException e) {
            System.out.println("Error : " + e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    cob.closeDBConnection();
                }
            } catch (SQLException e) {
                System.out.println("Error : Connection closing error");
            }
        }
        return users;
    }

    public User getUserById(int id) throws SQLException {
        DBConnectionManager cob = new DBConnectionManager();
        Connection connection = cob.getDBConnection();
        PreparedStatement statement = null;
        User user = null;

        try {
            statement = connection.prepareStatement(GET_USER_BY_ID_SQL);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String phoneNumber = resultSet.getString("phone_number");
                String address = resultSet.getString("address");
                boolean role = resultSet.getBoolean("role");
                user = new User(userId, username, email, firstName, lastName, phoneNumber, address, role);
            }
        } catch (SQLException e) {
            System.out.println("Error : " + e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    cob.closeDBConnection();
                }
            } catch (SQLException e) {
                System.out.println("Error : Connection closing error");
            }
        }
        return user;
    }

    public int addUser(User user) throws SQLException {
        DBConnectionManager cob = new DBConnectionManager();
        Connection connection = cob.getDBConnection();
        PreparedStatement statement = null;
        int newId = 0;

        try {
            statement = connection.prepareStatement(ADD_USER_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setString(6, user.getPhoneNumber());
            statement.setString(7, user.getAddress());
            statement.setBoolean(8, user.isRole());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    newId = generatedKeys.getInt(1);
                } else {
                    System.out.println("No auto-generated keys were returned");
                }
                System.out.println("User insertion successful");
            } else {
                System.out.println("User insertion failed");
            }

        } catch (SQLException e) {
            System.out.println("Error : " + e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    cob.closeDBConnection();
                }
            } catch (SQLException e) {
                System.out.println("Error : Connection closing error");
            }
        }
        return newId;
    }

    public boolean updateUser(User user) throws SQLException {
        DBConnectionManager cob = new DBConnectionManager();
        Connection connection = cob.getDBConnection();
        PreparedStatement statement = null;
        boolean success = false;

        try {
            statement = connection.prepareStatement(UPDATE_USER_SQL);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getPhoneNumber());
            statement.setString(6, user.getAddress());
            statement.setInt(7, user.getUserId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                success = true;
                System.out.println("User update successful");
            } else {
                System.out.println("User update failed");
            }

        } catch (SQLException e) {
            System.out.println("Error : " + e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    cob.closeDBConnection();
                }
            } catch (SQLException e) {
                System.out.println("Error : Connection closing error");
            }
        }
        return success;
    }
}
