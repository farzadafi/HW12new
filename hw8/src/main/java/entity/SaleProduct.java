package entity;

import java.sql.Date;

public class SaleProduct {
    private int id;
    private int customerId;
    private int productId;
    private int number;
    private Double totalPrice;
    private Date date;

    public SaleProduct() {
    }

    public SaleProduct(int id, int customerId, int productId, int number, Double totalPrice, Date date) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.number = number;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public SaleProduct(int customerId, int productId, int number, Double totalPrice, Date date) {
        this.customerId = customerId;
        this.productId = productId;
        this.number = number;
        this.totalPrice = totalPrice;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SaleProduct{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", productId=" + productId +
                ", number=" + number +
                ", totalPrice=" + totalPrice +
                ", date=" + date +
                '}';
    }
}
