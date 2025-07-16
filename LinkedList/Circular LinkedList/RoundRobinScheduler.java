class Process {
    int processId;
    int burstTime;
    int priority;
    Process next;

    Process(int processId, int burstTime, int priority) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.priority = priority;
    }
}

class RoundRobinScheduler {
    Process head = null;

    void addProcess(Process p) {
        if (head == null) {
            head = p;
            head.next = head;
        } else {
            Process temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = p;
            p.next = head;
        }
    }

    void removeProcess(int processId) {
        if (head == null) return;

        Process curr = head, prev = null;
        do {
            if (curr.processId == processId) {
                if (curr == head && curr.next == head) {
                    head = null;
                } else if (curr == head) {
                    Process temp = head;
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

    void simulate(int timeQuantum) {
        if (head == null) return;

        Process curr = head;
        int time = 0;
        int totalWaitingTime = 0;
        int totalTurnAroundTime = 0;
        int count = 0;

        do {
            count++;
            curr = curr.next;
        } while (curr != head);

        curr = head;
        System.out.println("Round Robin Scheduling Simulation:");
        while (count > 0) {
            if (curr.burstTime > 0) {
                int execTime = Math.min(curr.burstTime, timeQuantum);
                System.out.println("Process " + curr.processId + " executed for " + execTime + " units");
                time += execTime;
                curr.burstTime -= execTime;
                if (curr.burstTime == 0) {
                    totalTurnAroundTime += time;
                    totalWaitingTime += time;
                    count--;
                }
            }
            curr = curr.next;
        }
        System.out.println("Average Waiting Time: " + (totalWaitingTime / 3.0));
        System.out.println("Average Turnaround Time: " + (totalTurnAroundTime / 3.0));
    }

    void displayProcesses() {
        if (head == null) return;
        Process temp = head;
        do {
            System.out.println("Process ID: " + temp.processId + ", Burst Time: " + temp.burstTime + ", Priority: " + temp.priority);
            temp = temp.next;
        } while (temp != head);
    }
}

class RoundRobinSimulation {
    public static void main(String[] args) {
        RoundRobinScheduler scheduler = new RoundRobinScheduler();
        scheduler.addProcess(new Process(1, 10, 1));
        scheduler.addProcess(new Process(2, 5, 2));
        scheduler.addProcess(new Process(3, 8, 3));

        System.out.println("Initial Process List:");
        scheduler.displayProcesses();

        System.out.println("\nSimulating with Time Quantum = 4:");
        scheduler.simulate(4);
    }
}
