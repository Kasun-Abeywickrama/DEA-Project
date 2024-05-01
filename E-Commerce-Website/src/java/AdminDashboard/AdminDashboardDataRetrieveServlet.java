/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminDashboard;

import DatabaseConnection.DBConnectionManager;
import java.sql.*;
import java.io.IOException;
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
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        DBConnectionManager dbcon = new DBConnectionManager();
        
        try{
            
            Connection connection = dbcon.getDBConnection();
            
            Statement stmt = connection.createStatement();
            
            ////Low stock product list
            
            //Getting the low stock product list to an arraylist of the AdminDashboardProductDetails class
            ArrayList<AdminDashboardProductDetails> low_stock_product_list = new ArrayList<>();
            
            ResultSet rs1 = stmt.executeQuery("SELECT Product.product_id, Product.name, SUM(Product_stock.available_quantity) AS total_available_quantity FROM Product LEFT JOIN Product_stock ON Product.product_id = Product_stock.product_id GROUP BY Product.product_id, Product.name ORDER BY total_available_quantity LIMIT 10;");
            
            while(rs1.next()){
                
                AdminDashboardProductDetails pd = new AdminDashboardProductDetails(rs1.getInt("Product.product_id"), rs1.getString("Product.name"), rs1.getInt("total_available_quantity"), 0);
                low_stock_product_list.add(pd);
               
            }
            rs1.close();
            
            ////Status Order list
            
            //Getting the status order list to an arraylist of the AdminDashboardOrderDetails class
            ArrayList<AdminDashboardOrderDetails> status_order_list = new ArrayList<>();
            
            //Getting the 10 oldest pending orders
            ResultSet rs2 = stmt.executeQuery("SELECT order_id, date_time, total_price, status FROM Orders WHERE status = 'Pending' ORDER BY date_time LIMIT 10;");
            int status_order_count = 0;
            
            while(rs2.next()){
                
                AdminDashboardOrderDetails od = new AdminDashboardOrderDetails(rs2.getInt("order_id"), rs2.getString("date_time"), rs2.getFloat("total_price"), rs2.getString("status"));
                status_order_list.add(od);
                status_order_count++;
                
            }
            rs2.close();
            
            //If there is less than 10 pending orders, get dispatched orders for the remaining space
            if(status_order_count < 10){
                
                ResultSet rs3 = stmt.executeQuery("SELECT order_id, date_time, total_price, status FROM Orders WHERE status = 'Dispatched' ORDER BY date_time LIMIT "+(10-status_order_count)+";");
            
                while(rs3.next()){

                    AdminDashboardOrderDetails od = new AdminDashboardOrderDetails(rs3.getInt("order_id"), rs3.getString("date_time"), rs3.getFloat("total_price"), rs3.getString("status"));
                    status_order_list.add(od);
                    status_order_count++;

                }
                rs3.close();
                
                //If there is less than 10 pending and dispatched orders, get delivered orders for the remaining space
                if(status_order_count < 10){
                
                    ResultSet rs4 = stmt.executeQuery("SELECT order_id, date_time, total_price, status FROM Orders WHERE status = 'Delivered' ORDER BY date_time DESC LIMIT "+(10-status_order_count)+";");
            
                    while(rs4.next()){

                        AdminDashboardOrderDetails od = new AdminDashboardOrderDetails(rs4.getInt("order_id"), rs4.getString("date_time"), rs4.getFloat("total_price"), rs4.getString("status"));
                        status_order_list.add(od);

                    }
                    rs4.close();
                }
            }
            
            ////Monthly top ordered product list
            
            //Getting the top sold products of the month to an arraylist of the AdminDashboardProductDetails class
            ArrayList<AdminDashboardProductDetails>  month_top_ordered_product_list = new ArrayList<>();
            
            ResultSet rs5 = stmt.executeQuery("SELECT Product.product_id, Product.name, SUM(Orders_Product.quantity) AS total_quantity_sold "
                                                +"FROM Orders_Product " 
                                                +"JOIN Product_stock ON Orders_Product.stock_id = Product_stock.stock_id "
                                                +"JOIN Product ON Product_stock.product_id = Product.product_id "
                                                +"JOIN Orders ON Orders_Product.order_id = Orders.order_id "
                                                +"WHERE YEAR(Orders.date_time) = YEAR(CURRENT_DATE()) "
                                                +"AND MONTH(Orders.date_time) = MONTH(CURRENT_DATE()) "
                                                +"GROUP BY Product.product_id, Product.name "
                                                +"ORDER BY total_quantity_sold DESC "
                                                +"LIMIT 5;");
            
            while(rs5.next()){
                
                AdminDashboardProductDetails toppd = new AdminDashboardProductDetails(rs5.getInt("Product.product_id"), rs5.getString("Product.name"), 0, rs5.getInt("total_quantity_sold"));
                month_top_ordered_product_list.add(toppd);
                
            }
            rs5.close();
            
            ///Total orders of the month and the delivered orders of the month
            
            ResultSet rs6 = stmt.executeQuery("SELECT COUNT(order_id) FROM Orders WHERE YEAR(date_time) = YEAR(CURRENT_DATE()) AND MONTH(date_time) = MONTH(CURRENT_DATE());");
            
            rs6.next();
            
            request.setAttribute("month_total_orders", Integer.toString(rs6.getInt("COUNT(order_id)")));
            
            rs6.close();
            
            ResultSet rs7 = stmt.executeQuery("SELECT COUNT(order_id) FROM Orders WHERE YEAR(date_time) = YEAR(CURRENT_DATE()) AND MONTH(date_time) = MONTH(CURRENT_DATE()) AND status = 'Delivered';");
            
            rs7.next();
            
            request.setAttribute("month_delivered_orders", Integer.toString(rs7.getInt("COUNT(order_id)")));
            
            rs7.close();
            
            
            stmt.close();
            
            dbcon.closeDBConnection();
            
            request.setAttribute("low_stock_product_list", low_stock_product_list);
            request.setAttribute("status_order_list", status_order_list);
            request.setAttribute("month_top_ordered_product_list", month_top_ordered_product_list);
            
            request.getRequestDispatcher("/admin_dashboard_page.jsp").forward(request,response); 
            
        }
        catch(SQLException e){
            response.getWriter().println(e);
        } 
    }
              
}
