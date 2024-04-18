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
public class AdminDashboardOrderDetails {
    
    //This class is used to represent the details of a single order
    //This class is used for the admin dashboard
    
    private int orderId;
    
    private String orderDate;
    
    private float totalPrice;
    
    private String orderStatus;
    
    
    public AdminDashboardOrderDetails(int id, String date, float totprice, String status){
        
        orderId = id;
        orderDate = date;
        totalPrice = totprice;
        orderStatus = status;
        
    }
    
    
    public int getOrderId(){
        return orderId;
    }
    
    public String getOrderDate(){
        return orderDate;
    }
    
    public float getTotalPrice(){
        return totalPrice;
    }
    
    public String getOrderStatus(){
        return orderStatus;
    }
    
}
