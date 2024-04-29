/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserManagement;

import java.io.IOException;
import java.sql.SQLException;
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
@WebServlet(name = "ReadUserServlet", urlPatterns = {"/ReadUserServlet"})
public class ReadUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("user_id") != null) {

            String userIdStr = (String) session.getAttribute("user_id");
            int userId = Integer.parseInt(userIdStr);

            UserDAO userDAO = new UserDAO();

            try {
                User user = userDAO.getUserById(userId);

                request.setAttribute("user", user);

                request.getRequestDispatcher("user_details.jsp").forward(request, response);
            } catch (SQLException e) {
                response.getWriter().println("Error fetching user details from database.");
            }
        } else {
            response.sendRedirect("sign_in_page.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
