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


@WebServlet(name = "CategoryRemoveServlet", urlPatterns = {"/CategoryRemoveServlet"})
public class CategoryRemoveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int mainCategoryId = Integer.parseInt(request.getParameter("getMainCategoryID"));

        DBConnectionManager dbcon = new DBConnectionManager();

        try {
            Connection connection = dbcon.getDBConnection();
            Statement stmt = connection.createStatement();

            int rowsAffected = stmt.executeUpdate("DELETE FROM main_category WHERE main_category_id=" + mainCategoryId);

            if (rowsAffected > 0) {
                request.setAttribute("alert_message", "Main Category removed successfully");
            } else {
                request.setAttribute("alert_message", "Unable to remove the main category, Already used in several sub-categories");
            }

            stmt.close();
            dbcon.closeDBConnection();

            request.getRequestDispatcher("/category_management_page.jsp").forward(request, response);

        } catch (SQLException e) {
            dbcon.closeDBConnection(); 
            request.setAttribute("alert_message", "Unable to remove the main category, Already used in several sub-categories");
            request.getRequestDispatcher("/category_management_page.jsp").forward(request, response);
               
        }
    }
}
