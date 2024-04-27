/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingCart;

/**
 *
 * @author DELL
 */
public class ShoppingCartDetails {
    
    private int productId;
    
    private String productName;
    
    private float sellingPrice;
    
    private String subCategoryName;
    
    private int quantity;
    
    private int totalAvailableQuantity;
    
    public ShoppingCartDetails(int pid, String name, float price, String subCat, int quan, int totquan){
        productId = pid;
        productName = name;
        sellingPrice = price;
        subCategoryName = subCat;
        quantity = quan;
        totalAvailableQuantity = totquan;
    }
    
    public int getProductId(){
        return productId;
    }
    
    public String getProductName(){
        return productName;
    }
    
    public float getSellingPrice(){
        return sellingPrice;
    }
    
    public String getSubCategoryName(){
        return subCategoryName;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public int getTotalAvailableQuantity(){
        return totalAvailableQuantity;
    }
}
