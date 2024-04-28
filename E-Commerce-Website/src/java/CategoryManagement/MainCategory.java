/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CategoryManagement;

/**
 *
 * @author dewmi
 */
public class MainCategory {
 
    private int mainCategoryId;
    private String mainCategoryName;
    
    // Default constructor
    public MainCategory (){
        
    }
    
    //Parameterized constructor
    public MainCategory (String mainCategoryName)
    {
        this.mainCategoryName = mainCategoryName;
    }
    
    public MainCategory (int mainCategoryId , String mainCategoryName)
    {
        this.mainCategoryId = mainCategoryId;
        this.mainCategoryName = mainCategoryName;
    }
    
    // Getter method for mainCategoryId
    public int getMainCategoryId() {
        return mainCategoryId;
    }

    // Setter method for mainCategoryId
    public void setMainCategoryId(int mainCategoryId) {
        this.mainCategoryId = mainCategoryId;
    }
    
    // Getter method for mainCategoryName
    public String getMainCategoryName() {
        return mainCategoryName;
    }

    // Setter method for mainCategoryName
    public void setMainCategoryName(String mainCategoryName) {
        this.mainCategoryName = mainCategoryName;
    }
    
}
