/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InventoryManagement;

/**
 *
 * @author Sithuruwan
 */
public class ProductStock {
    
    //This is the java class that is used to represent a stock of a product.
    private int productId;
    
    private int stockId;
    
    private String supplierName;
    
    private String dateTime;
    
    private float buyingPrice;
    
    private int suppliedQuantity;
    
    private int availableQuantity;
    
    public ProductStock(int pid, int sid, String supName, String dt, float bp, int sq, int aq){
        productId = pid;
        stockId = sid;
        supplierName = supName;
        dateTime = dt;
        buyingPrice = bp;
        suppliedQuantity = sq;
        availableQuantity = aq;
    }
    
    
    //Getters
    public int getProductId(){
        return productId;
    }
    
    public int getStockId(){
        return stockId;
    }
    
    public String getSupplierName(){
        return supplierName;
    }
    
    public String getdateTime(){
        return dateTime;
    }
    
    public float getBuyingPrice(){
        return buyingPrice;
    }
    
    public int getSuppliedQuantity(){
        return suppliedQuantity;
    }
    
    public int getAvailableQuantity(){
        return availableQuantity;
    }
    
    
    //Setters
    public void setProductId(int pid){
        productId = pid;
    }
    
    public void setStockId(int sid){
        stockId = sid;
    }
    
    public void setSupplierName(String name){
        supplierName = name;
    }
    
    public void setDateTime(String dt){
        dateTime = dt;
    }
    
    public void setBuyingPrice(float bp){
        buyingPrice = bp;
    }
    
    public void setSuppliedQuantity(int q){
        suppliedQuantity = q;
    }
    
    public void setAvailableQuantity(int q){
        availableQuantity = q;
    }
    
}
