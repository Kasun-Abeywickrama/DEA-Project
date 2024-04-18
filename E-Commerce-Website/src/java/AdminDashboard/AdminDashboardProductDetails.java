/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminDashboard;

/**
 *
 * @author Sithuruwan
 */
public class AdminDashboardProductDetails {
    
    //This class is used to represent the details of a single product
    //This class is used for the admin dashboard
    
    private int productId;
    
    private String productName;
    
    private int totalAvailableQuantity;
    
    private int monthSoldQuantity;
    
    public AdminDashboardProductDetails(int id, String name, int totAvailableQuantity, int monsoldquantity){
        productId = id;
        productName = name;
        totalAvailableQuantity = totAvailableQuantity;
        monthSoldQuantity = monsoldquantity;
    }
    
    public int getProductId(){
        return productId;
    }
    
    public String getProductName(){
        return productName;
    }
    
    public int getTotalAvailableQuantity(){
        return totalAvailableQuantity;
    }
    
    public int getMonthSoldQuantity(){
        return monthSoldQuantity;
    }
    
}
