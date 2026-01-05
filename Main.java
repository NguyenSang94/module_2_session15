package session15_Kha1;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MovieManager<Movie> manager = new MovieManager<>();

        while (true) {
            System.out.println("\nChọn chức nắng: ");
            System.out.println("1. Thêm phim mới");
            System.out.println("2. Sửa phim");
            System.out.println("3. Xóa phim");
            System.out.println("4. Hiển thị phim");
            System.out.println("5. Tìm phim theo tên");
            System.out.println("6. Lọc phim rating > 8.0");
            System.out.println("7. Thoát");
            System.out.print("Chọn chức năng: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> addMovie(scanner, manager);
                    case 2 -> editMovie(scanner, manager);
                    case 3 -> deleteMovie(scanner, manager);
                    case 4 -> manager.displayMovies();
                    case 5 -> {
                        System.out.print("Nhập tên phim cần tìm: ");
                        manager.searchByTitle(scanner.nextLine());
                    }
                    case 6 -> manager.filterByRating();
                    case 7 -> {
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

    private static void addMovie(Scanner scanner, MovieManager<Movie> manager) {
        try {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Tên phim: ");
            String title = scanner.nextLine();

            System.out.print("Đạo diễn: ");
            String director = scanner.nextLine();

            System.out.print("Ngày phát hành (yyyy-MM-dd): ");
            LocalDate date = LocalDate.parse(scanner.nextLine());

            System.out.print("Rating: ");
            double rating = scanner.nextDouble();

            manager.addMovie(new Movie(id, title, director, date, rating));
            System.out.println("Thêm phim thành công!");

        } catch (DateTimeParseException e) {
            System.err.println("Sai định dạng ngày (yyyy-MM-dd)");
        } catch (InputMismatchException e) {
            System.err.println("Rating phải là số!");
            scanner.nextLine();
        }
    }

    private static void editMovie(Scanner scanner, MovieManager<Movie> manager) {
        System.out.print("Nhập ID phim cần sửa: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Movie movie = manager.findById(id);
        if (movie == null) {
            System.out.println("Không tìm thấy phim.");
            return;
        }

        try {
            System.out.print("Tên mới: ");
            movie.setTitle(scanner.nextLine());

            System.out.print("Đạo diễn mới: ");
            movie.setDirector(scanner.nextLine());

            System.out.print("Ngày phát hành mới (yyyy-MM-dd): ");
            movie.setReleaseDate(LocalDate.parse(scanner.nextLine()));

            System.out.print("Rating mới: ");
            movie.setRating(scanner.nextDouble());

            System.out.println("Cập nhật thành công!");

        } catch (DateTimeParseException e) {
            System.err.println("Sai định dạng ngày!");
        } catch (InputMismatchException e) {
            System.err.println("Rating phải là số!");
            scanner.nextLine();
        }
    }

    private static void deleteMovie(Scanner scanner, MovieManager<Movie> manager) {
        System.out.print("Nhập ID phim cần xóa: ");
        int id = scanner.nextInt();

        if (manager.deleteMovie(id)) {
            System.out.println("Xóa phim thành công!");
        } else {
            System.out.println("Không tìm thấy phim.");
        }
    }
}

