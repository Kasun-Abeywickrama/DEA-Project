package CategoryManagement;


public class SubCategoryDetails {
    
    private int subCategoryID;
    private int mainCategoryID;
    private String subCategoryName;
    
    public SubCategoryDetails(int subCategoryID, int mainCategoryID, String subCategoryName) {
        this.subCategoryID = subCategoryID;
        this.mainCategoryID = mainCategoryID;
        this.subCategoryName = subCategoryName;
    }

    public int getSubCategoryID() {
        return subCategoryID;
    }

    public int getMainCategoryID() {
        return mainCategoryID;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }
}
