/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AuthenticationSystem;

import DatabaseConnection.DBConnectionManager;
import java.sql.*;

/**
 *
 * @author Sithuruwan
 */
public class AuthenticationSystemOperation {
    
   
    //User sign up
    public String signUp(String username, String password) throws SQLException {
        
        String message = null;
        
        DBConnectionManager dbcon = new DBConnectionManager();
        
        String retrieveUserDetailsQuery = "SELECT username FROM User WHERE username = ?;";
        String insertUserQuery = "INSERT INTO User(username, password) VALUES(? , ?);";
        
        try{
            Connection connection = dbcon.getDBConnection();
            
            PreparedStatement pstmt1 = connection.prepareStatement(retrieveUserDetailsQuery);
            pstmt1.setString(1, username);
            
            PreparedStatement pstmt2 = connection.prepareStatement(insertUserQuery);
            
            ResultSet rs = pstmt1.executeQuery();
            
            if(rs.next()){
                
                message = "The username already taken, Please select another username";
            }
            else{
                //Hashing the password
                authentication_functions hp = new authentication_functions();
                String hashed_password = hp.hash_password(password);
                
                pstmt2.setString(1, username);
                pstmt2.setString(2, hashed_password);
                
                pstmt2.executeUpdate();
                
                message = "Successfull";
            }
            rs.close();
            
            pstmt1.close();
            pstmt2.close();
            dbcon.closeDBConnection();
        }
        catch(SQLException e){
            System.out.println(e);
            throw e;
        }
        
        return message;
    }
    
}
