
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
import java.util.Random;

public class GrimrockCastle {
// Global variables and objects go here
    private static JFrame currentFrame = null;
    static Scanner scanner = new Scanner(System.in);
    static int delaySec = 32;
    static int pauseGame = 3000;

// Main method that has minor control logic, some text to small to keep in a file, and calls to the various game functions
    public static void main(String[] args) {
    // Main variables and arrays go here
        String imageName, textName, weapon;
        int classType = 0, charHealth = 0, npcHealth = 1, choice1 = 0, choice2 = 0, npcType, charDMG = 0, npcDMG = 0, potNum=3;
        String[] classArr = { "Knight", "Wizard", "Thief" };
        String[] weaponArr = { "[GREATSWORD] ", "[ARCANE_STAFF] ", "[THROWING_KNIFE] " };

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
        gameDelay();
        textName = "loadScreen.txt";
        scrollScreen(textName);
        clearConsole();

    // Ask user to enter choice for their class
        delayedPrint(
                "\nWe will now choose a class for you to play as! \nPlease select one of the following classes by typing the class name and hitting enter:");
        textName = "classSelect.txt";
        titlePrint(textName);
        classType = classType();
        switch (classType) {
            case 0:
                charHealth = 150;
                break;
            case 1:
                charHealth = 125;
                break;
            case 2:
                charHealth = 100;
                break;
        }
        imageName = classPic(classType);
        displayImage(imageName);
        delayedPrint("You have chosen: " + classArr[classType]);
        weapon = weaponArr[classType];
        gameDelay();
        textName = "loadScreen.txt";
        scrollScreen(textName);
        clearConsole();


        
    // Begin first 'level', exposition and image loading
        //Everything from now on is based on if you live or die
        while (charHealth > 0 && choice2<2) {
            delayedPrint("\n            Welcome to ...");
            textName = "gateTitle.txt";
            titlePrint(textName);
            textName = "gateText.txt";
            fileDelayPrint(textName);
            while (choice1 < 3) {
                choice1 = gateChoice(classType);
            }
            if (choice1 == 3) {
                charHealth = wallChoice1(classType, charHealth);
                delayedPrint(
                        "...\nAfter your brief game of stone wall Jenga, you brush stone dust off of your shoulder, and move onward to the Castle proper...");
            } else if (choice1 == 4) {
                delayedPrint(
                        "...\nAs the dust settles from the collapsing door, you gently hop over the debris and move onward to the Castle proper...");
            }
            gameDelay();
            textName = "loadScreen.txt";
            scrollScreen(textName);
            clearConsole();

        // Begin 2nd level
            //Text load for level
            delayedPrint("                                  You are now entering...");
            textName = "insideTitle.txt";
            titlePrint(textName);
            textName = "insideText.txt";
            fileDelayPrint(textName);

            //Loop logic for pre combat choices
            while (choice2 == 0) {
                choice2 = battle1(classType);
            }
            
            //Begin combat phase
            gameDelay();
            textName="loadScreen.txt";
            scrollScreen(textName);
            clearConsole();
            textName="fightTitle.txt";
            scrollScreen(textName);

            //load npc data for use in control logic
            npcType = 1;
            npcHealth = 50;

            //combat loop logic takes place based on player action
            while (charHealth > 0 && npcHealth > 0 &&choice2==1) {
                charDMG = combat1(classType, charHealth, npcHealth, weapon, potNum);
                if(charDMG<0){
                    potNum--;
                }
                npcHealth = npcHealth - charDMG;
                npcDMG = npCombat(npcType);
                charHealth = charHealth - npcDMG;
        }
            //End of combat results
            if (npcHealth<=0){
            delayedPrint("You vanquish the boney undead guard! The skeleton crumbles to the ground and turns to ash! You freely approach the open door, heading further inside to the throne room. Who knows what will await you there...");    
            }
            else if (charHealth<=0){
                delayedPrint("You have fallen to your undead adversary... Your corpse dragged to the Inner Sanctum to be added to the army of undead... YOU LOSE");
            }
                delayedPrint("\nTO BE CONTINUED");
                break;
        }

    }

// Stores the entered name for later use
    public static String getName() {
        String name = "";
        // Input check for the enter key to start the game
        name = scanner.nextLine();
        return name;
    }

// Function that determines the internal variable for tracking your class, used in other functions
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

// The displayImage function will open and display the pixel art background images for each level
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

// Iterates through every line then prints out a line every .25 seconds simulating older OS loading
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

//Creates scrolling effect when printing lines of .txt files for title loading
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

//Delays actions in console to keep things uniform and neat
    public static void gameDelay() {
        try {
            Thread.sleep(pauseGame); // 10000 milliseconds = 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void combatDelay() {
        try {
            Thread.sleep(3000); // 3000 milliseconds = 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


//Switch logic for first obstacle, some choices have class dependent results and flavor text i.e. item in inventory
    public static int gateChoice(int classType) {
        char yn;
        String YN;
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                delayedPrint("You kick the door, but the only outcome is a stubbed toe... Ouch!");
                break;
            case 2:
                delayedPrint("The door is locked tight, and there is nowhere to go!");
                break;
            case 3:
                delayedPrint(
                        "Upon closer inspection, there are loose bricks around one of the hinges...\nWould you like to try to push the bricks apart? (Y/N):");
                YN = scanner.next();
                YN = YN.toUpperCase();
                yn = YN.charAt(0);
                if (yn == 'Y') {
                    break;
                } else {
                    delayedPrint("You back away from the ruined wall to gather your thoughts...");
                    choice = 0;
                }
                break;
            case 4:
                delayedPrint("You open your pack and look inside...\nYou found a...");
                switch (classType) {
                    case 0:
                        delayedPrint("[GREATAXE]");
                        break;
                    case 1:
                        delayedPrint("[FIREBALL SCROLL]");
                        break;
                    case 2:
                        delayedPrint("[CHERRY BOMB]");
                        break;
                }
                delayedPrint("Would you like to use it?(Y/N)");
                YN = scanner.next();
                YN = YN.toUpperCase();
                yn = YN.charAt(0);
                if (yn == 'Y') {
                    break;
                } else {
                    delayedPrint("You quickly repack your bag to ponder another solution...");
                    choice = 0;
                }
                break;
                default:
                delayedPrint("Invalid choice! Please try again");
                choice = 0;
        }

        return choice;
    }

//Result for getting by first obstacle by inspecting, outcomes are class depenedent
    public static int wallChoice1(int classType, int charHealth) {
        delayedPrint("You push against the decaying stonework with all you might and..!\n");
        if (classType != 0) {
            delayedPrint(
                    "You slam your body into the wall, but you are no knight... The bricks crumble enough to pass through...\n but you take 10 damage from the impact!");
            charHealth = charHealth - 10;
        } else {
            delayedPrint(
                    "With a mighty heave you crash into the wall! Your armor protects you from the falling debris as the old keep tumbles to pieces around you...");
        }
        return charHealth;
    }

//Switch statement for actions before first fight, some options do nothing, some result in combat start
    public static int battle1(int classType) {
        int result = 0;
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                delayedPrint(
                        "\nYou take up arms with a fire in your eyes... This skeleton will meet its end at your hand! Prepare for battle!");
                result = 1;
                break;
            case 2:
                if (classType == 2) {
                    delayedPrint(
                            "\nYou quietly step into the shadows at the edge of the room, as you silently step across the hall...\nYou gracefully duck between cover, and slink through the open door, gently closing it behind you as the skeleton chatters, ignorant to your presence...");
                    result = 2;
                    break;
                } else {
                    delayedPrint(
                            "\nYou take a few steps forward, the sound of your footsteps echoing through the chamber... The skeleton quickly faces towards you, clacks its jaw, and runs to engage! Prepare for battle!");
                    result = 1;
                }
                break;
            case 3:
                delayedPrint(
                        "\nYou look around to find a path to escape... but the only doors around consist of the main entrance, and where the skeleton emerged...");
                result = 0;
                break;
            case 4:
                delayedPrint(
                        "\nYou rummage through your pack... but deem nothing in there very useful for this situation...");
                result = 0;
                break;
                default:
                delayedPrint("Invalid choice! Please try again");
                result = 0;
        }

        return result;
    }

//Clears console for nicer printing
    public static void clearConsole() {
        try {
            // Clearing console using ANSI escape codes (works on UNIX-based systems)
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Handle exceptions if any
            e.printStackTrace();
        }
    }

//Switch logic for combat, various choices, healing, attack, check health, etc
    public static int combat1(int classType, int charHealth, int npcHealth, String weapon, int potNum) {
        int dmg = 0, combatLoop=1;
        char yn;
        String YN;
        while(combatLoop==1){
        delayedPrint("\nThe skeleton shuffles on its bony feet, ready to strike!\n");
        fileDelayPrint("choicesText.txt");
        int choice = scanner.nextInt();
        
        switch (choice) {
            case 1:
                dmg = playerAttack(classType);
                delayedPrint("You attack the skeleton with your " + weapon + "dealing " + dmg + " damage!\n");
                combatLoop=0;
                break;
            case 2:
                delayedPrint("You try to move passed the skeleton... but it thrusts its sword blocking the way!\n");
                combatLoop=0;
                combatDelay();
                break;
            case 3:
                delayedPrint("\nCurrent Player Health: " + charHealth +'\n');
                combatDelay();
                combatLoop = 1;
                break;
            case 4:
                delayedPrint("You look into your pack and find... " + potNum
                        + " Potions of Health!\nWould you like to use one?(Y/N)");
                YN = scanner.next();
                YN = YN.toUpperCase();
                yn = YN.charAt(0);
                if (yn == 'Y') {
                    dmg = potRoll(potNum);
                    delayedPrint("You drink the potion and heal for ["+dmg+"] health!");
                    dmg=dmg*-1;
                    combatLoop=0;
                    combatDelay();
                } else {
                    delayedPrint("You quickly close your pack to resume combat...");
                    combatLoop=1;
                    combatDelay();
                }
                
                break;
                default:
                delayedPrint("Invalid choice! Try again");
                combatLoop=1;
        }
    }
        gameDelay();
        return dmg;
    }


//Random num gen for player damage based on class type
    public static int playerAttack(int classType) {
        int attack = 0;
        Random rand = new Random();
        switch (classType) {
            case 0:
                attack = rand.nextInt(8) + 5;
                break;
            case 1:
                attack = rand.nextInt(14) + 6;
                break;
            case 2:
                attack = rand.nextInt(10) + 4;
                break;
        }

        return attack;
    }

//NPC damage gen based on enemy type
    public static int npCombat(int npcType) {
       
        int attack = 0;
        Random rand = new Random();
        if (npcType == 1) {
            attack = rand.nextInt(8) + 1;
        } else {
            attack = rand.nextInt(10) + 1;
        }
        delayedPrint("The skeleton clacks its jaw in rage! It attacks you for " + attack + " damage!");
        combatDelay();
        return attack;
    }

//Healing random gen for health potions
    public static int potRoll(int potNum){
        int heal = 0;
        Random rand = new Random();
        if(potNum>0){
        heal = rand.nextInt(8)+2;
        }
        else{
            delayedPrint("You are out of healing potions!");
            heal = 0;
        }
        return heal;
    }
}
