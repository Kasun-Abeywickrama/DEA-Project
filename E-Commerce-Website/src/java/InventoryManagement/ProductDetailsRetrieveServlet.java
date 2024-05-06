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
        
        InventoryManagementOperation operation = new InventoryManagementOperation();
        
        try{
            ArrayList<ProductDetails> productDetailsList = operation.getProductDetailsList();
            
            request.setAttribute("product_details_list", productDetailsList);
            RequestDispatcher rd = request.getRequestDispatcher("/inventory_management_page.jsp");
            rd.forward(request, response);  
        }
        catch(SQLException e){
            response.getWriter().println(e);
        }     
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Sending the searched product details list to the JSP page
        if("product_list_for_search_bar".equals(request.getParameter("submit"))){
        
            String searchString = request.getParameter("search_string");

            InventoryManagementOperation operation = new InventoryManagementOperation();

            try{
                ArrayList<ProductDetails> productDetailsList = operation.getProductDetailsListForSearchBar(searchString);

                request.setAttribute("product_details_list", productDetailsList);
                RequestDispatcher rd = request.getRequestDispatcher("/inventory_management_page.jsp");
                rd.forward(request, response);  
            }
            catch(SQLException e){
                response.getWriter().println(e);
            }
        }
        
        
        //Sending the details of a product to the stock add page
        if("product_details_for_stock_add".equals(request.getParameter("submit"))){
            
            int product_id = Integer.parseInt(request.getParameter("product_id"));
            
            InventoryManagementOperation operation = new InventoryManagementOperation();
            
            try{
                ProductDetails pd = operation.getProductDetailsByProductId(product_id);
                
                if(pd != null){
                    request.setAttribute("product_id", Integer.toString(pd.getProductId()));
                    request.setAttribute("product_name", pd.getProductName());
                    RequestDispatcher rd = request.getRequestDispatcher("/stock_add_page.jsp");
                    rd.forward(request, response);
                }
                else{
                    response.sendRedirect("ProductDetailsRetrieveServlet?alert_message=Product Does Not Exist");
                } 
            }
            catch(SQLException e){
                response.getWriter().println(e);
            }  
        }
    }
}
