package entity;

public class Product {
    private int id;
    private int adminId;
    private int categoryId;
    private int brandId;
    private String name;
    private int number;
    private Double price;

    public Product() {
    }

    public Product(int id, int adminId, int categoryId, int brandId, String name, int number, Double price) {
        this.id = id;
        this.adminId = adminId;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.name = name;
        this.number = number;
        this.price = price;
    }

    public Product(int adminId,int categoryId,int brandId, String name, int number, Double price) {
        this.adminId = adminId;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.name = name;
        this.number = number;
        this.price = price;
    }

    public Product(int id, int number, Double price) {
        this.id = id;
        this.number = number;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", brandId=" + brandId +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", price=" + price +
                '}';
    }
}
