class Task {
    int taskId;
    String taskName;
    int priority;
    String dueDate;
    Task next;

    Task(int taskId, String taskName, int priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
    }
}

class TaskScheduler {
    Task head = null;

    void addTask(Task task, int position) {
        if (head == null) {
            head = task;
            task.next = head;
            return;
        }
        if (position == 0) {
            Task temp = head;
            while (temp.next != head) temp = temp.next;
            task.next = head;
            head = task;
            temp.next = head;
        } else {
            Task temp = head;
            int count = 0;
            while (count < position - 1 && temp.next != head) {
                temp = temp.next;
                count++;
            }
            task.next = temp.next;
            temp.next = task;
        }
    }

    void removeTask(int taskId) {
        if (head == null) return;
        Task curr = head, prev = null;
        do {
            if (curr.taskId == taskId) {
                if (curr == head && curr.next == head) {
                    head = null;
                } else if (curr == head) {
                    Task temp = head;
                    while (temp.next != head) temp = temp.next;
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

    void viewCurrentAndNext() {
        if (head == null) {
            System.out.println("No tasks in the scheduler.");
            return;
        }
        System.out.println("Current Task: " + head.taskName);
        head = head.next;
        System.out.println("Moved to Next Task: " + head.taskName);
    }

    void displayTasks() {
        if (head == null) {
            System.out.println("No tasks to display.");
            return;
        }
        Task temp = head;
        do {
            System.out.println("Task ID: " + temp.taskId + ", Name: " + temp.taskName + ", Priority: " + temp.priority + ", Due: " + temp.dueDate);
            temp = temp.next;
        } while (temp != head);
    }

    void searchByPriority(int priority) {
        if (head == null) return;
        Task temp = head;
        boolean found = false;
        do {
            if (temp.priority == priority) {
                System.out.println("Task: " + temp.taskName);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);
        if (!found) System.out.println("No tasks with priority " + priority);
    }
}

class TaskSchedulerApp {
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();
        scheduler.addTask(new Task(1, "Write Report", 2, "2025-07-07"), 0);
        scheduler.addTask(new Task(2, "Code Review", 1, "2025-07-08"), 1);
        scheduler.addTask(new Task(3, "Team Meeting", 3, "2025-07-09"), 2);

        System.out.println("All Tasks:");
        scheduler.displayTasks();

        System.out.println("\nView and move to next task:");
        scheduler.viewCurrentAndNext();

        System.out.println("\nSearch by Priority 1:");
        scheduler.searchByPriority(1);

        System.out.println("\nRemove Task ID 2 and display:");
        scheduler.removeTask(2);
        scheduler.displayTasks();
    }
}