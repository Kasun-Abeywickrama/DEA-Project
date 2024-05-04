/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminDashboard;

import DatabaseConnection.DBConnectionManager;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Sithuruwan
 */
public class AdminDashboardModel {
    
    //This is the model class related to the admin dashboard
    
    
    ////Low stock product list
    public ArrayList<AdminDashboardProductDetails> getLowStockProductList() throws SQLException {
        
        ArrayList<AdminDashboardProductDetails> adpdList = new ArrayList<>();
        
        DBConnectionManager dbcon = new DBConnectionManager();
        
        String retrieveLowStockProductListQuery = "SELECT Product.product_id, Product.name, SUM(Product_stock.available_quantity) AS total_available_quantity FROM Product LEFT JOIN Product_stock ON Product.product_id = Product_stock.product_id GROUP BY Product.product_id, Product.name ORDER BY total_available_quantity LIMIT 10;";
        
        try{
            Connection connection = dbcon.getDBConnection();
            
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery(retrieveLowStockProductListQuery);
            
            while(rs.next()){
                
                int productId = rs.getInt("Product.product_id");
                String productName = rs.getString("Product.name");
                int totalAvailableQuantity = rs.getInt("total_available_quantity");
                
                AdminDashboardProductDetails adpd = new AdminDashboardProductDetails(productId, productName, totalAvailableQuantity, 0);
                adpdList.add(adpd);
            }
            rs.close();
            stmt.close();
            dbcon.closeDBConnection();
        }
        catch(SQLException e){
            System.out.println(e);
            throw e;
        }
        
        return adpdList;       
    }
    
    
    
    ////Status Order list
    public ArrayList<AdminDashboardOrderDetails> getStatusOrderList() throws SQLException {
        
        ArrayList<AdminDashboardOrderDetails> odList = new ArrayList<>();
        
        DBConnectionManager dbcon = new DBConnectionManager();
        
        String retrievePendingOrdersListQuery = "SELECT order_id, date_time, total_price, status FROM Orders WHERE status = 'Pending' ORDER BY date_time LIMIT 10;";
        String retrieveDispatchedOrdersListQuery = "SELECT order_id, date_time, total_price, status FROM Orders WHERE status = 'Dispatched' ORDER BY date_time LIMIT ? ;";
        String retrieveDeliveredOrdersListQuery = "SELECT order_id, date_time, total_price, status FROM Orders WHERE status = 'Delivered' ORDER BY date_time DESC LIMIT ? ;";
        
        try{
            
            Connection connection = dbcon.getDBConnection();
            
            Statement stmt = connection.createStatement();
            
            PreparedStatement pstmt1 = connection.prepareStatement(retrieveDispatchedOrdersListQuery);
            
            PreparedStatement pstmt2 = connection.prepareStatement(retrieveDeliveredOrdersListQuery);
            
            //Getting the 10 oldest pending orders
            ResultSet rs1 = stmt.executeQuery(retrievePendingOrdersListQuery);
            int status_order_count = 0;
            
            while(rs1.next()){
                
                int orderId = rs1.getInt("order_id");
                String dateTime = rs1.getString("date_time");
                float totalPrice = rs1.getFloat("total_price");
                String status = rs1.getString("status");
                
                AdminDashboardOrderDetails od = new AdminDashboardOrderDetails(orderId, dateTime, totalPrice, status);
                odList.add(od);
                status_order_count++;
            }
            rs1.close();
            
            //If there is less than 10 pending orders, get dispatched orders for the remaining space
            if(status_order_count < 10){
                
                pstmt1.setInt(1, 10-status_order_count);
                
                ResultSet rs2 = pstmt1.executeQuery();
            
                while(rs2.next()){

                    int orderId = rs2.getInt("order_id");
                    String dateTime = rs2.getString("date_time");
                    float totalPrice = rs2.getFloat("total_price");
                    String status = rs2.getString("status");

                    AdminDashboardOrderDetails od = new AdminDashboardOrderDetails(orderId, dateTime, totalPrice, status);
                    odList.add(od);
                    status_order_count++;
                }
                rs2.close();
                
                //If there is less than 10 pending and dispatched orders, get delivered orders for the remaining space
                if(status_order_count < 10){
                    
                    pstmt2.setInt(1, 10-status_order_count);
                
                    ResultSet rs3 = pstmt2.executeQuery();
            
                    while(rs3.next()){

                        int orderId = rs3.getInt("order_id");
                        String dateTime = rs3.getString("date_time");
                        float totalPrice = rs3.getFloat("total_price");
                        String status = rs3.getString("status");

                        AdminDashboardOrderDetails od = new AdminDashboardOrderDetails(orderId, dateTime, totalPrice, status);
                        odList.add(od);
                    }
                    rs3.close();
                }
            }
            stmt.close();
            pstmt1.close();
            pstmt2.close();
            dbcon.closeDBConnection();
        }
        catch(SQLException e){
            System.out.println(e);
            throw e;
        }
        
        return odList;
    }
    
    
    
    ////Monthly top ordered product list
    
    public ArrayList<AdminDashboardProductDetails> getMonthlyTopOrderedProducts() throws SQLException {
        
        ArrayList<AdminDashboardProductDetails> topOrderedProducts = new ArrayList<>();
        
        DBConnectionManager dbcon = new DBConnectionManager();
        
        String retrieveMonthlyTopOrderedProductsQuery = "SELECT Product.product_id, Product.name, SUM(Orders_Product.quantity) AS total_quantity_sold "
                            +"FROM Orders_Product " 
                            +"JOIN Product_stock ON Orders_Product.stock_id = Product_stock.stock_id "
                            +"JOIN Product ON Product_stock.product_id = Product.product_id "
                            +"JOIN Orders ON Orders_Product.order_id = Orders.order_id "
                            +"WHERE YEAR(Orders.date_time) = YEAR(CURRENT_DATE()) "
                            +"AND MONTH(Orders.date_time) = MONTH(CURRENT_DATE()) "
                            +"GROUP BY Product.product_id, Product.name "
                            +"ORDER BY total_quantity_sold DESC "
                            +"LIMIT 5;";
        
        try{
            
            Connection connection = dbcon.getDBConnection();
            
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery(retrieveMonthlyTopOrderedProductsQuery);
            
            while(rs.next()){
                int productId = rs.getInt("Product.product_id");
                String productName = rs.getString("Product.name");
                int totalQuantitySold = rs.getInt("total_quantity_sold");
                
                AdminDashboardProductDetails toppd = new AdminDashboardProductDetails(productId, productName, 0, totalQuantitySold);
                topOrderedProducts.add(toppd);
            }
            rs.close();
            stmt.close();
            dbcon.closeDBConnection();
        }
        catch(SQLException e){
            System.out.println(e);
            throw e;
        }
        
        return topOrderedProducts;
    }
    
    
    
    ////Total orders of the month and the delivered orders of the month
    public ArrayList<String> getTotalOrdersAndDeliveredOrdersOfTheMonth() throws SQLException{
        
        ArrayList<String> arrList = new ArrayList<>();
        arrList.add("0");
        arrList.add("0");
        
        DBConnectionManager dbcon = new DBConnectionManager();
        
        String retrieveTotalOrdersOfMonthQuery = "SELECT COUNT(order_id) FROM Orders WHERE YEAR(date_time) = YEAR(CURRENT_DATE()) AND MONTH(date_time) = MONTH(CURRENT_DATE());";
        String retrieveDeliveredOrdersOfMonthQuery = "SELECT COUNT(order_id) FROM Orders WHERE YEAR(date_time) = YEAR(CURRENT_DATE()) AND MONTH(date_time) = MONTH(CURRENT_DATE()) AND status = 'Delivered';";
        
        try{
            
            Connection connection = dbcon.getDBConnection();
            
            Statement stmt1 = connection.createStatement();
            Statement stmt2 = connection.createStatement();
            
            ResultSet rs1 = stmt1.executeQuery(retrieveTotalOrdersOfMonthQuery);
            rs1.next();
            arrList.set(0, Integer.toString(rs1.getInt("COUNT(order_id)")));
            rs1.close();
            
            ResultSet rs2 = stmt2.executeQuery(retrieveDeliveredOrdersOfMonthQuery);
            rs2.next();
            arrList.set(1, Integer.toString(rs2.getInt("COUNT(order_id)")));
            rs2.close();
            
            stmt1.close();
            stmt2.close();
            dbcon.closeDBConnection();
        }
        catch(SQLException e){
            System.out.println(e);
            throw e;
        }
        
        return arrList;
    }
    
}
