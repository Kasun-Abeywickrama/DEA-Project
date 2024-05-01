/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InventoryManagement;

import DatabaseConnection.DBConnectionManager;
import java.io.IOException;
import java.sql.Connection;
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
            
            int productId = Integer.parseInt(request.getParameter("product_id"));

            DBConnectionManager dbcon = new DBConnectionManager();

            try{

                //Connect to the database
                Connection connection = dbcon.getDBConnection();

                //Create a statement
                Statement stmt = connection.createStatement();

                //Execute the SQL query to retrieve the product stock list
                ResultSet rs1 = stmt.executeQuery("SELECT * FROM Product_stock WHERE product_id="+productId+" ORDER BY stock_id DESC;");

                //Store the results in an arraylist of the ProductStock class
                ArrayList<ProductStock> productStockList = new ArrayList();

                //Add the data to the array list
                while(rs1.next()){

                    ProductStock ps = new ProductStock(productId, rs1.getInt("stock_id"), rs1.getString("supplier_name"), rs1.getString("date_time"), rs1.getFloat("buying_price"), rs1.getInt("supplied_quantity"), rs1.getInt("available_quantity"));
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
                    dbcon.closeDBConnection();

                    request.setAttribute("product_id", Integer.toString(productId));
                    request.setAttribute("product_stock_list", productStockList);
                    request.getRequestDispatcher("/stock_management_page.jsp").forward(request,response);
                }
                else{
                    rs2.close();
                        
                    stmt.close();
                    dbcon.closeDBConnection();

                    response.sendRedirect("inventory_management_page.jsp?alert_message=Product does not exist");
                }
            }
            catch(SQLException e){
                response.getWriter().println(e);
            }    
        }
        
        //Sending the details of a single stock to the JSP page
        if("stock_details".equals(request.getParameter("submit"))){
            
            int stock_id = Integer.parseInt(request.getParameter("stock_id"));
        
            DBConnectionManager dbcon = new DBConnectionManager();

            try{

                Connection connection = dbcon.getDBConnection();

                Statement stmt = connection.createStatement();

                ResultSet rs = stmt.executeQuery("SELECT Product_stock.*, Product.name FROM Product_stock, Product WHERE Product.product_id = Product_stock.product_id and Product_stock.stock_id="+stock_id+";");

                if(rs.next()){

                    ProductStock ps = new ProductStock(rs.getInt("Product_stock.product_id"), rs.getInt("Product_stock.stock_id"), rs.getString("Product_stock.supplier_name"), rs.getString("Product_stock.date_time"), rs.getFloat("Product_stock.buying_price"), rs.getInt("Product_stock.supplied_quantity"), rs.getInt("Product_stock.available_quantity"));

                    request.setAttribute("product_name", rs.getString("Product.name"));

                    rs.close();
                    stmt.close();
                    dbcon.closeDBConnection();

                    request.setAttribute("stock_details", ps);
                    request.getRequestDispatcher("/stock_update_page.jsp").forward(request, response);
                }
                else{
                    rs.close();
                    stmt.close();
                    dbcon.closeDBConnection();

                    response.sendRedirect("inventory_management_page.jsp?alert_message=Stock does not exist");
                }
            }
            catch(SQLException e){
                response.getWriter().println(e);
            }       
        }
    }  
}
