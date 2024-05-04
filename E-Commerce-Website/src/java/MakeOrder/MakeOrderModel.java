/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MakeOrder;

import DatabaseConnection.DBConnectionManager;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Sithuruwan
 */
public class MakeOrderModel {
    
    //Make Order Model
    
    
    //Insert Order
    public int insertOrder(String dateTime, String status, float totalPrice, String shippingAddress, String receiverName, String receiverPhone, int userId) throws SQLException {
        
        int orderId = 0;
        
        DBConnectionManager dbcon = new DBConnectionManager();
        
        String updateQuery = "INSERT INTO Orders (date_time, status, total_price, shipping_address, receiver_name, receiver_phone_number, user_id) VALUES( ?, ?, ?, ?, ?, ?, ?);";
        
        try{
            
            Connection connection = dbcon.getDBConnection();
            
            PreparedStatement pstmt = connection.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, dateTime);
            pstmt.setString(2, status);
            pstmt.setFloat(3, totalPrice);
            pstmt.setString(4, shippingAddress);
            pstmt.setString(5, receiverName);
            pstmt.setString(6, receiverPhone);
            pstmt.setInt(7, userId);
            
            pstmt.execute();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            orderId = rs.getInt(1);
            rs.close();
            
            pstmt.close();
            dbcon.closeDBConnection();
        }
        catch(SQLException e){
            System.out.println(e);
            throw e;
        }
        
        return orderId;
    }
    
    
    
    
    //update Orders_Product and Product_stock with a ordered product
    public ArrayList<String> orderProduct(int productId, int orderedQuantity, int orderId) throws SQLException {
        
        ArrayList<String> ret = new ArrayList<>();
        ret.add("1");
        ret.add("0.0");
        
        float tp = 0.0f;
        ret.set(1, Float.toString(tp));
        
        DBConnectionManager dbcon = new DBConnectionManager();
        
        String getProductQuery = "SELECT * FROM Product WHERE product_id = ?;";
        String getAllStocksQuery = "SELECT * FROM Product_stock WHERE product_id = ? AND available_quantity > 0 ORDER BY date_time;";
        String getStockQuery = "SELECT * FROM Product_stock WHERE stock_id= ? ;";
        String updateProductStockQuery = "UPDATE Product_stock SET available_quantity = available_quantity - ? WHERE stock_id = ?;";
        String insertOrdersProductQuery = "INSERT INTO Orders_Product (selling_price, quantity, order_id, stock_id) VALUES ( ?, ?, ?, ?);";
        
        try{
            
            Connection connection = dbcon.getDBConnection();
            
            PreparedStatement pstmt1 = connection.prepareStatement(getProductQuery);
            pstmt1.setInt(1, productId);
            
            PreparedStatement pstmt2 = connection.prepareStatement(getAllStocksQuery);
            pstmt2.setInt(1, productId);
            
            PreparedStatement pstmt3 = connection.prepareStatement(getStockQuery);
            
            PreparedStatement pstmt4 = connection.prepareStatement(updateProductStockQuery);
            
            PreparedStatement pstmt5 = connection.prepareStatement(insertOrdersProductQuery);
            
            //Getting the current selling price of the product
            ResultSet rs1 = pstmt1.executeQuery();

            if(rs1.next()){

                //Getting all the stocks of the above product id to a result set (order by the lowest updated date)
                ResultSet rs2 = pstmt2.executeQuery();

                //Loop through the results
                while(rs2.next() && orderedQuantity>0){

                    //Get the record of the stock id
                    pstmt3.setInt(1, rs2.getInt("stock_id"));
                    ResultSet rs3 = pstmt3.executeQuery();

                    if(rs3.next()){

                        //Calculating the reducing amount
                        int reduce_quantity;
                        if(rs3.getInt("available_quantity") > orderedQuantity){
                            reduce_quantity = orderedQuantity;
                        }
                        else{
                            reduce_quantity = rs3.getInt("available_quantity");
                        }

                        if(reduce_quantity != 0){
                            //Reducing the stock
                            pstmt4.setInt(1, reduce_quantity);
                            pstmt4.setInt(2, rs3.getInt("stock_id"));
                            pstmt4.executeUpdate();

                            //Adding the record to the orders_product table
                            pstmt5.setFloat(1, rs1.getFloat("selling_price"));
                            pstmt5.setInt(2, reduce_quantity);
                            pstmt5.setInt(3, orderId);
                            pstmt5.setInt(4, rs3.getInt("stock_id"));
                            pstmt5.executeUpdate();
                        }

                        orderedQuantity -= reduce_quantity;
                        tp += ((float)reduce_quantity*rs1.getFloat("selling_price"));
                        ret.set(1, Float.toString(tp));
                    }
                    rs3.close();
                }
                rs2.close();

                if(orderedQuantity != 0){
                    ret.set(0, "0");
                }    
            }
            else{
                ret.set(0, "0");
            }
            rs1.close();
            
            pstmt1.close();
            pstmt2.close();
            pstmt3.close();
            pstmt4.close();
            pstmt5.close();
            dbcon.closeDBConnection();
        }
        catch(SQLException e){
            System.out.println(e);
            throw e;
        }
        
        return ret;
    }
    
    
    
    //handle order not successfull
    public void orderNotSuccess(int orderId) throws SQLException {
        
        DBConnectionManager dbcon = new DBConnectionManager();
        
        String ordersProductQuery = "SELECT * FROM Orders_Product WHERE order_id = ?;";
        String updateProductStockQuery = "UPDATE Product_stock SET available_quantity = available_quantity + ? WHERE stock_id = ?;";
        String deleteAllOrdersProduct = "DELETE FROM Orders_Product WHERE order_id = ? ;";
        String deleteOrder = "DELETE FROM Orders WHERE order_id = ? ;";
        
        try{
            Connection connection = dbcon.getDBConnection();
            
            PreparedStatement pstmt1 = connection.prepareStatement(ordersProductQuery);
            pstmt1.setInt(1, orderId);
            
            PreparedStatement pstmt2 = connection.prepareStatement(updateProductStockQuery);
            
            PreparedStatement pstmt3 = connection.prepareStatement(deleteAllOrdersProduct);
            pstmt3.setInt(1, orderId);
            
            PreparedStatement pstmt4 = connection.prepareStatement(deleteOrder);
            pstmt4.setInt(1, orderId);
            
            ResultSet rs = pstmt1.executeQuery();

            while(rs.next()){

                pstmt2.setInt(1, rs.getInt("quantity"));
                pstmt2.setInt(2, rs.getInt("stock_id"));
                pstmt2.executeUpdate();
            }
            rs.close();

            pstmt3.executeUpdate();
            pstmt4.executeUpdate();
            
            pstmt1.close();
            pstmt2.close();
            pstmt3.close();
            pstmt4.close();
            dbcon.closeDBConnection();
        }
        catch(SQLException e){
            System.out.println(e);
            throw e;
        } 
    }
    
    
    
    //Order success
    public void orderSuccess(int orderId, float totalPrice) throws SQLException {
        
        DBConnectionManager dbcon = new DBConnectionManager();
        
        String updateQuery = "UPDATE Orders SET total_price = ? WHERE order_id = ?;";
        
        try{
            Connection connection = dbcon.getDBConnection();
            
            PreparedStatement pstmt = connection.prepareStatement(updateQuery);
            pstmt.setFloat(1, totalPrice);
            pstmt.setInt(2, orderId);
            
            pstmt.executeUpdate();
            
            pstmt.close();
            dbcon.closeDBConnection();
        }
        catch(SQLException e){
            System.out.println(e);
            throw e;
        } 
    }
    
}
