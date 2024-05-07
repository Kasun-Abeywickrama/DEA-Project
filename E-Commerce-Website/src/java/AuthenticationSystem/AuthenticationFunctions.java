/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AuthenticationSystem;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Sithuruwan
 */
public class AuthenticationFunctions {
    
    //Generate the hashed password
    //The Apache common codecs library is used to hash the password
    //The password is hashed using the SHA-256 Password Hashing Algorithm
    public String hash_password(String password){
        
        return DigestUtils.sha256Hex(password);
    }
    
}
