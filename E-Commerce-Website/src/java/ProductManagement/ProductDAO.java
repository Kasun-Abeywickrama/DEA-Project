package ProductManagement;

/**
 *
 * @author HP
 */
import DatabaseConnection.DBConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private static final String GET_ALL_PRODUCTS_SQL = "SELECT * FROM product";
    private static final String GET_PRODUCT_BY_ID_SQL = "SELECT * FROM product WHERE product_id = ?";
    private static final String ADD_PRODUCT_SQL = "INSERT INTO product (name, buying_price, selling_price, description, image, sub_category_id) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_PRODUCT_SQL = "UPDATE product SET name = ?, buying_price = ?, selling_price = ?, description = ?, image = ?, sub_category_id = ? WHERE product_id = ?";
    private static final String DELETE_PRODUCT_SQL = "DELETE FROM product WHERE product_id = ?";

    public static List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        DBConnectionManager cob = new DBConnectionManager();
        Connection connection = cob.getDBConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(GET_ALL_PRODUCTS_SQL);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String name = resultSet.getString("name");
                double buyingPrice = resultSet.getDouble("buying_price");
                double sellingPrice = resultSet.getDouble("selling_price");
                String description = resultSet.getString("description");
                String image = resultSet.getString("image");
                int subCategoryId = resultSet.getInt("sub_category_id");
                products.add(new Product(productId, name, buyingPrice, sellingPrice, description, image, subCategoryId));
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
        return products;
    }

    public static Product getProductById(int id) throws SQLException {
        DBConnectionManager cob = new DBConnectionManager();
        Connection connection = cob.getDBConnection();
        PreparedStatement statement = null;
        Product product = null;

        try {
            statement = connection.prepareStatement(GET_PRODUCT_BY_ID_SQL);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String name = resultSet.getString("name");
                double buyingPrice = resultSet.getDouble("buying_price");
                double sellingPrice = resultSet.getDouble("selling_price");
                String description = resultSet.getString("description");
                String image = resultSet.getString("image");
                int subCategoryId = resultSet.getInt("sub_category_id");
                product = new Product(productId, name, buyingPrice, sellingPrice, description, image, subCategoryId);
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
        return product;
    }

    public static boolean addProduct(Product product) throws SQLException {

        DBConnectionManager cob = new DBConnectionManager();
        Connection connection = cob.getDBConnection();
        PreparedStatement statement = null;
        boolean state = false;

        try {
            statement = connection.prepareStatement(ADD_PRODUCT_SQL);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getBuyingPrice());
            statement.setDouble(3, product.getSellingPrice());
            statement.setString(4, product.getDescription());
            statement.setString(5, product.getImage());
            statement.setInt(6, product.getSubCategoryId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Product insertion successful");
                state = true;
            } else {
                System.out.println("Product insertion failed");
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
        return state;
    }

    public static boolean updateProduct(Product product) throws SQLException {

        DBConnectionManager cob = new DBConnectionManager();
        Connection connection = cob.getDBConnection();
        PreparedStatement statement = null;
        boolean state = false;

        try {
            statement = connection.prepareStatement(UPDATE_PRODUCT_SQL);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getBuyingPrice());
            statement.setDouble(3, product.getSellingPrice());
            statement.setString(4, product.getDescription());
            statement.setString(5, product.getImage());
            statement.setInt(6, product.getSubCategoryId());
            statement.setInt(7, product.getProductId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Update successful");
                state = true;
            } else {
                System.out.println("Update failed");
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
        return state;
    }

    public static boolean deleteProduct(int id) throws SQLException {

        DBConnectionManager cob = new DBConnectionManager();
        Connection connection = cob.getDBConnection();
        PreparedStatement statement = null;
        boolean state = false;

        try {
            statement = connection.prepareStatement(DELETE_PRODUCT_SQL);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Product deletion successful");
            state = true;

        } catch (SQLException e) {
            System.out.println("Error : Product deletion failed " + e);
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
        return state;
    }

}
