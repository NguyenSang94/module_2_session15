package session15_Kha1;

import java.util.ArrayList;

public class MovieManager<T extends Movie> {

    private ArrayList<T> movies = new ArrayList<>();

    public void addMovie(T movie) {
        movies.add(movie);
    }

    public void displayMovies() {
        if (movies.isEmpty()) {
            System.out.println("Danh sách phim trống.");
            return;
        }
        movies.forEach(System.out::println);
    }

    public T findById(int id) {
        for (T movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    public boolean deleteMovie(int id) {
        T movie = findById(id);
        if (movie != null) {
            movies.remove(movie);
            return true;
        }
        return false;
    }

    public void searchByTitle(String title) {
        boolean found = false;
        for (T movie : movies) {
            if (movie.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println(movie);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy phim");
        }
    }

    public void filterByRating() {
        boolean found = false;
        for (T movie : movies) {
            if (movie.getRating() > 8.0) {
                System.out.println(movie);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không có phim nào có rating > 8.0");
        }
    }
}

