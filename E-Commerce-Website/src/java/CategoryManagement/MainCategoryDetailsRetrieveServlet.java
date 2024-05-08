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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "MainCategoryDetailsRetrieveServlet", urlPatterns = {"/MainCategoryDetailsRetrieveServlet"})
public class MainCategoryDetailsRetrieveServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	 CategoryManegmnetDao operation  = new CategoryManegmnetDao ();
         
         try{
         	ArrayList<MainCategoryDetails> mainCategoryDetailsList = operation.GetAllDataM();
             
             request.setAttribute("MainCategory_details_list", mainCategoryDetailsList);
             
             
          
            // Retrieve Subcategory Details
    
     	ArrayList<SubCategoryDetails> subCategoryDetailsList = operation.GetAllDataS();
         
         request.setAttribute("SubCategory_details_list", subCategoryDetailsList);
         RequestDispatcher rd = request.getRequestDispatcher("/category_management_page.jsp");
         rd.forward(request, response);  
     }
     catch(SQLException e){
         response.getWriter().println(e);
     }  
}
}
