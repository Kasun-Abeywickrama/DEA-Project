/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockManagement;

import DatabaseConnection.db_connection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sithuruwan
 */
@WebServlet(name = "StockEditServlet", urlPatterns = {"/StockEditServlet"})
public class StockEditServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Stock Add
        if("Add".equals(request.getParameter("submit"))){
            
            int product_id = Integer.parseInt(request.getParameter("product_id"));
            int add_stock = Integer.parseInt(request.getParameter("add_stock"));
                
            try{
                    
                Class.forName("com.mysql.cj.jdbc.Driver");
                    
                db_connection db_conn = new db_connection();
                    
                try{
                        
                    Connection con = DriverManager.getConnection(db_conn.url, db_conn.uname, db_conn.password);
                
                    Statement stmt = con.createStatement();
                    
                    ResultSet rs = stmt.executeQuery("SELECT * FROM Product WHERE product_id = '"+product_id+"';");
                    
                    if(rs.next()){
                        
                        stmt.executeUpdate("UPDATE product SET stock = stock+"+add_stock+" WHERE product_id = "+product_id+" ;");
                        
                        con.close();
                        
                        request.setAttribute("alert_message", "Stock updated successfully");
                        request.getRequestDispatcher("/stock_management_page.jsp").forward(request,response); 
                    }
                    else{
                        con.close();

                        request.setAttribute("alert_message", "Product does not exist");
                        request.getRequestDispatcher("/stock_management_page.jsp").forward(request,response);
                    } 
                }
                catch(SQLException e3){
                    response.getWriter().println(e3);
                }   
            }
            catch(ClassNotFoundException e2){
                response.getWriter().println(e2);
            }
        }
        
        //Stock Remove
        if("Remove".equals(request.getParameter("submit"))){
            
            int product_id = Integer.parseInt(request.getParameter("product_id"));
            int remove_stock = Integer.parseInt(request.getParameter("remove_stock"));

            try{
                    
                Class.forName("com.mysql.cj.jdbc.Driver");
                    
                db_connection db_conn = new db_connection();
                    
                try{
                        
                    Connection con = DriverManager.getConnection(db_conn.url, db_conn.uname, db_conn.password);
                
                    Statement stmt = con.createStatement();
                        
                    ResultSet rs = stmt.executeQuery("SELECT * FROM product WHERE product_id="+product_id+" ;");
                        
                    if(rs.next()){
                        if(remove_stock > rs.getInt("stock")){
                                
                            con.close();
                                
                            request.setAttribute("alert_message", "Not enough stock to remove");
                            request.getRequestDispatcher("/stock_management_page.jsp").forward(request,response);
                        }
                        else{
                            stmt.executeUpdate("UPDATE product SET stock = stock-"+remove_stock+" WHERE product_id = "+product_id+" ;");
                        
                            con.close();
                    
                            request.setAttribute("alert_message", "Stock updated successfuly");
                            request.getRequestDispatcher("/stock_management_page.jsp").forward(request,response);
                        }
                    }
                    else{
                        con.close();

                        request.setAttribute("alert_message", "Product does not exist");
                        request.getRequestDispatcher("/stock_management_page.jsp").forward(request,response);
                    }  
                }
                catch(SQLException e3){
                    response.getWriter().println(e3);
                }   
            }
            catch(ClassNotFoundException e2){
                response.getWriter().println(e2);
            }
        }
    }
}
