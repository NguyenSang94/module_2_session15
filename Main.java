package session15_Kha2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SubjectManager<Subject> manager = new SubjectManager<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {
            System.out.println("\n===== QUẢN LÝ MÔN HỌC =====");
            System.out.println("1. Hiển thị danh sách môn học");
            System.out.println("2. Thêm môn học");
            System.out.println("3. Xóa môn học theo mã");
            System.out.println("4. Tìm kiếm môn học theo tên");
            System.out.println("5. Lọc môn học có tín chỉ > 3");
            System.out.println("6. Thoát");
            System.out.print("Chọn chức năng: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> manager.displaySubjects();
                    case 2 -> addSubject(scanner, manager, formatter);
                    case 3 -> {
                        System.out.print("Nhập mã môn cần xóa: ");
                        manager.deleteByCode(scanner.nextLine());
                    }
                    case 4 -> {
                        System.out.print("Nhập tên môn cần tìm: ");
                        manager.searchByName(scanner.nextLine());
                    }
                    case 5 -> manager.filterByCredits();
                    case 6 -> {
                        System.out.println("Thoát chương trình.");
                        return;
                    }
                    default -> System.out.println("Lựa chọn không hợp lệ.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Lỗi: Vui lòng nhập số!");
                scanner.nextLine();
            }
        }
    }

    private static void addSubject(Scanner scanner, SubjectManager<Subject> manager, DateTimeFormatter formatter) {
        try {
            System.out.print("Mã môn: ");
            String code = scanner.nextLine();

            System.out.print("Tên môn: ");
            String name = scanner.nextLine();

            System.out.print("Số tín chỉ: ");
            int credits = scanner.nextInt();
            scanner.nextLine();

            if (credits <= 0 || credits > 10) {
                throw new IllegalArgumentException("Số tín chỉ phải từ 1 đến 10.");
            }
            System.out.print("Ngày bắt đầu (yyyy-MM-dd): ");
            LocalDate startDate = LocalDate.parse(scanner.nextLine(), formatter);

            manager.addSubject(new Subject(code, name, credits, startDate));
            System.out.println("Thêm môn học thành công!");
        } catch (InputMismatchException e) {
            System.err.println("Số tín chỉ phải là số nguyên.");
            scanner.nextLine();
        } catch (DateTimeParseException e) {
            System.err.println("Sai định dạng ngày (yyyy-MM-dd).");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

