/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AuthenticationSystem;

import DatabaseConnection.db_connection;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Sithuruwan
 */
@WebServlet(name = "sign_up_servlet", urlPatterns = {"/sign_up_servlet"})
public class sign_up_servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Getting the user inputs
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String re_password = request.getParameter("re_password");
        
        //Checking if the fields are empty, and sending a message
        if("".equals(username) || "".equals(password) || "".equals(re_password)){
            request.setAttribute("message", "Please enter all the relevant details");
            getServletContext().getRequestDispatcher("/sign_up_page.jsp").forward(request,response);
            return;
        }
        
        //Checking if the password has more than 8 characters, and sending a message
        if(password.length() <8){
            request.setAttribute("message", "The Password must have at least 8 characters");
            getServletContext().getRequestDispatcher("/sign_up_page.jsp").forward(request,response);
            return;
        }
        
        //Checking if the two passwords are same, otherwise sending a message
        if(!password.equals(re_password)){
            request.setAttribute("message", "The provided passwords does not match");
            getServletContext().getRequestDispatcher("/sign_up_page.jsp").forward(request,response);
            return;
        }
        
        try
        {
            //Register the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            
            //Creating an object of the db_connection class
            db_connection db_conn = new db_connection();
            
            try{
                //Creating the database connection
                Connection conn = DriverManager.getConnection(db_conn.url, db_conn.uname, db_conn.password);
                
                //Creating a statement
                Statement stmt = conn.createStatement();
                
                //Executing sql query to check if the username is available
                ResultSet rs = stmt.executeQuery("SELECT username FROM User WHERE username='"+username+"';");
                
                //if the username is already taken, sending a message
                //Otherwise, storing the data and sending a message
                while(rs.next()){
                        
                    //Closing db connection
                    conn.close();

                    request.setAttribute("message", "The username already taken, Please select another username");
                    getServletContext().getRequestDispatcher("/sign_up_page.jsp").forward(request,response);
                    return;
                }
                
                try{
                    //Hashing the password
                    authentication_functions hp = new authentication_functions();
                    String hashed_password = hp.get_SHA_256_SecurePassword(password, hp.getSalt());
                    
                    //Executing the sql query to store the data
                    stmt.executeUpdate("INSERT INTO User(username, password) VALUES('"+username+"', '"+hashed_password+"');");

                    //Close database connection
                    conn.close();
                    
                    request.setAttribute("message", "Successfully Signed Up");
                    getServletContext().getRequestDispatcher("/sign_up_page.jsp").forward(request,response);
                }
                catch(NoSuchAlgorithmException e3){
                    response.getWriter().println(e3);
                }
            }
            catch(SQLException e2){
                response.getWriter().println(e2);
            }
        }
        catch(ClassNotFoundException e1){
            response.getWriter().println(e1);
        }

    }

}
