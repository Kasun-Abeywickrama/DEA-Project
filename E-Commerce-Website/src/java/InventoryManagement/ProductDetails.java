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
public class ProductDetails {
    
    //This is the java class that is used to represent the details of a product.
    private int productId;
    
    private String productName;
    
    private String productSubCategory;
    
    private String productMainCategory;
    
    private int totalAvailableQuantity;
    
    public ProductDetails(int id, String name, String subCategory, String mainCategory, int totAvailableQuantity){
        productId = id;
        productName = name;
        productSubCategory = subCategory;
        productMainCategory = mainCategory;
        totalAvailableQuantity = totAvailableQuantity;
    }
    
    public int getProductId(){
        return productId;
    }
    
    public String getProductName(){
        return productName;
    }
    
    public String getProductSubCategory(){
        return productSubCategory;
    }
    
    public String getProductMainCategory(){
        return productMainCategory;
    }
    
    public int getTotalAvailableQuantity(){
        return totalAvailableQuantity;
    }
    
}
