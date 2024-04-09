/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockManagement;

/**
 *
 * @author Sithuruwan
 */
public class ProductStock {
    
    //This is the java class that is used to represent the stock data of a product.
    private int productId;
    
    private String productName;
    
    private String productSubCategory;
    
    private String productMainCategory;
    
    private int productStock;
    
    public ProductStock(int id, String name, String subCategory, String mainCategory, int stock){
        productId = id;
        productName = name;
        productSubCategory = subCategory;
        productMainCategory = mainCategory;
        productStock = stock;
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
    
    public int getProductStock(){
        return productStock;
    }
    
}
