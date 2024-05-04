/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InventoryManagement;

import DatabaseConnection.DBConnectionManager;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Sithuruwan
 */
public class InventoryManagementOperation {
    
    
    //Retrieve all the product details
    public ArrayList<ProductDetails> getProductDetailsList() throws SQLException{
        
        ArrayList<ProductDetails> productDetailsList = new ArrayList<>();
        
        DBConnectionManager dbcon = new DBConnectionManager();
        
        String retrieveProductDetailsListQuery = "SELECT Product.product_id, Product.name ,Sub_category.name, Main_category.name FROM Product, Sub_category, Main_category WHERE Product.sub_category_id = Sub_category.sub_category_id and Sub_category.main_category_id = Main_category.main_category_id ORDER BY Product.product_id;";
        String retrieveTotalAvailableQuantityQuery = "SELECT SUM(available_quantity) AS total_available_quantity FROM Product_stock WHERE product_id= ? ;";
        
        try{   
            //Connect to the database
            Connection connection = dbcon.getDBConnection();
               
            //Creating statements
            Statement stmt = connection.createStatement();
            
            PreparedStatement pstmt = connection.prepareStatement(retrieveTotalAvailableQuantityQuery);
                
            //Execute the SQL query to retrieve the product list
            ResultSet rs1 = stmt.executeQuery(retrieveProductDetailsListQuery);

            while(rs1.next()){
                    
                int productId = rs1.getInt("Product.product_id");
                
                //Setting the parameter in the prepared statement
                pstmt.setInt(1, productId);
                    
                ResultSet rs2 = pstmt.executeQuery();
                    
                rs2.next();
                    
                String productName = rs1.getString("Product.name");
                String subCategory = rs1.getString("Sub_category.name");
                String mainCategory = rs1.getString("Main_category.name");
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
        
        String retrieveProductDetailsListForSearchBarQuery = "SELECT Product.product_id, Product.name ,Sub_category.name, Main_category.name FROM Product, Sub_category, Main_category WHERE Product.sub_category_id = Sub_category.sub_category_id and Sub_category.main_category_id = Main_category.main_category_id and Product.name LIKE ? ORDER BY Product.product_id;";
        String retrieveTotalAvailableQuantityQuery = "SELECT SUM(available_quantity) AS total_available_quantity FROM Product_stock WHERE product_id = ? ;";
            
        try{  
            //Connect to the database
            Connection connection = dbcon.getDBConnection();
                
            PreparedStatement pstmt1 = connection.prepareStatement(retrieveProductDetailsListForSearchBarQuery);
            pstmt1.setString(1, searchInput+"%");
                
            PreparedStatement pstmt2 = connection.prepareStatement(retrieveTotalAvailableQuantityQuery);
                
            //Execute the SQL query to retrieve the searched product list
            ResultSet rs1 = pstmt1.executeQuery();

            while(rs1.next()){
                    
                int productId = rs1.getInt("Product.product_id");
                    
                //Execute the SQL query to get the total stock of the searched product
                pstmt2.setInt(1, productId);
                ResultSet rs2 = pstmt2.executeQuery();
                    
                rs2.next();
                    
                String productName = rs1.getString("Product.name");
                String subCategory = rs1.getString("Sub_category.name");
                String mainCategory = rs1.getString("Main_category.name");
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
        
        String retrieveProductDetailsQuery = "SELECT Product.product_id, Product.name ,Sub_category.name, Main_category.name FROM Product, Sub_category, Main_category WHERE Product.sub_category_id = Sub_category.sub_category_id and Sub_category.main_category_id = Main_category.main_category_id AND Product.product_id = ?";
        String retrieveTotalAvailableQuantityQuery = "SELECT SUM(available_quantity) AS total_available_quantity FROM Product_stock WHERE product_id = ? ;";
        
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
                String subCategory = rs1.getString("Sub_category.name");
                String mainCategory = rs1.getString("Main_category.name");
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
        
        String retrieveProductStockListQuery = "SELECT * FROM Product_stock WHERE product_id = ? ORDER BY stock_id DESC;";

        try{

            //Connect to the database
            Connection connection = dbcon.getDBConnection();

            //Create a statement
            PreparedStatement pstmt = connection.prepareStatement(retrieveProductStockListQuery);
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
        
        String retrieveProductStockDetailsQuery = "SELECT * FROM Product_stock WHERE stock_id = ?;";
        
        try{
            Connection connection = dbcon.getDBConnection();
            
            PreparedStatement pstmt = connection.prepareStatement(retrieveProductStockDetailsQuery);
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
        
        String insertProductStockQuery = "INSERT INTO Product_stock(supplier_name, date_time, buying_price, supplied_quantity, available_quantity, product_id) VALUES(?, ?, ?, ?, ?, ?);";
        
        try{
            
            Connection connection = dbcon.getDBConnection();
                    
            PreparedStatement pstmt = connection.prepareStatement(insertProductStockQuery);
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
        
        String retrieveProductStockDetailsQuery = "SELECT * FROM Product_stock WHERE stock_id = ?";
        String retrieveOrdersProductListQuery = "SELECT * FROM Orders_Product WHERE stock_id = ?;";
        String updateProductDetailsQuery = "UPDATE Product_stock SET supplier_name = ?, buying_price = ?, date_time= ? WHERE stock_id = ?;";
        
        try{
            
            Connection connection = dbcon.getDBConnection();
            
            PreparedStatement pstmt1 = connection.prepareStatement(retrieveProductStockDetailsQuery);
            pstmt1.setInt(1, stockId);
            
            PreparedStatement pstmt2 = connection.prepareStatement(retrieveOrdersProductListQuery);
            pstmt2.setInt(1, stockId);
            
            PreparedStatement pstmt3 = connection.prepareStatement(updateProductDetailsQuery);
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
        
        String addProductStockQuantityQuery = "UPDATE Product_stock SET supplied_quantity = supplied_quantity + ?, available_quantity = available_quantity + ?, date_time = ? WHERE stock_id = ? ;";
        
        try{
            
            Connection connection = dbcon.getDBConnection();
            
            PreparedStatement pstmt = connection.prepareStatement(addProductStockQuantityQuery);
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
        
        String retrieveProductStockDetailsQuery = "SELECT * FROM Product_stock WHERE stock_id = ? ;";
        String reduceProductStockQuantityQuery = "UPDATE Product_stock SET supplied_quantity = supplied_quantity - ?, available_quantity = available_quantity - ?, date_time = ? WHERE stock_id = ? ;";
        
        try{
            
            Connection connection = dbcon.getDBConnection();
            
            PreparedStatement pstmt1 = connection.prepareStatement(retrieveProductStockDetailsQuery);
            pstmt1.setInt(1, stockId);
            
            PreparedStatement pstmt2 = connection.prepareStatement(reduceProductStockQuantityQuery);
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
        
        String retrieveOrdersProductListQuery = "SELECT * FROM Orders_Product WHERE stock_id = ?;";
        String deleteProductStockQuery = "DELETE FROM Product_stock WHERE stock_id = ?";
        
        try{
            
            Connection connection = dbcon.getDBConnection();
            
            PreparedStatement pstmt1 = connection.prepareStatement(retrieveOrdersProductListQuery);
            pstmt1.setInt(1, stock_id);
            
            PreparedStatement pstmt2 = connection.prepareStatement(deleteProductStockQuery);
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
