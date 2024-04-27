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

    public String addCustomerNameToJsonObject(String OrderJson) throws SQLException {
        // Deserialize JSON string to JsonObject
        JsonObject jsonObject = new Gson().fromJson(OrderJson, JsonObject.class);

        DBConnectionManager cob = new DBConnectionManager();
        Connection connection = cob.getDBConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("SELECT username FROM user WHERE user_id = " + jsonObject.get("userId"));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                jsonObject.addProperty("customerName", resultSet.getString("username"));
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

        // Convert JsonObject back to JSON string
        String updatedOrderJson = jsonObject.toString();
        return updatedOrderJson;
    }

    public String getOrderProductsDetails(String orderId) {
        final String SQL = "SELECT  op.orders_product_id, op.selling_price, op.quantity, p.name AS product_name FROM  Orders_Product op INNER JOIN Product_stock ps ON op.stock_id = ps.stock_id INNER JOIN Product p ON ps.product_id = p.product_id WHERE op.order_id = ?";

        DBConnectionManager cob = new DBConnectionManager();
        Connection connection = cob.getDBConnection();
        PreparedStatement statement = null;
        JSONArray productsArray = new JSONArray();

        try {
            statement = connection.prepareStatement(SQL);
            statement.setString(1, orderId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Create a JSONObject to store product details
                JSONObject productObject = new JSONObject();
                productObject.put("orders_product_id", resultSet.getInt("orders_product_id"));
                productObject.put("selling_price", resultSet.getDouble("selling_price"));
                productObject.put("quantity", resultSet.getInt("quantity"));
                productObject.put("product_name", resultSet.getString("product_name"));

                // Add productObject to productsArray
                productsArray.add(productObject);
            }

        } catch (SQLException e) {
            System.out.println("Error getting order product details: " + e);
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

        // Return JSON representation of productsArray
        return productsArray.toJSONString();
    }
}
