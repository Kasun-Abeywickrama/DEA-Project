package OrderManagement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DatabaseConnection.DBConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author HP
 */
public class OrderDAO {

    private static final String GET_ALL_ORDERS_SQL = "SELECT * FROM orders";
    private static final String GET_ORDER_BY_ID_SQL = "SELECT * FROM orders WHERE order_id = ?";
    private static final String ADD_ORDER_SQL = "INSERT INTO orders (date_time, total_price, status, shipping_address, receiver_name, receiver_phone_number, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_ORDER_SQL = "UPDATE orders SET date_time = ?, total_price = ?, status = ?, shipping_address = ?, receiver_name = ?, receiver_phone_Number = ?, user_id = ? WHERE order_id = ?";
    private static final String DELETE_ORDER_SQL = "DELETE FROM orders WHERE order_id = ?";
    private static final String GET_ORDERS_BY_STATUS_SQL = "SELECT * FROM orders WHERE status = ?";
    private static final String UPDATE_ORDER_STATUS_SQL = "UPDATE orders SET status = ? WHERE order_id = ?";

    public static List<Order> getOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        DBConnectionManager cob = new DBConnectionManager();
        Connection connection = cob.getDBConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(GET_ALL_ORDERS_SQL);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                String dateTime = resultSet.getString("date_time");
                double totalPrice = resultSet.getDouble("total_price");
                String status = resultSet.getString("status");
                String shippingAddress = resultSet.getString("shipping_address");
                String receiverName = resultSet.getString("receiver_name");
                String receiverPhoneNumber = resultSet.getString("receiver_phone_number");
                int userId = resultSet.getInt("user_id");

                orders.add(new Order(orderId, dateTime, totalPrice, status, shippingAddress, receiverName, receiverPhoneNumber, userId));
            }
        } catch (SQLException e) {
            System.out.println("Error getting orders: " + e);
            throw e; // Re-throw the exception for proper handling
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
        return orders;
    }

    public static Order getOrderById(int orderId) throws SQLException {
        DBConnectionManager cob = new DBConnectionManager();
        Connection connection = cob.getDBConnection();
        PreparedStatement statement = null;
        Order order = null;

        try {
            statement = connection.prepareStatement(GET_ORDER_BY_ID_SQL);
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String dateTime = resultSet.getString("date_time");
                double totalPrice = resultSet.getDouble("total_price");
                String status = resultSet.getString("status");
                String shippingAddress = resultSet.getString("shipping_address");
                String receiverName = resultSet.getString("receiver_name");
                String receiverPhoneNumber = resultSet.getString("receiver_phone_number");
                int userId = resultSet.getInt("user_id");

                order = new Order(orderId, dateTime, totalPrice, status, shippingAddress, receiverName, receiverPhoneNumber, userId);
            }
        } catch (SQLException e) {
            System.out.println("Error getting order by ID: " + e);
            throw e; // Re-throw the exception for proper handling
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
        return order;
    }

    public static List<Order> getOrdersByStatus(String status) throws SQLException {
        List<Order> orders = new ArrayList<>();
        DBConnectionManager cob = new DBConnectionManager();
        Connection connection = cob.getDBConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(GET_ORDERS_BY_STATUS_SQL);
            statement.setString(1, status);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                String dateTime = resultSet.getString("date_time");
                double totalPrice = resultSet.getDouble("total_price");
                String orderStatus = resultSet.getString("status");
                String shippingAddress = resultSet.getString("shipping_address");
                String receiverName = resultSet.getString("receiver_name");
                String receiverPhoneNumber = resultSet.getString("receiver_phone_number");
                int userId = resultSet.getInt("user_id");

                orders.add(new Order(orderId, dateTime, totalPrice, orderStatus, shippingAddress, receiverName, receiverPhoneNumber, userId));
            }
        } catch (SQLException e) {
            System.out.println("Error getting orders by status: " + e);
            throw e; // Re-throw the exception for proper handling
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
        return orders;
    }

    public static int addOrder(Order order) throws SQLException {
        DBConnectionManager cob = new DBConnectionManager();
        Connection connection = cob.getDBConnection();
        PreparedStatement statement = null;
        int newId = 0;

        try {
            statement = connection.prepareStatement(ADD_ORDER_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, order.getDateTime());
            statement.setDouble(2, order.getTotalPrice());
            statement.setString(3, order.getStatus());
            statement.setString(4, order.getShippingAddress());
            statement.setString(5, order.getReceiverName());
            statement.setString(6, order.getReceiverPhoneNumber());
            statement.setInt(7, order.getUserId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    // Get the ID of the newly inserted row
                    newId = generatedKeys.getInt(1);
                } else {
                    System.out.println("No auto-generated keys were returned");
                }
            } else {
                System.out.println("Order insertion failed");
            }

        } catch (SQLException e) {
            System.out.println("Error adding order: " + e);
            throw e; // Re-throw the exception for proper handling
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
        return newId;
    }

    public static boolean updateOrder(Order order) throws SQLException {
        DBConnectionManager cob = new DBConnectionManager();
        Connection connection = cob.getDBConnection();
        PreparedStatement statement = null;
        boolean state = false;

        try {
            statement = connection.prepareStatement(UPDATE_ORDER_SQL);
            statement.setString(1, order.getDateTime());
            statement.setDouble(2, order.getTotalPrice());
            statement.setString(3, order.getStatus());
            statement.setString(4, order.getShippingAddress());
            statement.setString(5, order.getReceiverName());
            statement.setString(6, order.getReceiverPhoneNumber());
            statement.setInt(7, order.getUserId());
            statement.setInt(8, order.getOrderId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                state = true;
            } else {
                System.out.println("Order update failed");
            }

        } catch (SQLException e) {
            System.out.println("Error updating order: " + e);
            throw e; // Re-throw the exception for proper handling
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
        return state;
    }

    public static boolean updateOrderStatus(String status, int orderId) throws SQLException {
        DBConnectionManager cob = new DBConnectionManager();
        Connection connection = cob.getDBConnection();
        PreparedStatement statement = null;
        boolean state = false;

        try {
            statement = connection.prepareStatement(UPDATE_ORDER_STATUS_SQL);
            statement.setString(1, status);
            statement.setDouble(2, orderId);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                state = true;
            } else {
                System.out.println("Order status update failed");
            }

        } catch (SQLException e) {
            System.out.println("Error updating order: " + e);
            throw e; // Re-throw the exception for proper handling
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
        return state;
    }

    public static boolean deleteOrder(int orderId) throws SQLException {
        DBConnectionManager cob = new DBConnectionManager();
        Connection connection = cob.getDBConnection();
        PreparedStatement statement = null;
        boolean state = false;

        try {
            statement = connection.prepareStatement(DELETE_ORDER_SQL);
            statement.setInt(1, orderId);
            statement.executeUpdate();
            System.out.println("Order deletion successful");
            state = true;

        } catch (SQLException e) {
            System.out.println("Error deleting order: " + e);
            throw e; // Re-throw the exception for proper handling
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
        return state;
    }

}
