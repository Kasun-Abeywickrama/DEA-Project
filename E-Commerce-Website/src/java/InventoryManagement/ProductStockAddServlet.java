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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            
        LocalDateTime dt = LocalDateTime.now();
            
        DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
            
        String newdt = dtformat.format(dt);
            
        DBConnectionManager dbcon = new DBConnectionManager();
                
        try{
                    
            Connection connection = dbcon.getDBConnection();
                    
            Statement stmt = connection.createStatement();
                    
            ResultSet rs = stmt.executeQuery("SELECT * FROM Product WHERE product_id = "+product_id+";");
                    
            if(rs.next()){
                        
                stmt.executeUpdate("INSERT INTO Product_stock(supplier_name, date_time, buying_price, supplied_quantity, available_quantity, product_id) VALUES('"+supplier_name+"', '"+newdt+"', "+buying_price+", "+quantity+", "+quantity+", "+product_id+");");
                        
                rs.close();
                stmt.close();
                dbcon.closeDBConnection();
                        
                request.setAttribute("alert_message", "Stock added successfully");
                request.getRequestDispatcher("/inventory_management_page.jsp").forward(request,response);   
            }
            else{
                rs.close();
                stmt.close();
                dbcon.closeDBConnection();
                        
                request.setAttribute("alert_message", "Product does not exist");
                request.getRequestDispatcher("/inventory_management_page.jsp").forward(request,response);
            }  
        }
        catch(SQLException e){
            response.getWriter().println(e);
        } 
    } 
    
}
