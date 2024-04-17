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
@WebServlet(name = "ProductStockRetrieveServlet", urlPatterns = {"/ProductStockRetrieveServlet"})
public class ProductStockRetrieveServlet extends HttpServlet {
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Sending the stock list of the product to the JSP page
        if("stock_list".equals(request.getParameter("submit"))){
            
            String productId = request.getParameter("product_id");

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                db_connection db_conn = new db_connection();

                try{

                    //Connect to the database
                    Connection con = DriverManager.getConnection(db_conn.url, db_conn.uname, db_conn.password);

                    //Create a statement
                    Statement stmt = con.createStatement();

                    //Execute the SQL query to retrieve the product stock list
                    ResultSet rs1 = stmt.executeQuery("SELECT * FROM Product_stock WHERE product_id="+productId+" ORDER BY stock_id DESC;");

                    //Store the results in an arraylist of the ProductStock class
                    ArrayList<ProductStock> productStockList = new ArrayList();

                    //Add the data to the array list
                    while(rs1.next()){

                        ProductStock ps = new ProductStock(Integer.parseInt(productId), rs1.getInt("stock_id"), rs1.getString("supplier_name"), rs1.getString("date_time"), rs1.getFloat("buying_price"), rs1.getInt("supplied_quantity"), rs1.getInt("available_quantity"));
                        productStockList.add(ps);

                    }
                    rs1.close();

                    //Execute the SQL query to retrieve the product name
                    ResultSet rs2 = stmt.executeQuery("SELECT name FROM Product WHERE product_id="+productId+";");

                    if(rs2.next()){
                        
                        request.setAttribute("product_name", rs2.getString("Product.name"));
                        
                        rs2.close();
                        
                        //Execute the SQL query to retrieve the total available quantity
                        ResultSet rs3 = stmt.executeQuery("SELECT SUM(available_quantity) AS total_available_quantity FROM Product_stock WHERE product_id="+productId+";");
                        
                        rs3.next();
                        
                        request.setAttribute("total_available_quantity", Integer.toString(rs3.getInt("total_available_quantity")));
                        
                        rs3.close();
                        
                        stmt.close();
                        con.close();

                        request.setAttribute("product_id", productId);
                        request.setAttribute("product_stock_list", productStockList);
                        request.getRequestDispatcher("/stock_management_page.jsp").forward(request,response);
                    }
                    else{
                        rs2.close();
                        
                        stmt.close();
                        con.close();

                        request.setAttribute("alert_message", "Product does not exist");
                        request.getRequestDispatcher("/inventory_management_page.jsp").forward(request,response);
                    }
                }
                catch(SQLException e2){
                    response.getWriter().println(e2);
                }
            }
            catch (ClassNotFoundException e1) {
                response.getWriter().println(e1);
            }
        }
        
        //Sending the details of a single stock to the JSP page
        if("stock_details".equals(request.getParameter("submit"))){
            
            String stock_id = request.getParameter("stock_id");
        
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");

                try{

                    db_connection db = new db_connection();

                    Connection con = DriverManager.getConnection(db.url, db.uname, db.password);

                    Statement stmt = con.createStatement();

                    ResultSet rs = stmt.executeQuery("SELECT Product_stock.*, Product.name FROM Product_stock, Product WHERE Product.product_id = Product_stock.product_id and Product_stock.stock_id="+stock_id+";");

                    if(rs.next()){

                        ProductStock ps = new ProductStock(rs.getInt("Product_stock.product_id"), rs.getInt("Product_stock.stock_id"), rs.getString("Product_stock.supplier_name"), rs.getString("Product_stock.date_time"), rs.getFloat("Product_stock.buying_price"), rs.getInt("Product_stock.supplied_quantity"), rs.getInt("Product_stock.available_quantity"));

                        request.setAttribute("product_name", rs.getString("Product.name"));

                        rs.close();
                        stmt.close();
                        con.close();

                        request.setAttribute("stock_details", ps);
                        request.getRequestDispatcher("/stock_update_page.jsp").forward(request, response);
                    }
                    else{
                        rs.close();
                        stmt.close();
                        con.close();

                        request.setAttribute("alert_message", "Stock does not exist");
                        request.getRequestDispatcher("/inventory_management_page.jsp").forward(request,response);
                    }
                }
                catch(SQLException e2){
                    response.getWriter().println(e2);
                }
            } 
            catch(ClassNotFoundException e1){
                response.getWriter().println(e1);
            }  
        }
    }  
}
