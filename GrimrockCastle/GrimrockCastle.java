
//Library imports go here
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GrimrockCastle {
    // Global variables and objects go here
    private static JFrame currentFrame = null;
    static Scanner scanner = new Scanner(System.in);
    static int delaySec = 32;
    static int pauseGame = 3000;

    // Main method that has minor control logic, some text to small to keep in a
    // file, and calls to the various game functions
    public static void main(String[] args) {
        // Main variables and arrays go here
        String imageName, textName;
        int classType, choice1;
        String[] classArr = { "Knight", "Wizard", "Thief" };

        // Begin main method
        // Display the intro text and title
        textName = "titletext.txt";
        delayedPrint("                Welcome to ...");
        titlePrint(textName);

        // Asks for user to input name
        textName = "nametext.txt";
        fileDelayPrint(textName);
        String userName = getName();
        delayedPrint("Hello " + userName + "!");
        textName = "loadScreen.txt";
        scrollScreen(textName);

        // Ask user to enter choice for their class
        delayedPrint(
                "\nWe will now choose a class for you to play as! \nPlease select one of the following classes by typing the class name and hitting enter:");
        textName = "classSelect.txt";
        titlePrint(textName);
        classType = classType();
        imageName = classPic(classType);
        displayImage(imageName);
        delayedPrint("You have chosen: " + classArr[classType]);
        gameDelay();
        textName = "loadScreen.txt";
        scrollScreen(textName);

        // Begin first 'level', exposition and image loading
        delayedPrint("\n                    Welcome to ...");
        textName = "gateTitle.txt";
        titlePrint(textName);
        textName = "gateText.txt";
        fileDelayPrint(textName);
        choice1 = gateChoice();
    }

    // Stores the entered name for later use
    public static String getName() {
        String name = "";
        // Input check for the enter key to start the game
        name = scanner.nextLine();
        return name;
    }

    // Function that determines the internal variable for tracking your class, used
    // in other functions
    public static int classType() {
        int classInt = 0;

        while (true) {
            String input = scanner.nextLine();
            String classIn;
            classIn = input.toLowerCase();
            switch (classIn) {
                case "knight":
                    classInt = 0;
                    break;
                case "wizard":
                    classInt = 1;
                    break;
                case "thief":
                    classInt = 2;
                    break;
                default:
                    delayedPrint("\nInvalid class choice! Please try again.");
                    continue;
            }
            return classInt;
        }

    }

    // The displayImage function will open and display the pixel art background
    // images for each level
    public static void displayImage(String imageName) {
        // Close the previous JFrame if it exists
        if (currentFrame != null) {
            currentFrame.dispose();
        }

        // Load the image
        ImageIcon imageIcon = new ImageIcon(imageName);

        // Create a JFrame to display the image
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the JFrame to fit the image
        frame.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());

        // Create a JLabel to hold the image
        JLabel label = new JLabel(imageIcon);

        // Add the JLabel to the JFrame
        frame.getContentPane().add(label);

        // Make the JFrame visible
        frame.setVisible(true);

        // Bring the JFrame to the front
        frame.toFront();

        // Ensure the JFrame is always on top
        frame.setAlwaysOnTop(true);

        // Activate the application window
        java.awt.Toolkit.getDefaultToolkit().beep();

        // Schedule a task to close the JFrame after 2.5 seconds
        Timer timer = new Timer(2500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the JFrame
            }
        });
        timer.setRepeats(false); // Make sure the timer only runs once
        timer.start();

        // Update the currentFrame variable
        currentFrame = frame;
    }

    // Iterates through every line then prints out a line every .25 seconds
    // simulating older OS loading
    public static void titlePrint(String textName) {
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

    // A print delay that creates the scrolling text effect on print lines in the main
    public static void delayedPrint(String message) {
        for (char c : message.toCharArray()) {
            System.out.print(c);
            try {
                TimeUnit.MILLISECONDS.sleep(delaySec);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    // Logic to determine what class you chose, and what image to display
    public static String classPic(int classType) {
        String filename = " ";
        switch (classType) {
            case 0:
                filename = "knight_pixel.png";
                break;
            case 1:
                filename = "wizard_pixel.png";
                break;
            case 2:
                filename = "thief_pixel.png";
                break;
        }

        return filename;
    }

    // Creates the scrolling text effect for .txt files with large amounts of text
    public static void fileDelayPrint(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    System.out.print(c);
                    try {
                        Thread.sleep(delaySec); // Adding delay between characters
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

    public static void gameDelay() {
        try {
            Thread.sleep(pauseGame); // 10000 milliseconds = 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int gateChoice() {
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                delayedPrint("You kick the door, but the only outcome is a stubbed toe... Ouch!");
                break;
            case 2:
                delayedPrint("The door is locked tight, and there is nowhere to go!");
                break;
            case 3:
                delayedPrint("Upon closer inspection, there are loose bricks around one of the hinges...");
                break;
            case 4:
                delayedPrint("You open your pack and look inside...");
                break;
        }

        return choice;
    }
}
