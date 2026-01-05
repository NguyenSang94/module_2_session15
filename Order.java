package session15_Gioi1;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private List<Product> products = new ArrayList<>();

    public Order(int orderId) {
        this.orderId = orderId;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product p : products) {
            total += p.getPrice();
        }
        return total;
    }

    public void showOrder() {
        System.out.println("Đơn hàng ID: " + orderId);
        for (Product p : products) {
            System.out.println(p);
        }
        System.out.println("Tổng tiền: " + getTotalPrice());
    }
}

