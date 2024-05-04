/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductView;

import java.io.IOException;
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
        
            ProductViewModel model = new ProductViewModel();

            try{

                ProductViewDetails pvd = model.getProductDetailsByProductId(product_id);
                
                if(pvd != null){
                    
                    request.setAttribute("product_details", pvd);
                    request.getRequestDispatcher("/product_details.jsp").forward(request, response);
                }
                else{
                    response.sendRedirect("landing-page.jsp?alert=Product Does Not Exist");
                }    
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

            response.sendRedirect("landing-page.jsp?alert=Product Successfully Added to the Cart");    
        }  
    }

}
