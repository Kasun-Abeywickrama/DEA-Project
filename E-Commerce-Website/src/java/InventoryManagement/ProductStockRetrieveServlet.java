/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InventoryManagement;

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

/**
 *
 * @author Sithuruwan
 */
@WebServlet(name = "ProductStockRetrieveServlet", urlPatterns = {"/ProductStockRetrieveServlet"})
public class ProductStockRetrieveServlet extends HttpServlet {
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Sending the stock list of the product to the JSP page
        if("stock_list".equals(request.getParameter("submit"))){
            
            int productId = Integer.parseInt(request.getParameter("product_id"));
            
            InventoryManagementModel model = new InventoryManagementModel();
            
            try{
                ArrayList<ProductStock> ps = model.getStockListByProductId(productId);
                
                ProductDetails pd = model.getProductDetailsByProductId(productId);
                
                if(pd != null){
                    request.setAttribute("product_id", Integer.toString(pd.getProductId()));
                    request.setAttribute("product_name", pd.getProductName());
                    request.setAttribute("total_available_quantity", Integer.toString(pd.getTotalAvailableQuantity()));
                    request.setAttribute("product_stock_list", ps);

                    RequestDispatcher rd = request.getRequestDispatcher("/stock_management_page.jsp");
                    rd.forward(request, response);
                }
                else{
                    response.sendRedirect("inventory_management_page.jsp?alert_message=Product Does Not Exist");
                }
            }
            catch(SQLException e){
                response.getWriter().println(e);
            }    
        }
        
        
        
        //Sending the details of a single stock to the JSP page
        if("stock_details".equals(request.getParameter("submit"))){
            
            int stock_id = Integer.parseInt(request.getParameter("stock_id"));
            
            InventoryManagementModel model = new InventoryManagementModel();

            try{

                ProductStock ps = model.getProductStockDetailsById(stock_id);
                
                if(ps != null){
                    
                    ProductDetails pd = model.getProductDetailsByProductId(ps.getProductId());
                    
                    if(pd != null){
                        
                        request.setAttribute("stock_details", ps);
                        request.setAttribute("product_name", pd.getProductName());
                        
                        RequestDispatcher rd = request.getRequestDispatcher("/stock_update_page.jsp");
                        rd.forward(request, response);
                    }
                    else{
                        response.sendRedirect("inventory_management_page.jsp?alert_message=Product Does Not Exist");
                    }  
                }
                else{
                    response.sendRedirect("inventory_management_page.jsp?alert_message=Stock Does Not Exist");
                }    
            }
            catch(SQLException e){
                response.getWriter().println(e);
            }       
        }
        
        
    }  
}
