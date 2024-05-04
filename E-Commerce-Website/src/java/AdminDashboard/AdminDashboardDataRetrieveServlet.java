/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminDashboard;

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
        
        AdminDashboardModel model = new AdminDashboardModel();
        
        try{
            
            ////Low stock product list
            //Getting the low stock product list to an arraylist of the AdminDashboardProductDetails class
            ArrayList<AdminDashboardProductDetails> low_stock_product_list = model.getLowStockProductList();
            

            ////Status Order list
            //Getting the status order list to an arraylist of the AdminDashboardOrderDetails class
            ArrayList<AdminDashboardOrderDetails> status_order_list = model.getStatusOrderList();
            
            
            ////Monthly top ordered product list
            //Getting the top sold products of the month to an arraylist of the AdminDashboardProductDetails class
            ArrayList<AdminDashboardProductDetails>  month_top_ordered_product_list = model.getMonthlyTopOrderedProducts();
            
            
            ///Total orders of the month and the delivered orders of the month
            ArrayList<String> arrList = model.getTotalOrdersAndDeliveredOrdersOfTheMonth();
            
            request.setAttribute("month_total_orders", arrList.get(0));
            request.setAttribute("month_delivered_orders", arrList.get(1));
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
