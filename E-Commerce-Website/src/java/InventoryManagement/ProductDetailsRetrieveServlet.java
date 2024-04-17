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
@WebServlet(name = "ProductDetailsRetrieveServlet", urlPatterns = {"/ProductDetailsRetrieveServlet"})
public class ProductDetailsRetrieveServlet extends HttpServlet {

    //Sending the product details list to the JSP page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            db_connection db_conn = new db_connection();
            
            try{
                
                //Connect to the database
                Connection con = DriverManager.getConnection(db_conn.url, db_conn.uname, db_conn.password);
                
                //Create two statements
                Statement stmt1 = con.createStatement();
                
                Statement stmt2 = con.createStatement();
                
                //Execute the SQL query to retrieve the product list
                ResultSet rs1 = stmt1.executeQuery("SELECT product.product_id, product.name ,sub_category.name, main_category.name FROM product, sub_category, main_category WHERE product.sub_category_id = sub_category.sub_category_id and sub_category.main_category_id = main_category.main_category_id ORDER BY product.product_id;");
                
                //Store the results in an arraylist of the ProductDetails class
                ArrayList<ProductDetails> productDetailsList = new ArrayList();

                //Add the data to the array list
                while(rs1.next()){
                    
                    int product_id = rs1.getInt("product_id");
                    
                    //Execute the SQL query to get the total stock of the product
                    ResultSet rs2 = stmt2.executeQuery("SELECT SUM(available_quantity) AS total_available_quantity FROM Product_stock WHERE product_id="+product_id+";");
                    
                    rs2.next();
                    
                    ProductDetails pd = new ProductDetails(product_id, rs1.getString("product.name"), rs1.getString("sub_category.name"), rs1.getString("main_category.name"), rs2.getInt("total_available_quantity"));
                    productDetailsList.add(pd);
                    
                    rs2.close();
                }
                rs1.close();
                
                stmt1.close();
                stmt2.close();
                con.close();
                
                request.setAttribute("product_details_list", productDetailsList);
                request.getRequestDispatcher("/inventory_management_page.jsp").forward(request,response);
                
            }
            catch(SQLException e2){
                response.getWriter().println(e2);
            }
        }
        catch (ClassNotFoundException e1) {
            response.getWriter().println(e1);
        } 
    }
    
    
    //Sending the searched product details list to the JSP page
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String searchString = request.getParameter("search_string");
            
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            db_connection db_conn = new db_connection();
            
            try{
                
                //Connect to the database
                Connection con = DriverManager.getConnection(db_conn.url, db_conn.uname, db_conn.password);
                
                //Create two statements
                Statement stmt1 = con.createStatement();
                
                Statement stmt2 = con.createStatement();
                
                //Execute the SQL query to retrieve the searched product list
                ResultSet rs1 = stmt1.executeQuery("SELECT product.product_id, product.name ,sub_category.name, main_category.name FROM product, sub_category, main_category WHERE product.sub_category_id = sub_category.sub_category_id and sub_category.main_category_id = main_category.main_category_id and product.name LIKE '"+searchString+"%' ORDER BY product.product_id;");
                
                //Store the results in an arraylist of the ProductDetails class
                ArrayList<ProductDetails> productDetailsList = new ArrayList();

                //Add the data to the array list
                while(rs1.next()){
                    
                    int product_id = rs1.getInt("product_id");
                    
                    //Execute the SQL query to get the total stock of the searched product
                    ResultSet rs2 = stmt2.executeQuery("SELECT SUM(available_quantity) AS total_available_quantity FROM Product_stock WHERE product_id="+product_id+";");
                    
                    rs2.next();
                    
                    ProductDetails pd = new ProductDetails(product_id, rs1.getString("product.name"), rs1.getString("sub_category.name"), rs1.getString("main_category.name"), rs2.getInt("total_available_quantity"));
                    productDetailsList.add(pd);
                    
                    rs2.close();
                }
                rs1.close();
                
                stmt1.close();
                stmt2.close();
                con.close();
                
                request.setAttribute("product_details_list", productDetailsList);
                request.getRequestDispatcher("/inventory_management_page.jsp").forward(request,response);
                
            }
            catch(SQLException e2){
                response.getWriter().println(e2);
            }
        }
        catch (ClassNotFoundException e1) {
            response.getWriter().println(e1);
        } 
    }
    
}
