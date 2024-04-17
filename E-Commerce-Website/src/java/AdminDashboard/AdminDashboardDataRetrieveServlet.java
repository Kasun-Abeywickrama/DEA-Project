/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminDashboard;

import DatabaseConnection.DBConnectionManager;
import java.sql.*;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sithuruwan
 */
@WebServlet(name = "AdminDashboardDataRetrieveServlet", urlPatterns = {"/AdminDashboardDataRetrieveServlet"})
public class AdminDashboardDataRetrieveServlet extends HttpServlet {
    

    //This GET request sends the low stock product list and the latest order list to the frontend.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        DBConnectionManager dbcon = new DBConnectionManager();
        
        try{
            
            Connection connection = dbcon.getDBConnection();
            
            Statement stmt = connection.createStatement();
            
            //Getting the low stock product list to an arraylist of the AdminDashboardProductDetails class
            ArrayList<AdminDashboardProductDetails> low_stock_product_list = new ArrayList<>();
            
            ResultSet rs1 = stmt.executeQuery("SELECT Product.product_id, Product.name, SUM(Product_stock.available_quantity) AS total_available_quantity FROM Product LEFT JOIN Product_stock ON Product.product_id = Product_stock.product_id GROUP BY Product.product_id, Product.name ORDER BY total_available_quantity LIMIT 10;");
            
            while(rs1.next()){
                
                AdminDashboardProductDetails pd = new AdminDashboardProductDetails(rs1.getInt("Product.product_id"), rs1.getString("Product.name"), rs1.getInt("total_available_quantity"));
                low_stock_product_list.add(pd);
               
            }
            rs1.close();
            
            //Getting the order list to an arraylist of the AdminDashboardOrderDetails class
            ArrayList<AdminDashboardOrderDetails> order_list = new ArrayList<>();
            
            //Getting the 10 oldest pending orders
            ResultSet rs2 = stmt.executeQuery("SELECT order_id, date_time, total_price, status FROM Orders WHERE status = 'Pending' ORDER BY date_time LIMIT 10;");
            int pending_order_count = 0;
            
            while(rs2.next()){
                
                AdminDashboardOrderDetails od = new AdminDashboardOrderDetails(rs2.getInt("order_id"), rs2.getString("date_time"), rs2.getFloat("total_price"), rs2.getString("status"));
                order_list.add(od);
                pending_order_count++;
                
            }
            rs2.close();
            
            //If there is less than 10 pending orders, get completed orders for the remaining space
            if(pending_order_count < 10){
                
                ResultSet rs3 = stmt.executeQuery("SELECT order_id, date_time, total_price, status FROM Orders WHERE status = 'Completed' ORDER BY date_time DESC LIMIT "+(10-pending_order_count)+";");
            
                while(rs3.next()){

                    AdminDashboardOrderDetails od = new AdminDashboardOrderDetails(rs3.getInt("order_id"), rs3.getString("date_time"), rs3.getFloat("total_price"), rs3.getString("status"));
                    order_list.add(od);

                }
                rs3.close();
            }
            
            stmt.close();
            
            dbcon.closeDBConnection();
            
            request.setAttribute("low_stock_product_list", low_stock_product_list);
            request.setAttribute("order_list", order_list);
            
            request.getRequestDispatcher("/admin_dashboard_page.jsp").forward(request,response); 
            
        }
        catch(SQLException e){
            response.getWriter().println(e);
        } 
    }
              
}
