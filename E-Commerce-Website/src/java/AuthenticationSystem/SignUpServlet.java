/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AuthenticationSystem;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sithuruwan
 */
@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Getting the user inputs
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        AuthenticationSystemModel model = new AuthenticationSystemModel();
            
        try{
            
            String message = model.signUp(username, password);
            
            if(message != null){
                if("Successfull".equals(message)){
                    response.sendRedirect("sign_in_page.jsp?error_state=Successfully Signed Up&user_name="+username);
                }
                else{
                    response.sendRedirect("sign_up_page.jsp?message="+message);
                }
            }
            else{
                response.sendRedirect("sign_up_page.jsp?message=User Registration Unsuccessfull");
            }
        }
        catch(SQLException e){
            response.getWriter().println(e);
        }
    }
}
