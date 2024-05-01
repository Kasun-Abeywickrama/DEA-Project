/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MakeOrder;

import DatabaseConnection.DBConnectionManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpSession;


@WebServlet(name = "OrderProcessingServlet", urlPatterns = {"/OrderProcessingServlet"})
public class OrderProcessingServlet extends HttpServlet {

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String receiver_name = request.getParameter("fname");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String area = request.getParameter("area");
        String shipping_address = request.getParameter("address");
        String receiver_phone_number = request.getParameter("tel");
        String status = "Pending";
        float total_price = 0.0f;
        
        LocalDateTime dt = LocalDateTime.now();
        DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");     
        String newdt = dtformat.format(dt);
        
        HttpSession ses = request.getSession(false);
        
        if(ses != null){
            
            if(ses.getAttribute("user_id") != null){
                
                int user_id = Integer.parseInt((String)ses.getAttribute("user_id"));
                
                DBConnectionManager dbcon = new DBConnectionManager();
        
                try{
                    Connection con = dbcon.getDBConnection();

                    Statement stmt1 = con.createStatement();
                    Statement stmt2 = con.createStatement();
                    Statement stmt3 = con.createStatement();
                    Statement stmt4 = con.createStatement();

                    //Insert the record to the order table
                    String sql = "INSERT INTO Orders (date_time, status, total_price, shipping_address, receiver_name, receiver_phone_number, user_id) VALUES('"+newdt+"', '"+status+"', "+total_price+", '"+shipping_address+"', '"+receiver_name+"', '"+receiver_phone_number+"', "+user_id+");";

                    PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.execute();

                    ResultSet rs = ps.getGeneratedKeys();
                    rs.next();
                    int order_id = rs.getInt(1);
                    rs.close();            

                    int isSuccess = 1;
                    int isCartHasProducts = 0;

                    //Get the cart products from the cookies and loop through each one of them
                    Cookie[] ck = request.getCookies();

                    //Checking if the cookie array is null
                    if(ck != null){

                        //Loop through the cookie array
                        for(Cookie i: ck){

                            //Accessing only the cookies related to the shopping cart
                            if(i.getName().startsWith("Cart-")){
                                
                                isCartHasProducts = 1;

                                String[] parts = i.getName().split("-");
                                int product_id = Integer.parseInt(parts[1]);
                                int ordered_quantity = Integer.parseInt(i.getValue());

                                //Getting the current selling price of the product
                                ResultSet rs1 = stmt1.executeQuery("SELECT * FROM Product WHERE product_id="+product_id+";");

                                if(rs1.next()){

                                    //Getting all the stocks of the above product id to a result set (order by the lowest updated date)
                                    ResultSet rs2 = stmt2.executeQuery("SELECT * FROM Product_stock WHERE product_id="+product_id+" AND available_quantity > 0 ORDER BY date_time;");

                                    //Loop through the results
                                    while(rs2.next() && ordered_quantity>0){

                                        //Get the record of the stock id
                                        ResultSet rs3 = stmt3.executeQuery("SELECT * FROM Product_stock WHERE stock_id="+rs2.getInt("stock_id")+";");

                                        if(rs3.next()){

                                            //Calculating the reducing amount
                                            int reduce_quantity;
                                            if(rs3.getInt("available_quantity") > ordered_quantity){
                                                reduce_quantity = ordered_quantity;
                                            }
                                            else{
                                                reduce_quantity = rs3.getInt("available_quantity");
                                            }

                                            if(reduce_quantity != 0){
                                                //Reducing the stock
                                                stmt4.executeUpdate("UPDATE Product_stock SET available_quantity = available_quantity - "+reduce_quantity+" WHERE stock_id="+rs3.getInt("stock_id")+";");

                                                //Adding the record to the orders_product table
                                                stmt4.executeUpdate("INSERT INTO Orders_Product (selling_price, quantity, order_id, stock_id) VALUES ("+rs1.getFloat("selling_price")+", "+reduce_quantity+", "+order_id+", "+rs3.getInt("stock_id")+");");
                                            }

                                            ordered_quantity -= reduce_quantity;
                                            total_price += ((float)reduce_quantity*rs1.getFloat("selling_price"));
                                        }
                                        rs3.close();
                                    }
                                    rs2.close();

                                    if(ordered_quantity != 0){

                                        isSuccess = 0;
                                        break;
                                    }    
                                }
                                else{
                                    isSuccess = 0;
                                    break;
                                }
                                rs1.close();
                            }
                        }
                    }

                    if(isSuccess == 0 || isCartHasProducts == 0){

                        ResultSet rs4 = stmt3.executeQuery("SELECT * FROM Orders_Product WHERE order_id="+order_id+";");

                        while(rs4.next()){

                            stmt4.executeUpdate("UPDATE Product_stock SET available_quantity = available_quantity+"+rs4.getInt("quantity")+" WHERE stock_id="+rs4.getInt("stock_id")+";");

                        }
                        rs4.close();

                        stmt4.executeUpdate("DELETE FROM Orders_Product WHERE order_id = "+order_id+";");
                        stmt4.executeUpdate("DELETE FROM Orders WHERE order_id = "+order_id+";");

                    }
                    else{

                        if(ck != null){
                            for(Cookie i: ck){
                                if(i.getName().startsWith("Cart-")){

                                    i.setMaxAge(0);
                                    response.addCookie(i);
                                }
                            }
                        }

                        stmt4.executeUpdate("UPDATE Orders SET total_price="+total_price+" WHERE order_id="+order_id+";");

                        response.sendRedirect("order.jsp");
                        return;
                    }

                    stmt1.close();
                    stmt2.close();
                    stmt3.close();
                    stmt4.close();
                    dbcon.closeDBConnection();

                }
                catch(SQLException e){
                    response.getWriter().println(e);
                } 
            }
            else{
                response.sendRedirect("sign_in_page.jsp");
                return;
            }
        }
        else{
            response.sendRedirect("sign_in_page.jsp");
            return;
        }
        
        response.sendRedirect("checkout_page.jsp");
    }

}
