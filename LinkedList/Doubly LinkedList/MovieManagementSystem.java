// Question 2: Movie Management using Doubly Linked List
class Movie {
    String title, director;
    int year;
    float rating;
    Movie prev, next;

    Movie(String title, String director, int year, float rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
    }
}

class MovieList {
    Movie head, tail;

    void addLast(Movie m) {
        if (head == null) {
            head = tail = m;
        } else {
            tail.next = m;
            m.prev = tail;
            tail = m;
        }
    }

    void removeByTitle(String title) {
        Movie temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                if (temp == head) head = temp.next;
                if (temp == tail) tail = temp.prev;
                if (temp.prev != null) temp.prev.next = temp.next;
                if (temp.next != null) temp.next.prev = temp.prev;
                return;
            }
            temp = temp.next;
        }
    }

    void searchByDirector(String director) {
        Movie temp = head;
        while (temp != null) {
            if (temp.director.equalsIgnoreCase(director))
                System.out.println("Found: " + temp.title);
            temp = temp.next;
        }
    }

    void displayForward() {
        Movie temp = head;
        while (temp != null) {
            System.out.println(temp.title + " | " + temp.director + " | " + temp.year + " | " + temp.rating);
            temp = temp.next;
        }
    }

    void displayReverse() {
        Movie temp = tail;
        while (temp != null) {
            System.out.println(temp.title + " | " + temp.director + " | " + temp.year + " | " + temp.rating);
            temp = temp.prev;
        }
    }
}

public class MovieManagementSystem {
    public static void main(String[] args) {
        MovieList list = new MovieList();

        list.addLast(new Movie("Inception", "Nolan", 2010, 8.8f));
        list.addLast(new Movie("Interstellar", "Nolan", 2014, 8.6f));
        list.addLast(new Movie("Tenet", "Nolan", 2020, 7.5f));

        System.out.println("All Movies (Forward):");
        list.displayForward();

        System.out.println("\nSearch by Director (Nolan):");
        list.searchByDirector("Nolan");

        System.out.println("\nRemove 'Tenet':");
        list.removeByTitle("Tenet");
        list.displayForward();

        System.out.println("\nAll Movies (Reverse):");
        list.displayReverse();
    }
}