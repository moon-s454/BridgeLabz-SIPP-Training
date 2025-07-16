// Question 1: Student Record Management using Singly Linked List
class Student {
    int rollNo;
    String name;
    int age;
    char grade;
    Student next;

    Student(int rollNo, String name, int age, char grade) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }
}

class StudentList {
    Student head;

    void addFirst(Student s) {
        s.next = head;
        head = s;
    }

    void addLast(Student s) {
        if (head == null) {
            head = s;
            return;
        }
        Student temp = head;
        while (temp.next != null)
            temp = temp.next;
        temp.next = s;
    }

    void deleteByRoll(int rollNo) {
        if (head == null) return;
        if (head.rollNo == rollNo) {
            head = head.next;
            return;
        }
        Student temp = head;
        while (temp.next != null && temp.next.rollNo != rollNo)
            temp = temp.next;
        if (temp.next != null)
            temp.next = temp.next.next;
    }

    void searchByRoll(int rollNo) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) {
                System.out.println("Found: " + temp.name);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Not Found");
    }

    void updateGrade(int rollNo, char grade) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) {
                temp.grade = grade;
                return;
            }
            temp = temp.next;
        }
    }

    void display() {
        Student temp = head;
        while (temp != null) {
            System.out.println(temp.rollNo + " " + temp.name + " " + temp.age + " " + temp.grade);
            temp = temp.next;
        }
    }
}

public class StudentRecordManagement {
    public static void main(String[] args) {
        StudentList list = new StudentList();
        list.addFirst(new Student(1, "Alice", 20, 'A'));
        list.addLast(new Student(2, "Bob", 21, 'B'));
        list.addLast(new Student(3, "Charlie", 22, 'C'));

        System.out.println("All Students:");
        list.display();

        System.out.println("\nSearch for Roll No 2:");
        list.searchByRoll(2);

        System.out.println("\nUpdate Grade for Roll No 2:");
        list.updateGrade(2, 'A');
        list.display();

        System.out.println("\nDelete Student with Roll No 1:");
        list.deleteByRoll(1);
        list.display();
    }
}