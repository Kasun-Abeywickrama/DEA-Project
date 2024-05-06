/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductView;

public class ProductViewDetails {
    
    private int productId;
    
    private String productName;
    
    private String description;
    
    private String subCategory;
    
    private float sellingPrice;
    
    private int totalAvailableQuantity;
    
    public ProductViewDetails(int id, String name, String des, String cat, float price, int totquan){
        productId = id;
        productName = name;
        description = des;
        subCategory = cat;
        sellingPrice = price;
        totalAvailableQuantity = totquan;
    }
    
    public int getProductId(){
        return productId;
    }
    
    public String getProductName(){
        return productName;
    }
    
    public String getDescription(){
        return description;
    }
    
    public String getSubCategory(){
        return subCategory;
    }
    
    public float getSellingPrice(){
        return sellingPrice;
    }
    
    public int getTotalAvailableQuantity(){
        return totalAvailableQuantity;
    }
}
