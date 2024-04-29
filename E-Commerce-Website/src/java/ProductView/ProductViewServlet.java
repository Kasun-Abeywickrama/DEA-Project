/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductView;

import DatabaseConnection.DBConnectionManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.http.Cookie;


@WebServlet(name = "ProductViewServlet", urlPatterns = {"/ProductViewServlet"})
public class ProductViewServlet extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if("get_details".equals(request.getParameter("submit"))){
            
            int product_id = Integer.parseInt(request.getParameter("product_id"));
        
            DBConnectionManager dbcon = new DBConnectionManager();

            try{

                Connection con = dbcon.getDBConnection();

                Statement stmt1 = con.createStatement();
                Statement stmt2 = con.createStatement();

                ResultSet rs = stmt1.executeQuery("SELECT Product.*, Sub_category.name FROM Product,Sub_category WHERE Product.sub_category_id = Sub_category.sub_category_id AND product_id="+product_id+";");

                if(rs.next()){

                    ResultSet qrs = stmt2.executeQuery("SELECT SUM(available_quantity) AS total_available_quantity FROM Product_stock WHERE product_id="+product_id+";");

                    qrs.next();

                    ProductViewDetails p = new ProductViewDetails(product_id, rs.getString("Product.name"), rs.getString("Product.description"), rs.getString("Sub_category.name"), rs.getFloat("Product.selling_price"), qrs.getInt("total_available_quantity"));

                    request.setAttribute("product_details", p);
                    qrs.close();
                }
                else{
                    request.setAttribute("error", "Product Does Not Exist");
                }
                rs.close();
                stmt1.close();
                stmt2.close();
                dbcon.closeDBConnection();

                request.getRequestDispatcher("/product_details.jsp").forward(request, response);
            }
            catch(SQLException e){
                response.getWriter().println(e);
            }    
        }
        
        
        if("add_to_cart".equals(request.getParameter("submit"))){
            
            int product_id = Integer.parseInt(request.getParameter("product_id"));
        
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            int setted_quantity = 0;
            String cookie_name = "Cart-"+Integer.toString(product_id);

            Cookie[] ckarray = request.getCookies();

            if(ckarray != null){

                for(Cookie i: ckarray){

                    if(i.getName().equals(cookie_name)){

                        setted_quantity = Integer.parseInt(i.getValue());
                        break;
                    }
                }
            }

            String cookie_value = Integer.toString(setted_quantity+quantity);

            Cookie ck = new Cookie(cookie_name, cookie_value);

            response.addCookie(ck);

            response.sendRedirect("landing-page.jsp");    
        }  
    }

}
