package ResourcePaths;

/**
 *
 * @author HP
 */
public class ResourcePaths {

    private static final String MAIN_PATH = "C:/Users/HP/Desktop/Git/Personal/DEA-Project/E-Commerce-Website/web/";

    public String getProductImagePath() {
        String imagePath = MAIN_PATH + "images/product_images/";
        return imagePath;
    }

    public String getCategoryImagePath() {
        String imagePath = MAIN_PATH + "images/category_images/";
        return imagePath;
    }

    public String getRelativeProductImagePath() {
        String imagePath = "images/product_images/";
        return imagePath;
    }

    public String getRelativeCategoryImagePath() {
        String imagePath = "images/category_images/";
        return imagePath;
    }

}
