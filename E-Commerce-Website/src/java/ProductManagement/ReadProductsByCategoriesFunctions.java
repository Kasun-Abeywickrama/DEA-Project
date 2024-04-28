package ProductManagement;

import DatabaseConnection.DBConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class ReadProductsByCategoriesFunctions {

    public ArrayList getProductsByCategories(int subCategoryId, int mainCategoryId) {
        DBConnectionManager cob = new DBConnectionManager();
        Connection connection = cob.getDBConnection();
        PreparedStatement statement = null;
        ArrayList<String> productList = new ArrayList<>();
        final String sql = "SELECT p.product_id, p.name AS product_name, p.buying_price, p.selling_price, p.description, p.image, s.name AS sub_category_name, m.name AS main_category_name FROM Product p JOIN Sub_category s ON p.sub_category_id = s.sub_category_id JOIN Main_category m ON s.main_category_id = m.main_category_id WHERE s.sub_category_id = ? AND m.main_category_id = ?";

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, subCategoryId);
            statement.setInt(2, mainCategoryId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String productDetails = resultSet.getInt("product_id") + ", "
                        + resultSet.getString("product_name") + ", "
                        + resultSet.getFloat("selling_price") + ", "
                        + resultSet.getString("description") + ", "
                        + resultSet.getString("sub_category_name") + ", "
                        + resultSet.getString("main_category_name");
                productList.add(productDetails);
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
        return productList;
    }
}
