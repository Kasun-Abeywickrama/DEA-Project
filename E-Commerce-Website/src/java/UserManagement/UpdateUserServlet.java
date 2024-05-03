package UserManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dewmi
 */
@WebServlet(name = "UpdateUserServlet", urlPatterns = {"/UpdateUserServlet"})
public class UpdateUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("user_id") != null) {
            String userIdStr = (String) session.getAttribute("user_id");
            int userId = Integer.parseInt(userIdStr);
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String phoneNumber = request.getParameter("phoneNumber");
            String address = request.getParameter("address");

            User updatedUser = new User(userId, username, email, firstName, lastName, phoneNumber, address);

            UserDAO userDAO = new UserDAO();

            try {
                boolean success = userDAO.updateUser(updatedUser);
                if (success) {
                    response.sendRedirect("ReadUserServlet");

                } else {
                    response.getWriter().println("Error updating user details.");
                }
            } catch (SQLException e) {
                // Handle database exception
                e.printStackTrace();
                response.getWriter().println("Error updating user details.");
            }
        }
    }

}
