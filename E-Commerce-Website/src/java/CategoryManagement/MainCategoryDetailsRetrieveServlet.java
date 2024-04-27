/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CategoryManagement;

import DatabaseConnection.DBConnectionManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "MainCategoryDetailsRetrieveServlet", urlPatterns = {"/MainCategoryDetailsRetrieveServlet"})
public class MainCategoryDetailsRetrieveServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBConnectionManager dbcon = new DBConnectionManager();
        
        try {
            Connection connection = dbcon.getDBConnection();
            
            Statement stmt1 = connection.createStatement();
            ResultSet rs1 = stmt1.executeQuery("SELECT * FROM main_category;");
            
            ArrayList<MainCategoryDetails> mainCategoryDetailsList = new ArrayList<>();
            
            while (rs1.next()) {
                MainCategoryDetails mdl = new MainCategoryDetails(
                rs1.getInt("main_category_id"), 
                rs1.getString("name"));
                mainCategoryDetailsList.add(mdl);
            }
            
            rs1.close();
            stmt1.close();
            
            // Retrieve Subcategory Details
            Statement stmt2 = connection.createStatement();
            ResultSet rs2 = stmt2.executeQuery("SELECT * FROM sub_category;");
            
            ArrayList<SubCategoryDetails> subCategoryDetailsList = new ArrayList<>();
            
            while (rs2.next()) {
                SubCategoryDetails su = new SubCategoryDetails(rs2.getInt("sub_category_id"), rs2.getInt("main_category_id"),rs2.getString("name"));
                subCategoryDetailsList.add(su);
            }
            
            rs2.close();
            stmt2.close();
            
            dbcon.closeDBConnection();
            
            request.setAttribute("MainCategory_details_list", mainCategoryDetailsList);
            request.setAttribute("SubCategory_details_list", subCategoryDetailsList);
            
            request.getRequestDispatcher("/category_management_page.jsp").forward(request, response);
            
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error occurred while retrieving data: " + e.getMessage());
        }
    }  
}
