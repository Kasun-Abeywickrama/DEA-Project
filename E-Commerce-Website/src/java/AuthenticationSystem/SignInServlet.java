package AuthenticationSystem;

import DatabaseConnection.DBConnectionManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SignInServlet", urlPatterns = {"/SignInServlet"})
public class SignInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // No implementation needed for GET requests
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieving username and password from request parameters
        String username = request.getParameter("email");
        String password = request.getParameter("pwd");

        ResultSet resultSet = null;
        PreparedStatement statement = null;
        DBConnectionManager cob = new DBConnectionManager();
        Connection conn = cob.getDBConnection();

        try {

            // Hashing the password for comparison
            AuthenticationFunctions authFunctions = new AuthenticationFunctions();
            String hashedPassword = authFunctions.hash_password(password);

            // Creating a SQL query to check user credentials
            String query = "SELECT user_id, username, role FROM user WHERE username = ? AND password = ?";
            statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, hashedPassword);

            // Executing the query
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Authentication successful
                HttpSession session = request.getSession();
                session.setAttribute("user_id", resultSet.getString("user_id"));
                session.setAttribute("user_name", resultSet.getString("username"));
                session.setAttribute("role", resultSet.getString("role"));
                session.setAttribute("authenticated", true);

                // Set session timeout to 1 day (in seconds)
                int sessionTimeout = 60 * 60 * 24;
                session.setMaxInactiveInterval(sessionTimeout);


                if(resultSet.getString("role").equals("1")){
                    response.sendRedirect("AdminDashboardDataRetrieveServlet");
                }else{
                    response.sendRedirect("/E-Commerce-Website/home");
                }
                

            } else {
                // Authentication failed
//                response.getWriter().println("");
                response.sendRedirect("sign_in_page.jsp?error_state=Unsuccessful login attempt. Please check your email and password! &user_name=" + username);
            }
        } catch (SQLException e) {
            // Informing about authentication error
            response.getWriter().println("Internal server error occurred.");
        } finally {
            // Closing database resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    cob.closeDBConnection();
                }
            } catch (SQLException e) {
                // Informing about resource closing error
                response.getWriter().println("Error closing resources");
            }
        }
    }
}
