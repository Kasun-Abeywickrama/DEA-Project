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
@WebServlet(name = "ProductStockRemoveServlet", urlPatterns = {"/ProductStockRemoveServlet"})
public class ProductStockRemoveServlet extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Remove a stock    
        int stock_id = Integer.parseInt(request.getParameter("stock_id"));
            
        InventoryManagementModel model = new InventoryManagementModel();
                
        try{
                    
            String message = model.removeProductStock(stock_id);
            
            if(message != null){
                response.sendRedirect("inventory_management_page.jsp?alert_message="+message);
            }
            else{
                response.sendRedirect("inventory_management_page.jsp?alert_message=Stock Does Not Exist");
            }
        }
        catch(SQLException e){
            response.sendRedirect("inventory_management_page.jsp?alert_message=Error Deleting Record");
        }
    } 
    
}
