package ProductManagement;

/**
 *
 * @author HP
 */
public class Product {

    private int productId;
    private String name;
    private double buyingPrice;
    private double sellingPrice;
    private String description;
    private String image;
    private int subCategoryId;

    public Product(int productId, String name, double buyingPrice, double sellingPrice, String description, String image, int subCategoryId) {
        this.productId = productId;
        this.name = name;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.description = description;
        this.image = image;
        this.subCategoryId = subCategoryId;
    }

    public Product(String name, double buyingPrice, double sellingPrice, String description, String image, int subCategoryId) {
        this.name = name;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.description = description;
        this.image = image;
        this.subCategoryId = subCategoryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

}
