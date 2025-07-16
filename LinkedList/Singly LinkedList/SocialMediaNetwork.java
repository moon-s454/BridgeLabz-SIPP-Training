import java.util.*;

class User {
    int userId;
    String name;
    int age;
    List<Integer> friendIds;
    User next;

    User(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friendIds = new ArrayList<>();
    }
}

class SocialNetwork {
    User head;

    void addUser(User user) {
        user.next = head;
        head = user;
    }

    User findUser(int userId) {
        User temp = head;
        while (temp != null) {
            if (temp.userId == userId) return temp;
            temp = temp.next;
        }
        return null;
    }

    void addFriend(int userId1, int userId2) {
        User user1 = findUser(userId1);
        User user2 = findUser(userId2);
        if (user1 != null && user2 != null && userId1 != userId2) {
            if (!user1.friendIds.contains(userId2)) user1.friendIds.add(userId2);
            if (!user2.friendIds.contains(userId1)) user2.friendIds.add(userId1);
        }
    }

    void removeFriend(int userId1, int userId2) {
        User user1 = findUser(userId1);
        User user2 = findUser(userId2);
        if (user1 != null && user2 != null) {
            user1.friendIds.remove((Integer) userId2);
            user2.friendIds.remove((Integer) userId1);
        }
    }

    void displayFriends(int userId) {
        User user = findUser(userId);
        if (user != null) {
            System.out.println("Friends of " + user.name + ":");
            for (int fid : user.friendIds) {
                User friend = findUser(fid);
                if (friend != null) {
                    System.out.println(friend.userId + " - " + friend.name);
                }
            }
        }
    }

    void mutualFriends(int userId1, int userId2) {
        User user1 = findUser(userId1);
        User user2 = findUser(userId2);
        if (user1 != null && user2 != null) {
            System.out.println("Mutual Friends:");
            for (int fid : user1.friendIds) {
                if (user2.friendIds.contains(fid)) {
                    User mutual = findUser(fid);
                    if (mutual != null) {
                        System.out.println(mutual.userId + " - " + mutual.name);
                    }
                }
            }
        }
    }

    void searchUser(String query) {
        User temp = head;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(query) || String.valueOf(temp.userId).equals(query)) {
                System.out.println("User Found: " + temp.userId + " - " + temp.name);
                return;
            }
            temp = temp.next;
        }
        System.out.println("User not found: " + query);
    }

    void countFriends() {
        User temp = head;
        while (temp != null) {
            System.out.println(temp.name + " has " + temp.friendIds.size() + " friends.");
            temp = temp.next;
        }
    }
}

class SocialMediaApp {
    public static void main(String[] args) {
        SocialNetwork network = new SocialNetwork();
        network.addUser(new User(1, "Alice", 21));
        network.addUser(new User(2, "Bob", 22));
        network.addUser(new User(3, "Charlie", 23));
        network.addUser(new User(4, "Diana", 24));

        network.addFriend(1, 2);
        network.addFriend(1, 3);
        network.addFriend(2, 3);
        network.addFriend(2, 4);

        System.out.println("Display friends of Bob:");
        network.displayFriends(2);

        System.out.println("\nMutual friends of Alice and Bob:");
        network.mutualFriends(1, 2);

        System.out.println("\nSearch for user 'Charlie':");
        network.searchUser("Charlie");

        System.out.println("\nFriend count for each user:");
        network.countFriends();
    }
}
