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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Sithuruwan
 */
@WebServlet(name = "ProductStockUpdateServlet", urlPatterns = {"/ProductStockUpdateServlet"})
public class ProductStockUpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Update the stock details
        if("update_details".equals(request.getParameter("submit"))){
            
            int stock_id = Integer.parseInt(request.getParameter("stock_id"));
            
            String supplier_name = request.getParameter("supplier_name");
            
            float buying_price = Float.parseFloat(request.getParameter("buying_price"));
            
            LocalDateTime dt = LocalDateTime.now();
            
            DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
            
            String newdt = dtformat.format(dt);
            
            DBConnectionManager dbcon = new DBConnectionManager();
                
            try{
                    
                Connection connection = dbcon.getDBConnection();
                    
                Statement stmt = connection.createStatement();
                    
                ResultSet rs1 = stmt.executeQuery("SELECT * FROM Product_stock WHERE stock_id = "+stock_id+";");
                    
                if(rs1.next()){
                        
                    float bp = rs1.getFloat("buying_price");
                        
                    rs1.close();
                        
                    ResultSet rs2 = stmt.executeQuery("SELECT * FROM Orders_Product WHERE stock_id="+stock_id+";");
                        
                    if(rs2.next()){
                            
                        if(bp == buying_price){
                                
                            stmt.executeUpdate("UPDATE Product_stock SET supplier_name='"+supplier_name+"', buying_price="+buying_price+", date_time='"+newdt+"' WHERE stock_id="+stock_id+";");
                        
                            rs2.close();
                            stmt.close();
                            dbcon.closeDBConnection();

                            request.setAttribute("alert_message", "Stock details updated successfully");
                            request.getRequestDispatcher("/inventory_management_page.jsp").forward(request,response); 
                        }
                        else{
                                
                            rs2.close();
                            stmt.close();
                            dbcon.closeDBConnection();
                                
                            request.setAttribute("alert_message", "Unable to update the details of the stock, Already purchased in several orders");
                            request.getRequestDispatcher("/inventory_management_page.jsp").forward(request,response); 
                        }
                    }
                    else{
                        stmt.executeUpdate("UPDATE Product_stock SET supplier_name='"+supplier_name+"', buying_price="+buying_price+", date_time='"+newdt+"' WHERE stock_id="+stock_id+";");
                        
                        rs2.close();
                        stmt.close();
                        dbcon.closeDBConnection();

                        request.setAttribute("alert_message", "Stock details updated successfully");
                        request.getRequestDispatcher("/inventory_management_page.jsp").forward(request,response); 
                    }     
                }
                else{
                    rs1.close();
                    stmt.close();
                    dbcon.closeDBConnection();
                        
                    request.setAttribute("alert_message", "Stock does not exist");
                    request.getRequestDispatcher("/inventory_management_page.jsp").forward(request,response);
                }  
            }
            catch(SQLException e2){
                response.getWriter().println(e2);
            }    
        }
        
            
        //Add Quantity to the stock
        if("add_quantity".equals(request.getParameter("submit"))){
            
            int add_quantity = Integer.parseInt(request.getParameter("add_quantity"));
            
            int stock_id = Integer.parseInt(request.getParameter("stock_id"));
            
            LocalDateTime dt = LocalDateTime.now();
            
            DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
            
            String newdt = dtformat.format(dt);
            
            DBConnectionManager dbcon = new DBConnectionManager();
                    
            try{
                        
                Connection connection = dbcon.getDBConnection();
                
                Statement stmt = connection.createStatement();
                    
                ResultSet rs = stmt.executeQuery("SELECT * FROM Product_stock WHERE stock_id = "+stock_id+";");
                    
                if(rs.next()){
                        
                    stmt.executeUpdate("UPDATE Product_stock SET supplied_quantity = supplied_quantity+"+add_quantity+", available_quantity = available_quantity+"+add_quantity+", date_time = '"+newdt+"' WHERE stock_id = "+stock_id+" ;");
                        
                    rs.close();
                    stmt.close();
                    dbcon.closeDBConnection();
                        
                    request.setAttribute("alert_message", "Stock quantity updated successfully");
                    request.getRequestDispatcher("/inventory_management_page.jsp").forward(request,response); 
                }
                else{
                    rs.close();
                    stmt.close();
                    dbcon.closeDBConnection();

                    request.setAttribute("alert_message", "Stock does not exist");
                    request.getRequestDispatcher("/inventory_management_page.jsp").forward(request,response);
                } 
            }
            catch(SQLException e){
                response.getWriter().println(e);
            }   
        }
        
        
        //Remove Quantity from the stock
        if("remove_quantity".equals(request.getParameter("submit"))){
            
            int remove_quantity = Integer.parseInt(request.getParameter("remove_quantity"));
            
            int stock_id = Integer.parseInt(request.getParameter("stock_id"));
            
            LocalDateTime dt = LocalDateTime.now();
            
            DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
            
            String newdt = dtformat.format(dt);
            
            DBConnectionManager dbcon = new DBConnectionManager();
                    
            try{
                        
                Connection connection = dbcon.getDBConnection();
                
                Statement stmt = connection.createStatement();
                        
                ResultSet rs = stmt.executeQuery("SELECT * FROM Product_stock WHERE stock_id="+stock_id+" ;");
                        
                if(rs.next()){
                    if(remove_quantity > rs.getInt("available_quantity")){
                            
                        rs.close();
                        stmt.close();
                        dbcon.closeDBConnection();
                                
                        request.setAttribute("alert_message", "Not enough quantity to remove");
                        request.getRequestDispatcher("/inventory_management_page.jsp").forward(request,response);
                    }
                    else{
                        stmt.executeUpdate("UPDATE Product_stock SET supplied_quantity = supplied_quantity-"+remove_quantity+", available_quantity = available_quantity-"+remove_quantity+", date_time = '"+newdt+"' WHERE stock_id = "+stock_id+" ;");
                        
                        rs.close();
                        stmt.close();
                        dbcon.closeDBConnection();
                    
                        request.setAttribute("alert_message", "Stock quantity updated successfuly");
                        request.getRequestDispatcher("/inventory_management_page.jsp").forward(request,response);
                    }
                }
                else{
                    rs.close();
                    stmt.close();
                    dbcon.closeDBConnection();

                    request.setAttribute("alert_message", "Stock does not exist");
                    request.getRequestDispatcher("/inventory_management_page.jsp").forward(request,response);
                }  
            }
            catch(SQLException e){
                response.getWriter().println(e);
            }   
        }
    }
    
}
