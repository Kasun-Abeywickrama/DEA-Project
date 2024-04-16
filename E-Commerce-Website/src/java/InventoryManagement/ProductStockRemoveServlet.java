/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InventoryManagement;

import DatabaseConnection.db_connection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            
        try{
                
            Class.forName("com.mysql.cj.jdbc.Driver");
                
            db_connection db_conn = new db_connection();
                
            try{
                    
                Connection con = DriverManager.getConnection(db_conn.url, db_conn.uname, db_conn.password);
                    
                Statement stmt = con.createStatement();
                    
                ResultSet rs1 = stmt.executeQuery("SELECT * FROM Product_stock WHERE stock_id = '"+stock_id+"';");
                    
                if(rs1.next()){
                        
                    rs1.close();
                    
                    ResultSet rs2 = stmt.executeQuery("SELECT * FROM Orders_Product WHERE stock_id="+stock_id+";");
                    
                    if(!(rs2.next())){
                        
                        rs2.close();
                        stmt.executeUpdate("DELETE FROM Product_stock WHERE stock_id="+stock_id+";");
                        
                        stmt.close();
                        con.close();
                        
                        request.setAttribute("alert_message", "Stock removed successfully");
                        request.getRequestDispatcher("/inventory_management_page.jsp").forward(request,response);
                    }
                    else{
                        rs2.close();
                        stmt.close();
                        con.close();
                        
                        request.setAttribute("alert_message", "Unable to remove the stock, Already purchased in several orders");
                        request.getRequestDispatcher("/inventory_management_page.jsp").forward(request,response);
                    } 
                }
                else{
                    rs1.close();
                    stmt.close();
                    con.close();
                        
                    request.setAttribute("alert_message", "Stock does not exist");
                    request.getRequestDispatcher("/inventory_management_page.jsp").forward(request,response);
                }  
            }
            catch(SQLException e2){
                response.getWriter().println(e2);
            }
        }
        catch(ClassNotFoundException e1){
            response.getWriter().println(e1);
        }  
    } 
    
}