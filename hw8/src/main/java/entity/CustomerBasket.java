package entity;

public class CustomerBasket {
    private int id;
    private int customerId;
    private int productId;
    private int number;
    private Double totalPrice;

    public CustomerBasket(int id, int customerId, int productId, int number, Double totalPrice) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.number = number;
        this.totalPrice = totalPrice;
    }

    public CustomerBasket(int customerId, int productId, int number, Double totalPrice) {
        this.customerId = customerId;
        this.productId = productId;
        this.number = number;
        this.totalPrice = totalPrice;
    }

    public CustomerBasket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CustomerBasket{" +
                "id=" + id +
                ", productId=" + productId +
                ", number=" + number +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
