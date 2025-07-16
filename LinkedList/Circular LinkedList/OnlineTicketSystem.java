class Ticket {
    int ticketId;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;
    Ticket next;

    Ticket(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
    }
}

class TicketReservationSystem {
    Ticket head = null;

    void addTicket(Ticket newTicket) {
        if (head == null) {
            head = newTicket;
            head.next = head;
        } else {
            Ticket temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newTicket;
            newTicket.next = head;
        }
    }

    void removeTicket(int ticketId) {
        if (head == null) return;

        Ticket curr = head, prev = null;
        do {
            if (curr.ticketId == ticketId) {
                if (curr == head && curr.next == head) {
                    head = null;
                } else if (curr == head) {
                    Ticket temp = head;
                    while (temp.next != head) {
                        temp = temp.next;
                    }
                    head = head.next;
                    temp.next = head;
                } else {
                    prev.next = curr.next;
                }
                return;
            }
            prev = curr;
            curr = curr.next;
        } while (curr != head);
    }

    void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }
        Ticket temp = head;
        do {
            System.out.println("Ticket ID: " + temp.ticketId + ", Customer: " + temp.customerName + ", Movie: " + temp.movieName + ", Seat: " + temp.seatNumber + ", Time: " + temp.bookingTime);
            temp = temp.next;
        } while (temp != head);
    }

    void searchTicket(String query) {
        if (head == null) return;
        Ticket temp = head;
        boolean found = false;
        do {
            if (temp.customerName.equalsIgnoreCase(query) || temp.movieName.equalsIgnoreCase(query)) {
                System.out.println("Ticket ID: " + temp.ticketId + ", Customer: " + temp.customerName + ", Movie: " + temp.movieName);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);
        if (!found) System.out.println("No matching ticket found.");
    }

    int countTickets() {
        if (head == null) return 0;
        int count = 0;
        Ticket temp = head;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);
        return count;
    }
}

class OnlineTicketSystem {
    public static void main(String[] args) {
        TicketReservationSystem system = new TicketReservationSystem();
        system.addTicket(new Ticket(101, "Alice", "Avengers", "A1", "10:00 AM"));
        system.addTicket(new Ticket(102, "Bob", "Avengers", "A2", "10:00 AM"));
        system.addTicket(new Ticket(103, "Charlie", "Batman", "B1", "1:00 PM"));

        System.out.println("All Tickets:");
        system.displayTickets();

        System.out.println("\nSearch for 'Avengers':");
        system.searchTicket("Avengers");

        System.out.println("\nRemoving Ticket ID 102:");
        system.removeTicket(102);
        system.displayTickets();

        System.out.println("\nTotal Tickets: " + system.countTickets());
    }
}