package OrderManagement;

/**
 *
 * @author HP
 */
public class OrderProductsDetails {

    private final int ordersProductId;
    private final double sellingPrice;
    private final int quantity;
    private final String productName;

    public OrderProductsDetails(int ordersProductId, double sellingPrice, int quantity, String productName) {
        this.ordersProductId = ordersProductId;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
        this.productName = productName;
    }

    // Getters
    public int getOrdersProductId() {
        return ordersProductId;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProductName() {
        return productName;
    }
}
