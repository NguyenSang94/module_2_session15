package session15_Gioi1;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Product> productList = new ArrayList<>();
    static Map<Integer, Order> orderMap = new HashMap<>();

     static void main(String[] args) {
        int choice;

        do {
            showMenu();
            choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1 -> addProduct();
                    case 2 -> removeProduct();
                    case 3 -> showProducts();
                    case 4 -> createOrder();
                    case 5 -> addProductToOrder();
                    case 6 -> showOrder();
                    case 0 -> System.out.println("Thoát chương trình");
                    default -> System.out.println("Lựa chọn không hợp lệ");
                }
            } catch (Exception e) {
                System.out.println( e.getMessage());
            }

        } while (choice != 0);
    }

    static void showMenu() {
        System.out.println("===== MENU =====");
        System.out.println("1. Thêm sản phẩm");
        System.out.println("2. Xóa sản phẩm");
        System.out.println("3. Hiển thị sản phẩm");
        System.out.println("4. Tạo đơn hàng");
        System.out.println("5. Thêm sản phẩm vào đơn hàng");
        System.out.println("6. Hiển thị đơn hàng");
        System.out.println("0. Thoát");
        System.out.print("Lựa chọn của bạn: ");
    }
    static void addProduct() {
        System.out.print("Nhập ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nhập tên: ");
        String name = scanner.nextLine();

        System.out.print("Nhập giá: ");
        double price = scanner.nextDouble();

        productList.add(new Product(id, name, price));
        System.out.println("Thêm sản phẩm thành công");
    }
    static void removeProduct() {
        System.out.print("Nhập ID cần xóa: ");
        int id = scanner.nextInt();
        boolean found = false;
        Iterator<Product> iterator = productList.iterator();

        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                found = true;
                break;
            }
        }

        if (!found) {
            throw new NoSuchElementException("Không tìm thấy sản phẩm");
        }
    }
    static void showProducts() {
        if (productList.isEmpty()) {
            System.out.println("Danh sách rỗng");
            return;
        }
        for (Product p : productList) {
            System.out.println(p);
        }
    }
    static void createOrder() {
        System.out.print("Nhập ID đơn hàng: ");
        int orderId = scanner.nextInt();
        orderMap.put(orderId, new Order(orderId));
        System.out.println("Tạo đơn hàng thành công");
    }
    static void addProductToOrder() {
        System.out.print("Nhập ID đơn hàng: ");
        int orderId = scanner.nextInt();

        Order order = orderMap.get(orderId);
        if (order == null) {
            throw new NoSuchElementException("Đơn hàng không tồn tại");
        }
        System.out.print("Nhập ID sản phẩm: ");
        int productId = scanner.nextInt();

        for (Product p : productList) {
            if (p.getId() == productId) {
                order.addProduct(p);
                System.out.println("Thêm sản phẩm vào đơn hàng thành công");
                return;
            }
        }
        throw new NoSuchElementException("Sản phẩm không tồn tại");
    }
    static void showOrder() {
        System.out.print("Nhập ID đơn hàng: ");
        int orderId = scanner.nextInt();

        Order order = orderMap.get(orderId);
        if (order == null) {
            throw new NoSuchElementException("Đơn hàng không tồn tại");
        }
        order.showOrder();
    }
}

