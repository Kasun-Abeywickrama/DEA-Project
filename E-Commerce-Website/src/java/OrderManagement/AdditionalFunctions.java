package OrderManagement;

import DatabaseConnection.DBConnectionManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author HP
 */
public class AdditionalFunctions {

    public String getUsernameById(int userId) throws SQLException {

        DBConnectionManager cob = new DBConnectionManager();
        Connection connection = cob.getDBConnection();
        PreparedStatement statement = null;
        String userName = null;

        try {
            statement = connection.prepareStatement("SELECT username FROM user WHERE user_id = " + userId);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                userName = resultSet.getString("username");
            }

        } catch (SQLException e) {
            System.out.println("Error getting orders: " + e);
            throw e;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    cob.closeDBConnection();
                }
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e);
            }
        }

        return userName;
    }

}
