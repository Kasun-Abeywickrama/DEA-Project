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
 
        InventoryManagementModel model = new InventoryManagementModel();
                
        try{
                    
            String message = model.addProductStock(product_id, supplier_name, buying_price, quantity);
            
            if(message != null){
                response.sendRedirect("inventory_management_page.jsp?alert_message="+message);
            }
            else{
                response.sendRedirect("inventory_management_page.jsp?alert_message=Product Does Not Exist");
            }
            
        }
        catch(SQLException e){
            response.sendRedirect("inventory_management_page.jsp?alert_message=Error Inserting Record");
        } 
    } 
    
}
