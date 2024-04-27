package CategoryManagement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

/**
 *
 * @author Sandakalum
 */
@WebServlet(name = "CategoryAddServlet", urlPatterns = {"/CategoryAddServlet"})
public class CategoryAddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String name = request.getParameter("name");

        DBConnectionManager dbcon = new DBConnectionManager();

        // Main Category Add
        try (Connection connection = dbcon.getDBConnection()) {
            
            Statement stmt = connection.createStatement();
  
            int rowsAffected = stmt.executeUpdate("INSERT INTO main_category (name) VALUES ('" + name + "');");

            if (rowsAffected > 0) {
                
                request.setAttribute("alert_message", "Main Category added successfully");
                request.getRequestDispatcher("/category_management_page.jsp").forward(request, response);
                
            } else {
                
                request.setAttribute("alert_message", "Unable to add main category");
                request.getRequestDispatcher("/category_management_page.jsp").forward(request, response);
                
            }

        } 
        catch (SQLException e) {
            response.getWriter().println(e);
        } 
        finally {
            dbcon.closeDBConnection(); 
        }   
    }
}

