
// Q4_FileReadComparison.java
// Compare FileReader vs InputStreamReader for large file reading

import java.io.*;

public class Q4_FileReadComparison {
    public static void readWithFileReader(String path) throws IOException {
        try (FileReader fr = new FileReader(path)) {
            while (fr.read() != -1) {}
        }
    }

    public static void readWithInputStreamReader(String path) throws IOException {
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(path))) {
            while (isr.read() != -1) {}
        }
    }
}
