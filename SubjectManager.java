package session15_Kha2;

import java.util.ArrayList;
import java.util.Optional;

public class SubjectManager<T extends Subject> {

    private ArrayList<T> subjects = new ArrayList<>();

    public void addSubject(T subject) {
        subjects.add(subject);
    }

    public void displaySubjects() {
        if (subjects.isEmpty()) {
            System.out.println("Danh sách môn học trống.");
            return;
        }
        subjects.forEach(System.out::println);
    }

    public void deleteByCode(String code) {
        Optional<T> subject = subjects.stream()
                .filter(s -> s.getCode().equalsIgnoreCase(code))
                .findFirst();

        if (subject.isPresent()) {
            subjects.remove(subject.get());
            System.out.println("Xóa môn học thành công.");
        } else {
            System.out.println("Không tìm thấy môn học với mã đã nhập.");
        }
    }

    public void searchByName(String name) {
        Optional<T> result = subjects.stream()
                .filter(s -> s.getName().toLowerCase().contains(name.toLowerCase()))
                .findFirst();

        if (result.isPresent()) {
            System.out.println(result.get());
        } else {
            System.out.println("Không có môn học phù hợp.");
        }
    }

    public void filterByCredits() {
        subjects.stream()
                .filter(s -> s.getCredits() > 3)
                .forEach(System.out::println);
    }
}

