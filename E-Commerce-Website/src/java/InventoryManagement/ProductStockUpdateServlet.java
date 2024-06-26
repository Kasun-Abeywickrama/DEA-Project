/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InventoryManagement;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sithuruwan
 */
@WebServlet(name = "ProductStockUpdateServlet", urlPatterns = {"/ProductStockUpdateServlet"})
public class ProductStockUpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Update the stock details
        if("update_details".equals(request.getParameter("submit"))){
            
            int stock_id = Integer.parseInt(request.getParameter("stock_id"));
            
            String supplier_name = request.getParameter("supplier_name");
            
            float buying_price = Float.parseFloat(request.getParameter("buying_price"));
            
            InventoryManagementOperation operation = new InventoryManagementOperation();
                
            try{
                    
                String message = operation.updateProductStockDetails(stock_id, supplier_name, buying_price);
                
                if(message != null){
                    response.sendRedirect("ProductDetailsRetrieveServlet?alert_message="+message);
                }
                else{
                    response.sendRedirect("ProductDetailsRetrieveServlet?alert_message=Stock Does Not Exist");
                }
                
            }
            catch(SQLException e){
                response.sendRedirect("ProductDetailsRetrieveServlet?alert_message=Error Updating Stock Details");
            }    
        }
        
           
        
        //Add Quantity to the stock
        if("add_quantity".equals(request.getParameter("submit"))){
            
            int add_quantity = Integer.parseInt(request.getParameter("add_quantity"));
            
            int stock_id = Integer.parseInt(request.getParameter("stock_id"));
            
            InventoryManagementOperation operation = new InventoryManagementOperation();
                    
            try{
                        
                String message = operation.addProductStockQuantity(stock_id, add_quantity);
                
                if(message != null){
                    response.sendRedirect("ProductDetailsRetrieveServlet?alert_message="+message);
                } 
                else{
                    response.sendRedirect("ProductDetailsRetrieveServlet?alert_message=Stock Does Not Exist");
                }
            }
            catch(SQLException e){
                response.sendRedirect("ProductDetailsRetrieveServlet?alert_message=Error Updating Stock Quantity");
            }   
        }
        
        
        
        //Remove Quantity from the stock
        if("remove_quantity".equals(request.getParameter("submit"))){
            
            int remove_quantity = Integer.parseInt(request.getParameter("remove_quantity"));
            
            int stock_id = Integer.parseInt(request.getParameter("stock_id"));
           
            InventoryManagementOperation operation = new InventoryManagementOperation();
                    
            try{
                        
                String message = operation.reduceProductStockQuantity(stock_id, remove_quantity);
                
                if(message != null){
                    response.sendRedirect("ProductDetailsRetrieveServlet?alert_message="+message);
                }
                else{
                    response.sendRedirect("ProductDetailsRetrieveServlet?alert_message=Stock Does Not Exist");
                }
   
            }
            catch(SQLException e){
                response.sendRedirect("ProductDetailsRetrieveServlet?alert_message=Error Updating Stock Quantity");
            }   
        }
        
        
    }
    
}
