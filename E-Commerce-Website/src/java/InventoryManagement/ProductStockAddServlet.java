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
@WebServlet(name = "ProductStockAddServlet", urlPatterns = {"/ProductStockAddServlet"})
public class ProductStockAddServlet extends HttpServlet {
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Add new stock
        int product_id = Integer.parseInt(request.getParameter("product_id"));
            
        String supplier_name = request.getParameter("supplier_name");
            
        float buying_price = Float.parseFloat(request.getParameter("buying_price"));
        
        int quantity = Integer.parseInt(request.getParameter("quantity"));
 
        InventoryManagementOperation operation = new InventoryManagementOperation();
                
        try{
                    
            String message = operation.addProductStock(product_id, supplier_name, buying_price, quantity);
            
            if(message != null){
                response.sendRedirect("ProductDetailsRetrieveServlet?alert_message="+message);
            }
            else{
                response.sendRedirect("ProductDetailsRetrieveServlet?alert_message=Product Does Not Exist");
            }
            
        }
        catch(SQLException e){
            response.sendRedirect("ProductDetailsRetrieveServlet?alert_message=Error Inserting Record");
        } 
    } 
    
}
