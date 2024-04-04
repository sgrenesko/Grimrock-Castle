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
    private static JFrame currentFrame = null;
    static Scanner scanner = new Scanner(System.in);
    static int delaySec=32;
    public static void main(String[] args) {
        String imageName, textName;
        int classType;
        String[] classArr = { "Knight", "Wizard", "Thief" };

        textName = "introtext.txt";
        delayedPrint("                Welcome to ...",50);
        textPrint(textName);
        delayedPrint("A text-based short RPG created by Stephen R Grenesko\nIn this game you will be exploring the ancient Castle Grimrock, wherin you will find monsters, puzzles, and more!\nTo begin, please type your name and press enter!",delaySec);
        String userName = getName();
        delayedPrint("Hello " + userName + "!",delaySec);
        delayedPrint("\nWe will now choose a class for you to play as! \nPlease select one of the following classes by typing the class name and hitting enter:",delaySec);
        textName = "classSelect.txt";
        textPrint(textName);
        classType = classType();
        imageName=classPic(classType);
        displayImage(imageName);
        delayedPrint("You have chosen: " + classArr[classType],delaySec);

        /*
         * imageName = "knight_pixel.png";
         * displayImage(imageName);
         * try {
         * Thread.sleep(2500);
         * } catch (InterruptedException e) {
         * e.printStackTrace();
         * }
         * imageName = "druid_sprite_ref.png"; // Assuming this is the correct image
         * name
         * displayImage(imageName);
         */
    }

    public static String getName() {
        String name = "";
        // Input check for the enter key to start the game
        name = scanner.nextLine();
        return name;
    }

    public static int classType() {
        int classInt = 0;
        while (true) {
        String input = scanner.nextLine();
        String classIn;
            classIn=input.toLowerCase();
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
                    delayedPrint("\nInvalid class choice! Please try again.",delaySec);
                    continue;
            }
        // break;
            return classInt;
        }
        
    }

    // The displayImage function will open and display the pixel art background
    // images for each level
    // and push that display to the front
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

    public static void textPrint(String textName) {
        try (BufferedReader br = new BufferedReader(new FileReader(textName))) {
            String line;

            // Iterates through every line then prints out a line every .25 seconds
            // simulating older OS loading
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void delayedPrint(String message, long delayInMillis) {
        for (char c : message.toCharArray()) {
            System.out.print(c);
            try {
                TimeUnit.MILLISECONDS.sleep(delayInMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    public static String classPic(int classType){
        String filename=" ";
        switch(classType){
            case 0:
            filename="knight_pixel.png";
        }

        return filename;
    }
}
