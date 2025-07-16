class TextState {
    String content;
    TextState prev, next;

    TextState(String content) {
        this.content = content;
    }
}

class TextEditor {
    TextState current;
    int size = 0;
    final int MAX_HISTORY = 10;

    void performAction(String newContent) {
        TextState newState = new TextState(newContent);
        if (current != null) {
            newState.prev = current;
            current.next = newState;
        }
        current = newState;
        trimHistory();
    }

    void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
        } else {
            System.out.println("No more undo available.");
        }
    }

    void redo() {
        if (current != null && current.next != null) {
            current = current.next;
        } else {
            System.out.println("No more redo available.");
        }
    }

    void displayCurrentState() {
        if (current != null) {
            System.out.println("Current Text: " + current.content);
        } else {
            System.out.println("Editor is empty.");
        }
    }

    void trimHistory() {
        // Count how many states are before the current one
        int count = 0;
        TextState temp = current;
        while (temp != null) {
            count++;
            temp = temp.prev;
        }
        // If history exceeds max, trim the earliest
        if (count > MAX_HISTORY) {
            temp = current;
            while (count > MAX_HISTORY && temp.prev != null) {
                temp = temp.prev;
                count--;
            }
            temp.prev = null;
        }
    }
}

class TextEditorApp {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();

        editor.performAction("Hello");
        editor.performAction("Hello World");
        editor.performAction("Hello World!");
        editor.performAction("Hello, World!");

        editor.displayCurrentState();

        System.out.println("\nUndo:");
        editor.undo();
        editor.displayCurrentState();

        System.out.println("\nRedo:");
        editor.redo();
        editor.displayCurrentState();

        System.out.println("\nUndo twice:");
        editor.undo();
        editor.undo();
        editor.displayCurrentState();

        System.out.println("\nRedo:");
        editor.redo();
        editor.displayCurrentState();
    }
}
