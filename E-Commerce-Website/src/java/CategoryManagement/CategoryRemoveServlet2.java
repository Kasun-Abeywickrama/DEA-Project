package CategoryManagement;
import DatabaseConnection.DBConnectionManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CategoryRemoveServlet2", urlPatterns = {"/CategoryRemoveServlet2"})
public class CategoryRemoveServlet2 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int subCategoryId = Integer.parseInt(request.getParameter("getSubCategoryID"));

        DBConnectionManager dbcon = new DBConnectionManager();

        try {
            Connection connection = dbcon.getDBConnection();
            Statement stmt = connection.createStatement();

            int rowsAffected = stmt.executeUpdate("DELETE FROM sub_category WHERE sub_category_id=" + subCategoryId);

            if (rowsAffected > 0) {
                request.setAttribute("alert_message", "Sub Category removed successfully");
            } else {
                request.setAttribute("alert_message", "Unable to remove the sub category, Already associated with several products");
            }

            stmt.close();
            dbcon.closeDBConnection();

            request.getRequestDispatcher("/category_management_page.jsp").forward(request, response);

        } catch (SQLException e) {
            dbcon.closeDBConnection();
            request.setAttribute("alert_message", "Unable to remove the sub category, Already associated with several products");
            request.getRequestDispatcher("/category_management_page.jsp").forward(request, response);
           
        }
    }
}
