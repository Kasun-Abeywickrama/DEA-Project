/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InventoryManagement;

import DatabaseConnection.DBConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Sithuruwan
 */
public class InventoryManagementModel {
    
    //This is the model class related to the inventory management database handling
    
    
    //Retrieve all the product details
    public ArrayList<ProductDetails> getProductDetailsList() throws SQLException{
        
        ArrayList<ProductDetails> productDetailsList = new ArrayList<>();
        
        DBConnectionManager dbcon = new DBConnectionManager();
        
        String retrieveProductListQuery = "SELECT product.product_id, product.name ,sub_category.name, main_category.name FROM product, sub_category, main_category WHERE product.sub_category_id = sub_category.sub_category_id and sub_category.main_category_id = main_category.main_category_id ORDER BY product.product_id;";
        String retrieveTotalAvailableQuantityQuery = "SELECT SUM(available_quantity) AS total_available_quantity FROM Product_stock WHERE product_id= ? ;";
        
        try{   
            //Connect to the database
            Connection connection = dbcon.getDBConnection();
               
            //Creating statements
            Statement stmt = connection.createStatement();
            
            PreparedStatement pstmt = connection.prepareStatement(retrieveTotalAvailableQuantityQuery);
                
            //Execute the SQL query to retrieve the product list
            ResultSet rs1 = stmt.executeQuery(retrieveProductListQuery);

            while(rs1.next()){
                    
                int productId = rs1.getInt("product_id");
                
                //Setting the parameter in the prepared statement
                pstmt.setInt(1, productId);
                    
                ResultSet rs2 = pstmt.executeQuery();
                    
                rs2.next();
                    
                String productName = rs1.getString("product.name");
                String subCategory = rs1.getString("sub_category.name");
                String mainCategory = rs1.getString("main_category.name");
                int totalAvailableQuantity = rs2.getInt("total_available_quantity");
                
                ProductDetails pd = new ProductDetails(productId, productName, subCategory, mainCategory, totalAvailableQuantity);
                productDetailsList.add(pd);
                    
                rs2.close();
            }
            rs1.close();
                
            stmt.close();
            pstmt.close();
            dbcon.closeDBConnection();         
        }
        catch(SQLException e){
            System.out.println(e);
            throw e;
        }
        
        return productDetailsList;
    }
    
    
    
    //Retrieve all the product details for the search bar
    public ArrayList<ProductDetails> getProductDetailsListForSearchBar(String searchInput) throws SQLException{
        
        ArrayList<ProductDetails> productDetailsList = new ArrayList<>();
        
        DBConnectionManager dbcon = new DBConnectionManager();
        
        String retrieveProductListQuery = "SELECT product.product_id, product.name ,sub_category.name, main_category.name FROM product, sub_category, main_category WHERE product.sub_category_id = sub_category.sub_category_id and sub_category.main_category_id = main_category.main_category_id and product.name LIKE ? ORDER BY product.product_id;";
        String retrieveTotalAvailableQuantityQuery = "SELECT SUM(available_quantity) AS total_available_quantity FROM Product_stock WHERE product_id = ? ;";
            
        try{  
            //Connect to the database
            Connection connection = dbcon.getDBConnection();
                
            PreparedStatement pstmt1 = connection.prepareStatement(retrieveProductListQuery);
            pstmt1.setString(1, searchInput+"%");
                
            PreparedStatement pstmt2 = connection.prepareStatement(retrieveTotalAvailableQuantityQuery);
                
            //Execute the SQL query to retrieve the searched product list
            ResultSet rs1 = pstmt1.executeQuery();

            while(rs1.next()){
                    
                int productId = rs1.getInt("product_id");
                    
                //Execute the SQL query to get the total stock of the searched product
                pstmt2.setInt(1, productId);
                ResultSet rs2 = pstmt2.executeQuery();
                    
                rs2.next();
                    
                String productName = rs1.getString("product.name");
                String subCategory = rs1.getString("sub_category.name");
                String mainCategory = rs1.getString("main_category.name");
                int totalAvailableQuantity = rs2.getInt("total_available_quantity");
                
                ProductDetails pd = new ProductDetails(productId, productName, subCategory, mainCategory, totalAvailableQuantity);
                productDetailsList.add(pd);
                    
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
        
        return productDetailsList;
    }
    
    
    
    //Retrieve details of a single product
    public ProductDetails getProductDetailsByProductId(int productId) throws SQLException{
        
        ProductDetails pd = null;
        
        DBConnectionManager dbcon = new DBConnectionManager();
        
        String productDetailsQuery = "SELECT product.product_id, product.name ,sub_category.name, main_category.name FROM product, sub_category, main_category WHERE product.sub_category_id = sub_category.sub_category_id and sub_category.main_category_id = main_category.main_category_id AND product_id = ?";
        String retrieveTotalAvailableQuantityQuery = "SELECT SUM(available_quantity) AS total_available_quantity FROM Product_stock WHERE product_id = ? ;";
        
        try{
            
            Connection connection = dbcon.getDBConnection();
            
            PreparedStatement pstmt1 = connection.prepareStatement(productDetailsQuery);
            pstmt1.setInt(1, productId);
            
            PreparedStatement pstmt2 = connection.prepareStatement(retrieveTotalAvailableQuantityQuery);
            pstmt2.setInt(1, productId);
            
            ResultSet rs1 = pstmt1.executeQuery();
            
            if(rs1.next()){
                
                ResultSet rs2 = pstmt2.executeQuery();
                
                rs2.next();
                
                String productName = rs1.getString("product.name");
                String subCategory = rs1.getString("sub_category.name");
                String mainCategory = rs1.getString("main_category.name");
                int totalAvailableQuantity = rs2.getInt("total_available_quantity");
                
                pd = new ProductDetails(productId, productName, subCategory, mainCategory, totalAvailableQuantity); 
                
                rs2.close();
            }
            rs1.close();
            pstmt2.close();
            pstmt1.close();
            dbcon.closeDBConnection();
        }
        catch(SQLException e){
            System.out.println(e);
            throw e;
        }
        
        return pd;
    }
    
    
    
    //Retrive stock list of a product
    public ArrayList<ProductStock> getProductStockListByProductId(int productId) throws SQLException {
        
        ArrayList<ProductStock> productStockList = new ArrayList<>();
        
        DBConnectionManager dbcon = new DBConnectionManager();
        
        String productStockListQuery = "SELECT * FROM Product_stock WHERE product_id = ? ORDER BY stock_id DESC;";

        try{

            //Connect to the database
            Connection connection = dbcon.getDBConnection();

            //Create a statement
            PreparedStatement pstmt = connection.prepareStatement(productStockListQuery);
            pstmt.setInt(1, productId);

            //Execute the SQL query to retrieve the product stock list
            ResultSet rs = pstmt.executeQuery();

            //Add the data to the array list
            while(rs.next()){

                int stockId = rs.getInt("stock_id");
                String supplierName = rs.getString("supplier_name");
                String dateTime = rs.getString("date_time");
                float buyingPrice = rs.getFloat("buying_price");
                int suppliedQuantity = rs.getInt("supplied_quantity");
                int availableQuantity = rs.getInt("available_quantity");
                
                ProductStock ps = new ProductStock(productId, stockId, supplierName, dateTime, buyingPrice, suppliedQuantity, availableQuantity);
                productStockList.add(ps);
            }
            rs.close();

            pstmt.close();
            dbcon.closeDBConnection();
        }
        catch(SQLException e){
            System.out.println(e);
            throw e;
        }
        
        return productStockList;
    }
    
    
    
    //Retrieve stock details by stock id
    public ProductStock getProductStockDetailsByStockId(int stockId) throws SQLException{
        
        ProductStock ps = null;
        
        DBConnectionManager dbcon = new  DBConnectionManager();
        
        String stockQuery = "SELECT * FROM Product_stock WHERE stock_id = ?;";
        
        try{
            Connection connection = dbcon.getDBConnection();
            
            PreparedStatement pstmt = connection.prepareStatement(stockQuery);
            pstmt.setInt(1, stockId);
            
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()){
                int productId = rs.getInt("product_id");
                String supplierName = rs.getString("supplier_name");
                String dateTime = rs.getString("date_time");
                float buyingPrice = rs.getFloat("buying_price");
                int suppliedQuantity = rs.getInt("supplied_quantity");
                int availableQuantity = rs.getInt("available_quantity");
                
                ps = new ProductStock(productId, stockId, supplierName, dateTime, buyingPrice, suppliedQuantity, availableQuantity);
                
            }
            rs.close();
            pstmt.close();
            dbcon.closeDBConnection();
        }
        catch(SQLException e){
            System.out.println(e);
            throw e;
        }
        
        return ps;
    }
    
    
    
    
    //Add product stock
    public String addProductStock(int productId, String supplierName, float buyingPrice, int quantity) throws SQLException{
        
        String message = null;
        
        LocalDateTime dt = LocalDateTime.now();
        DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String dateTime = dtformat.format(dt);
        
        DBConnectionManager dbcon = new DBConnectionManager();
        
        String insertQuery = "INSERT INTO Product_stock(supplier_name, date_time, buying_price, supplied_quantity, available_quantity, product_id) VALUES(?, ?, ?, ?, ?, ?);";
        
        try{
            
            Connection connection = dbcon.getDBConnection();
                    
            PreparedStatement pstmt = connection.prepareStatement(insertQuery);
            pstmt.setString(1, supplierName);
            pstmt.setString(2, dateTime);
            pstmt.setFloat(3, buyingPrice);
            pstmt.setInt(4, quantity);
            pstmt.setInt(5, quantity);
            pstmt.setInt(6, productId);
            
            int rowsAffected = pstmt.executeUpdate();
            
            if(rowsAffected > 0){
                message = "Stock Added Successfully";
            }
            
            pstmt.close();
            dbcon.closeDBConnection();
        }
        catch(SQLException e){
            System.out.println(e);
            throw e;
        }
        
        return message;
    }
    
    
    
    //Update Stock Details
    public String updateProductStockDetails(int stockId, String supplierName, float buyingPrice) throws SQLException {
        
        String message = null;
        
        LocalDateTime dt = LocalDateTime.now();
        DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String dateTime = dtformat.format(dt);
        
        DBConnectionManager dbcon = new DBConnectionManager();
        
        String retrieveStockDetailsQuery = "SELECT * FROM Product_stock WHERE stock_id = ?";
        String checkIfOrdersPlacedQuery = "SELECT * FROM Orders_Product WHERE stock_id = ?;";
        String updateQuery = "UPDATE Product_stock SET supplier_name = ?, buying_price = ?, date_time= ? WHERE stock_id = ?;";
        
        try{
            
            Connection connection = dbcon.getDBConnection();
            
            PreparedStatement pstmt1 = connection.prepareStatement(retrieveStockDetailsQuery);
            pstmt1.setInt(1, stockId);
            
            PreparedStatement pstmt2 = connection.prepareStatement(checkIfOrdersPlacedQuery);
            pstmt2.setInt(1, stockId);
            
            PreparedStatement pstmt3 = connection.prepareStatement(updateQuery);
            pstmt3.setString(1, supplierName);
            pstmt3.setFloat(2, buyingPrice);
            pstmt3.setString(3, dateTime);
            pstmt3.setInt(4, stockId);
            
            ResultSet rs1 = pstmt1.executeQuery();
                    
            if(rs1.next()){
                        
                float bp = rs1.getFloat("buying_price");
                        
                ResultSet rs2 = pstmt2.executeQuery();
                        
                if(rs2.next()){
                            
                    if(bp == buyingPrice){
                                
                        int rowsAffected = pstmt3.executeUpdate();
            
                        if(rowsAffected > 0){
                            message = "Stock Details Updated Successfully";
                        }
                    }
                    else{     
                        message = "Unable to update the details of the stock, Already purchased in several orders";
                    }
                }
                else{
                    int rowsAffected = pstmt3.executeUpdate();
            
                    if(rowsAffected > 0){
                        message = "Stock Details Updated Successfully";
                    }
                }
                rs2.close();
            }
            else{   
                message = "Stock Does Not Exist";
            }
            rs1.close();
            pstmt1.close();
            pstmt2.close();
            pstmt3.close();
            dbcon.closeDBConnection();
        }
        catch(SQLException e){
            System.out.println(e);
            throw e;
        }
        
        return message;
    }
    
    
    
    
    //Add Stock Quantity
    public String addProductStockQuantity(int stockId, int addQuantity) throws SQLException {
        
        String message = null;
        
        LocalDateTime dt = LocalDateTime.now();
        DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String dateTime = dtformat.format(dt);
        
        DBConnectionManager dbcon = new DBConnectionManager();
        
        String updateQuery = "UPDATE Product_stock SET supplied_quantity = supplied_quantity + ?, available_quantity = available_quantity + ?, date_time = ? WHERE stock_id = ? ;";
        
        try{
            
            Connection connection = dbcon.getDBConnection();
            
            PreparedStatement pstmt = connection.prepareStatement(updateQuery);
            pstmt.setInt(1, addQuantity);
            pstmt.setInt(2, addQuantity);
            pstmt.setString(3, dateTime);
            pstmt.setInt(4, stockId);
            
            int rowsAffected = pstmt.executeUpdate();
            
            if(rowsAffected > 0){
                message = "Stock Quantity Updated Successfully";
            }
            
            pstmt.close();
            dbcon.closeDBConnection();
            
        }
        catch(SQLException e){
            System.out.println(e);
            throw e;
        }
        
        return message;
    }
    
    
    
    
    //Reduce Stock Quantity
    public String reduceProductStockQuantity(int stockId, int reduceQuantity) throws SQLException {
        
        String message = null;
        
        LocalDateTime dt = LocalDateTime.now();
        DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String dateTime = dtformat.format(dt);
        
        DBConnectionManager dbcon = new DBConnectionManager();
        
        String checkQuantityQuery = "SELECT * FROM Product_stock WHERE stock_id = ? ;";
        String updateQuery = "UPDATE Product_stock SET supplied_quantity = supplied_quantity - ?, available_quantity = available_quantity - ?, date_time = ? WHERE stock_id = ? ;";
        
        try{
            
            Connection connection = dbcon.getDBConnection();
            
            PreparedStatement pstmt1 = connection.prepareStatement(checkQuantityQuery);
            pstmt1.setInt(1, stockId);
            
            PreparedStatement pstmt2 = connection.prepareStatement(updateQuery);
            pstmt2.setInt(1, reduceQuantity);
            pstmt2.setInt(2, reduceQuantity);
            pstmt2.setString(3, dateTime);
            pstmt2.setInt(4, stockId);
            
            ResultSet rs = pstmt1.executeQuery();
                        
            if(rs.next()){
                if(reduceQuantity > rs.getInt("available_quantity")){
                            
                    message = "Not enough quantity to remove";
                }
                else{
                    
                    int rowsAffected = pstmt2.executeUpdate();
            
                    if(rowsAffected > 0){
                        message = "Stock Quantity Updated Successfully";
                    }     
                }
            }
            else{
                message = "Stock Does Not Exist";
            }
            rs.close();
            pstmt1.close();
            pstmt2.close();
            dbcon.closeDBConnection();
            
        }
        catch(SQLException e){
            System.out.println(e);
            throw e;
        }
        
        return message;
    }
    
    
    
    //Remove Product Stock
    public String removeProductStock(int stock_id) throws SQLException {
        
        String message = null;
        
        DBConnectionManager dbcon = new DBConnectionManager();
        
        String checkDeleteQuery = "SELECT * FROM Orders_Product WHERE stock_id = ?;";
        String deleteQuery = "DELETE FROM Product_stock WHERE stock_id = ?";
        
        try{
            
            Connection connection = dbcon.getDBConnection();
            
            PreparedStatement pstmt1 = connection.prepareStatement(checkDeleteQuery);
            pstmt1.setInt(1, stock_id);
            
            PreparedStatement pstmt2 = connection.prepareStatement(deleteQuery);
            pstmt2.setInt(1, stock_id);
            
            ResultSet rs = pstmt1.executeQuery();
            
            if(rs.next()){
                
                message = "Unable to remove the stock, Already purchased in several orders";
            }
            else{
                int rowsAffected = pstmt2.executeUpdate();
                
                if(rowsAffected > 0){
                    message = "Product Stock Removed Successfully";
                }
            }
            rs.close();
            pstmt1.close();
            pstmt2.close();
            dbcon.closeDBConnection();
        }
        catch(SQLException e){
            System.out.println(e);
            throw e;
        }
        
        return message;
    }
    
}
