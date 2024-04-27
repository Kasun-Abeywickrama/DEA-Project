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
@WebServlet(name = "CategoryAddServlet2", urlPatterns = {"/CategoryAddServlet2"})
public class CategoryAddServlet2 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	int main_category_id=  Integer.parseInt(request.getParameter("main_category_id"));
        String name = request.getParameter("name");

        DBConnectionManager dbcon = new DBConnectionManager();

        // Sub Category Add
        try (Connection connection = dbcon.getDBConnection()) {
            
            Statement stmt = connection.createStatement();

            int rowsAffected = stmt.executeUpdate("INSERT INTO sub_category (name, main_category_id) VALUES ('" + name + "', " + main_category_id + ");");

            if (rowsAffected > 0) {
               
                request.setAttribute("alert_message", "Sub Category added successfully");
                request.getRequestDispatcher("/category_management_page.jsp").forward(request, response); 
            } 
            else {
                
                request.setAttribute("alert_message", "Unable to add the sub category");
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
