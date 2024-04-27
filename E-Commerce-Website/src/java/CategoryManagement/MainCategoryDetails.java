package CategoryManagement;


public class MainCategoryDetails {
    
    private int mainCatogeryId;

    private String mainCatogerytName;
	
	
    public MainCategoryDetails(int id, String name ){
        mainCatogeryId = id;
        mainCatogerytName = name;
    }
	
    public MainCategoryDetails(String name) {
        // TODO Auto-generated constructor stub
    }

    public int getMainCategoryID(){
	return  mainCatogeryId ;
    }
	    
    public String getMainCategoryName(){
	return mainCatogerytName;
    }
}  

