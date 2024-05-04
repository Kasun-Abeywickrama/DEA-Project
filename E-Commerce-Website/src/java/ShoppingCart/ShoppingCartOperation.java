/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingCart;

import DatabaseConnection.DBConnectionManager;
import java.sql.*;

/**
 *
 * @author Sithuruwan
 */
public class ShoppingCartOperation {
    
    
    //Get product details by product id
    public ShoppingCartDetails getProductDetailsByProductId(int productId, int quantity) throws SQLException {
        
        ShoppingCartDetails scd = null;
        
        DBConnectionManager dbcon = new DBConnectionManager();
        
        String retrieveProductDetailsQuery = "SELECT Product.name, Product.selling_price, Sub_category.name FROM Product, Sub_category WHERE Product.sub_category_id = Sub_category.sub_category_id AND product_id = ?;";
        String retrieveTotalAvailableQuantityQuery = "SELECT SUM(available_quantity) AS total_available_quantity FROM Product_stock WHERE product_id = ?;";
        
        try{
            
            Connection connection = dbcon.getDBConnection();
            
            PreparedStatement pstmt1 = connection.prepareStatement(retrieveProductDetailsQuery);
            pstmt1.setInt(1, productId);
            
            PreparedStatement pstmt2 = connection.prepareStatement(retrieveTotalAvailableQuantityQuery);
            pstmt2.setInt(1, productId);
            
            ResultSet rs1 = pstmt1.executeQuery();
            
            if(rs1.next()){
                
                ResultSet rs2 = pstmt2.executeQuery();
                
                rs2.next();
                
                String productName = rs1.getString("Product.name");
                float sellingPrice = rs1.getFloat("product.selling_price");
                String subCategory = rs1.getString("Sub_category.name");
                int totalAvailableQuantity = rs2.getInt("total_available_quantity");
                
                scd = new ShoppingCartDetails(productId, productName, sellingPrice, subCategory, quantity, totalAvailableQuantity);
                rs2.close();
            }
            rs1.close();
            
            pstmt1.close();
            pstmt2.close();
            dbcon.closeDBConnection();
        }
        catch(SQLException e){
            System.out.println(e);
            throw e;
        }
        
        return scd;
    }
    
    
}
