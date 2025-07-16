class Item {
    String itemName;
    int itemId;
    int quantity;
    double price;
    Item next;

    Item(String itemName, int itemId, int quantity, double price) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
    }
}

class Inventory {
    Item head;

    void addItem(Item item, int position) {
        if (position == 0 || head == null) {
            item.next = head;
            head = item;
        } else {
            Item temp = head;
            int count = 0;
            while (temp.next != null && count < position - 1) {
                temp = temp.next;
                count++;
            }
            item.next = temp.next;
            temp.next = item;
        }
    }

    void removeItem(int itemId) {
        if (head == null) return;
        if (head.itemId == itemId) {
            head = head.next;
            return;
        }
        Item temp = head;
        while (temp.next != null) {
            if (temp.next.itemId == itemId) {
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
    }

    void updateQuantity(int itemId, int newQty) {
        Item temp = head;
        while (temp != null) {
            if (temp.itemId == itemId) {
                temp.quantity = newQty;
                return;
            }
            temp = temp.next;
        }
    }

    void searchItem(String query) {
        Item temp = head;
        boolean found = false;
        while (temp != null) {
            if (String.valueOf(temp.itemId).equals(query) || temp.itemName.equalsIgnoreCase(query)) {
                System.out.println("Item: " + temp.itemName + ", ID: " + temp.itemId + ", Qty: " + temp.quantity + ", Price: " + temp.price);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("Item not found: " + query);
    }

    double calculateTotalValue() {
        double total = 0;
        Item temp = head;
        while (temp != null) {
            total += temp.quantity * temp.price;
            temp = temp.next;
        }
        return total;
    }

    void sortByName() {
        if (head == null || head.next == null) return;
        head = mergeSort(head, true);
    }

    void sortByPriceDesc() {
        if (head == null || head.next == null) return;
        head = mergeSort(head, false);
    }

    Item mergeSort(Item head, boolean byName) {
        if (head == null || head.next == null) return head;

        Item middle = getMiddle(head);
        Item nextOfMiddle = middle.next;
        middle.next = null;

        Item left = mergeSort(head, byName);
        Item right = mergeSort(nextOfMiddle, byName);

        return sortedMerge(left, right, byName);
    }

    Item sortedMerge(Item a, Item b, boolean byName) {
        if (a == null) return b;
        if (b == null) return a;

        Item result;
        if (byName) {
            if (a.itemName.compareToIgnoreCase(b.itemName) <= 0) {
                result = a;
                result.next = sortedMerge(a.next, b, byName);
            } else {
                result = b;
                result.next = sortedMerge(a, b.next, byName);
            }
        } else {
            if (a.price >= b.price) {
                result = a;
                result.next = sortedMerge(a.next, b, byName);
            } else {
                result = b;
                result.next = sortedMerge(a, b.next, byName);
            }
        }
        return result;
    }

    Item getMiddle(Item head) {
        if (head == null) return head;
        Item slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    void displayItems() {
        Item temp = head;
        while (temp != null) {
            System.out.println(temp.itemId + ": " + temp.itemName + " | Qty: " + temp.quantity + " | Price: " + temp.price);
            temp = temp.next;
        }
    }
}

class InventoryApp {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        inventory.addItem(new Item("Pen", 101, 50, 5.0), 0);
        inventory.addItem(new Item("Notebook", 102, 30, 25.0), 1);
        inventory.addItem(new Item("Marker", 103, 20, 10.0), 2);

        System.out.println("All Inventory Items:");
        inventory.displayItems();

        System.out.println("\nSearch for 'Notebook':");
        inventory.searchItem("Notebook");

        System.out.println("\nUpdate Quantity of ID 101:");
        inventory.updateQuantity(101, 100);
        inventory.displayItems();

        System.out.println("\nTotal Inventory Value: Rs." + inventory.calculateTotalValue());

        System.out.println("\nSort by Name:");
        inventory.sortByName();
        inventory.displayItems();

        System.out.println("\nSort by Price Descending:");
        inventory.sortByPriceDesc();
        inventory.displayItems();
    }
}