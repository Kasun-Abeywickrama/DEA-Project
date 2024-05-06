/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingCart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
@WebServlet(name = "ShoppingCartServlet", urlPatterns = {"/ShoppingCartServlet"})
public class ShoppingCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ShoppingCartOperation operation = new ShoppingCartOperation();
        
        try{
            
            //Creating the array list to store the data that must be send to the frontend
            ArrayList<ShoppingCartDetails> sharray = new ArrayList<>();
            
            //Creating the variable to store the sub_total
            float sub_total = 0f;
            
            //Getting the cookies to an array
            Cookie[] ckarray = request.getCookies();
            
            if(ckarray != null){
            
                for(Cookie i: ckarray){
                    
                    if(i.getName().startsWith("Cart-")){
                        
                        String[] parts = i.getName().split("-");

                        int product_id = Integer.parseInt(parts[1]);

                        int quantity = Integer.parseInt(i.getValue());

                        ShoppingCartDetails scd = operation.getProductDetailsByProductId(product_id, quantity);
                        
                        if(scd != null){
                            sharray.add(scd);
                            sub_total += (scd.getSellingPrice()*quantity);
                        }
                    }
                }
            }
            
            sharray.sort((ShoppingCartDetails a, ShoppingCartDetails b) -> a.getProductId() - b.getProductId());
            
            request.setAttribute("shopping_cart_product_list", sharray);
            request.setAttribute("sub_total", String.valueOf(sub_total));
            request.getRequestDispatcher("/checkout_page.jsp").forward(request,response); 
            
        }
        catch(SQLException e){
            response.getWriter().println(e);
        }
    }
    
    
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if("increase_item".equals(request.getParameter("submit"))){
            
            String product_id = request.getParameter("product_id");
            
            String name = "Cart-"+product_id;
            
            Cookie[] ckarray = request.getCookies();
            
            if(ckarray != null){
                
                for(Cookie i: ckarray){

                    if(i.getName().equals(name)){
                        
                        int value = Integer.parseInt(i.getValue());
                        
                        value++;
                        
                        i.setValue(Integer.toString(value));
                        
                        response.addCookie(i);
                        
                        break; 
                    }   
                }
                
            }
            
            response.sendRedirect("ShoppingCartServlet");
        }
        
        
        if("reduce_item".equals(request.getParameter("submit"))){
            
            String product_id = request.getParameter("product_id");
            
            String name = "Cart-"+product_id;
            
            Cookie[] ckarray = request.getCookies();
            
            if(ckarray != null){
                
                for(Cookie i: ckarray){

                    if(i.getName().equals(name)){
                        
                        int value = Integer.parseInt(i.getValue());
                        
                        value--;
                        
                        if(value < 1){
                            break;
                        }
                        
                        i.setValue(Integer.toString(value));
                        
                        response.addCookie(i);
                        
                        break; 
                    }   
                }
                
            }
            
            response.sendRedirect("ShoppingCartServlet");
        }
        
        
        if("remove_item".equals(request.getParameter("submit"))){
            
            String product_id = request.getParameter("product_id");
            
            String name = "Cart-"+product_id;
            
            Cookie[] ckarray = request.getCookies();
            
            if(ckarray != null){
                
                for(Cookie i: ckarray){

                    if(i.getName().equals(name)){
                        
                        i.setMaxAge(0);
                        
                        response.addCookie(i);
                        
                        break; 
                    }   
                }
                
            }
            
            response.sendRedirect("ShoppingCartServlet");
        }
        
    }
    
}
