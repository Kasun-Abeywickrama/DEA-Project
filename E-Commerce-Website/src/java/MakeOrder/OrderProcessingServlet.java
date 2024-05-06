/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MakeOrder;

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
import java.util.ArrayList;
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
                
                MakeOrderOperation operation = new MakeOrderOperation();
        
                try{

                    int order_id = operation.insertOrder(newdt, status, total_price, shipping_address, receiver_name, receiver_phone_number, user_id);

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

                                ArrayList<String> ret = operation.updateOrdersProductAndProductStock(product_id, ordered_quantity, order_id);
                                
                                if(Integer.parseInt(ret.get(0)) == 1){
                                    //Success
                                    total_price += Float.parseFloat(ret.get(1));
                                }
                                else{
                                    //not successfull
                                    isSuccess = 0;
                                    break;
                                }
                            }
                        }
                    }

                    if(isSuccess == 0 || isCartHasProducts == 0){

                        operation.orderNotSuccess(order_id);
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

                        operation.orderSuccess(order_id, total_price);

                        response.sendRedirect("order.jsp");
                        return;
                    }
                }
                catch(SQLException e){
                    response.sendRedirect("checkout_page.jsp?alert_message=Order Not Successfull");
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
        
        response.sendRedirect("checkout_page.jsp?alert_message=Order Not Successfull");
    }

}
