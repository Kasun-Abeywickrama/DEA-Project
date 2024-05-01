/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AuthenticationSystem;

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
        
        DBConnectionManager dbcon = new DBConnectionManager();
            
        try{
            //Creating the database connection
            Connection connection = dbcon.getDBConnection();
                
            //Creating a statement
            Statement stmt = connection.createStatement();
                
            //Executing sql query to check if the username is available
            ResultSet rs = stmt.executeQuery("SELECT username FROM User WHERE username='"+username+"';");
                
            //if the username is already taken, sending a message
            //Otherwise, storing the data and sending a message
            while(rs.next()){
                       
                rs.close();
                stmt.close();
                //Closing db connection
                dbcon.closeDBConnection();
                
                response.sendRedirect("sign_up_page.jsp?message=The username already taken, Please select another username");
                return;
            }
              
            //Hashing the password
            authentication_functions hp = new authentication_functions();
            String hashed_password = hp.hash_password(password);
                    
            //Executing the sql query to store the data
            stmt.executeUpdate("INSERT INTO User(username, password) VALUES('"+username+"', '"+hashed_password+"');");

            rs.close();
            stmt.close();
            //Close database connection
            dbcon.closeDBConnection();
                    
            response.sendRedirect("sign_in_page.jsp?error_state=Successfully Signed Up&user_name="+username);
        }
        catch(SQLException e){
            response.getWriter().println(e);
        }
    }
}
