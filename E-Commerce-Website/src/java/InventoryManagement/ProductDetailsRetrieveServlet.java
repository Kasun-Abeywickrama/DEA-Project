/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InventoryManagement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sithuruwan
 */
@WebServlet(name = "ProductDetailsRetrieveServlet", urlPatterns = {"/ProductDetailsRetrieveServlet"})
public class ProductDetailsRetrieveServlet extends HttpServlet {

    
    //Sending the product details list to the JSP page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        InventoryManagementModel model = new InventoryManagementModel();
        
        try{
            ArrayList<ProductDetails> productDetailsList = model.getAllProductDetails();
            
            request.setAttribute("product_details_list", productDetailsList);
            RequestDispatcher rd = request.getRequestDispatcher("/inventory_management_page.jsp");
            rd.forward(request, response);  
        }
        catch(SQLException e){
            response.getWriter().println(e);
        }     
    }
    
    
    //Sending the searched product details list to the JSP page
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String searchString = request.getParameter("search_string");
        
        InventoryManagementModel model = new InventoryManagementModel();
            
        try{
            ArrayList<ProductDetails> productDetailsList = model.getAllProductDetailsForSearchBar(searchString);
            
            request.setAttribute("product_details_list", productDetailsList);
            RequestDispatcher rd = request.getRequestDispatcher("/inventory_management_page.jsp");
            rd.forward(request, response);  
        }
        catch(SQLException e){
            response.getWriter().println(e);
        }    
    }  
}
