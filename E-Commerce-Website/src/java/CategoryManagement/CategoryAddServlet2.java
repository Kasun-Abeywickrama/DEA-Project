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

     CategoryManegmnetDao operation  = new CategoryManegmnetDao ();
         
         
        
        try{
                    
            String message = operation.addSCategory(name,main_category_id);
            
            if(message != null){
                response.sendRedirect("MainCategoryDetailsRetrieveServlet?alert_message="+message);
            }
            else{
                response.sendRedirect("MainCategoryDetailsRetrieveServlet?alert_message=Product Does Not Exist");
            }
            
        }
        catch(SQLException e){
            response.sendRedirect("MainCategoryDetailsRetrieveServlet?alert_message=Error Inserting Record");
            response.getWriter().println(e);
        } 
    }
    
}
