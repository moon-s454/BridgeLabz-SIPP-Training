class Book {
    String title;
    String author;
    String genre;
    int bookId;
    boolean isAvailable;
    Book prev, next;

    Book(String title, String author, String genre, int bookId, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.isAvailable = isAvailable;
    }
}

class Library {
    Book head, tail;

    void addBook(Book b, int position) {
        if (head == null) {
            head = tail = b;
            return;
        }
        if (position == 0) {
            b.next = head;
            head.prev = b;
            head = b;
        } else {
            Book temp = head;
            int count = 0;
            while (temp.next != null && count < position - 1) {
                temp = temp.next;
                count++;
            }
            b.next = temp.next;
            b.prev = temp;
            if (temp.next != null) temp.next.prev = b;
            else tail = b;
            temp.next = b;
        }
    }

    void removeBook(int bookId) {
        Book temp = head;
        while (temp != null) {
            if (temp.bookId == bookId) {
                if (temp == head) head = temp.next;
                if (temp == tail) tail = temp.prev;
                if (temp.prev != null) temp.prev.next = temp.next;
                if (temp.next != null) temp.next.prev = temp.prev;
                return;
            }
            temp = temp.next;
        }
    }

    void searchByTitleOrAuthor(String query) {
        Book temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(query) || temp.author.equalsIgnoreCase(query)) {
                System.out.println("Found: " + temp.title + " by " + temp.author);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No book found with title or author: " + query);
    }

    void updateAvailability(int bookId, boolean status) {
        Book temp = head;
        while (temp != null) {
            if (temp.bookId == bookId) {
                temp.isAvailable = status;
                return;
            }
            temp = temp.next;
        }
    }

    void displayForward() {
        Book temp = head;
        while (temp != null) {
            System.out.println(temp.bookId + ": " + temp.title + " by " + temp.author + " - " + (temp.isAvailable ? "Available" : "Not Available"));
            temp = temp.next;
        }
    }

    void displayBackward() {
        Book temp = tail;
        while (temp != null) {
            System.out.println(temp.bookId + ": " + temp.title + " by " + temp.author + " - " + (temp.isAvailable ? "Available" : "Not Available"));
            temp = temp.prev;
        }
    }

    int countBooks() {
        int count = 0;
        Book temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }
}

class LibrarySystem {
    public static void main(String[] args) {
        Library lib = new Library();
        lib.addBook(new Book("The Alchemist", "Paulo Coelho", "Fiction", 101, true), 0);
        lib.addBook(new Book("1984", "George Orwell", "Dystopia", 102, true), 1);
        lib.addBook(new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy", 103, false), 2);

        System.out.println("Library (Forward):");
        lib.displayForward();

        System.out.println("\nLibrary (Backward):");
        lib.displayBackward();

        System.out.println("\nSearch by Title or Author 'Orwell':");
        lib.searchByTitleOrAuthor("Orwell");

        System.out.println("\nUpdate Availability of Book ID 103:");
        lib.updateAvailability(103, true);
        lib.displayForward();

        System.out.println("\nRemove Book ID 102:");
        lib.removeBook(102);
        lib.displayForward();

        System.out.println("\nTotal Books in Library: " + lib.countBooks());
    }
}
