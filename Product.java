package session15_Gioi1;

public class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Giá sản phẩm phải lớn hơn 0");
        }
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Tên: " + name +
                ", Giá: " + price;
    }
}
