package ProductManagement;

import DatabaseConnection.DBConnectionManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dewmi
 */
@WebServlet(name = "ReadProductsByCategoriesServlet", urlPatterns = {"/ReadProductsByCategoriesServlet"})
public class ReadProductsByCategoriesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int mainCategoryId = Integer.parseInt(request.getParameter("main_category_id"));

        // Establish a database connection
        DBConnectionManager cob = new DBConnectionManager();
        Connection connection = cob.getDBConnection();
        PreparedStatement statement = null;
        ArrayList<String> subCategoryList = new ArrayList<>();
        final String sql = "SELECT sub_category_id, name AS sub_category_name FROM sub_category WHERE main_category_id = ?";

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, mainCategoryId);

            ResultSet resultSet = statement.executeQuery();

            // Iterate through the result set and add sub-category details to the list
            while (resultSet.next()) {
                String subCategoryDetails = resultSet.getString("sub_category_id") + ", "
                        + resultSet.getString("sub_category_name");

                subCategoryList.add(subCategoryDetails);
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

        // Set the sub-category list as an attribute in the request
        request.setAttribute("sub_category_list", subCategoryList);

        // Forward the request to category_products_page.jsp for further processing
        request.getRequestDispatcher("category_products_page.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
