/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Product;

import DatabaseConnection.db_connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class Product_CRUD_DB_Class {

    Connection conn = null;
    PreparedStatement pstmt = null;

    private void startDBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            db_connection dbConn = new db_connection();
            conn = DriverManager.getConnection(dbConn.url, dbConn.uname, dbConn.password);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Product_CRUD_DB_Class.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void colseConnections() {

        try {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            // Informing about resource closing error
            Logger.getLogger(Product_CRUD_DB_Class.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public boolean addProduct(String name, String price, String description, String image, String subCat) {
        
        System.out.println(name+","+price+","+description+","+image+","+subCat);
        
        startDBConnection();
        
        String query = "insert into product(name,price,description,image,sub_category_id) values (?,?,?,?,?)";
        boolean state = false;
        try {
            
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, name);
            pstmt.setString(2, price);
            pstmt.setString(3, description);
            pstmt.setString(4, image);
            pstmt.setString(5, subCat);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Insertion successful");
                Logger.getLogger(Product_CRUD_DB_Class.class.getName()).log(Level.SEVERE, null, "Insertion successful");
                state = true;
            } else {
                System.out.println("Insertion failed");
                Logger.getLogger(Product_CRUD_DB_Class.class.getName()).log(Level.SEVERE, null, "Insertion failed");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Product_CRUD_DB_Class.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            
            colseConnections();
            
            return state;
        }
        
    }
    
    
    
    

}
