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

}
