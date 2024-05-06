import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class delayDisplay {
    public static void delayedPrint(String message) {
        for (char c : message.toCharArray()) {
            System.out.print(c);
            try {
                TimeUnit.MILLISECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    public static void fileDelayPrint(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    System.out.print(c);
                    try {
                        Thread.sleep(30); // Adding delay between characters
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(); // Move to the next line after printing each line
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public static void scrollScreen(String textName) {
            try (BufferedReader br = new BufferedReader(new FileReader(textName))) {
                String line;
    
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public static void titleDisplay(String textName) {
            try (BufferedReader br = new BufferedReader(new FileReader(textName))) {
                String line;
    
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

