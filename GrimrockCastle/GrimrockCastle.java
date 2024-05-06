
//Library imports go here
import java.util.Scanner;
import java.util.Random;

public class GrimrockCastle {
// Global variables and objects go here
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
        @SuppressWarnings("unused")
        delayDisplay titleDisplay = new delayDisplay();
        classType userClass = new classType();

// Begin main method

    // Display the intro text and title
        textName = "titletext.txt";
        delayDisplay.delayedPrint("                Welcome to ...");
        delayDisplay.titleDisplay(textName);

    // Asks for user to input name
        textName = "nametext.txt";
        delayDisplay.fileDelayPrint(textName);
        String userName = getName();
        delayDisplay.delayedPrint("Hello " + userName + "!");
        gameDelay();
        textName = "loadScreen.txt";
        delayDisplay.scrollScreen(textName);
        clearConsole();

    // Ask user to enter choice for their class
        delayDisplay.delayedPrint(
                "\nWe will now choose a class for you to play as! \nPlease select one of the following classes by typing the class name and hitting enter:");
        textName = "classSelect.txt";
        delayDisplay.titleDisplay(textName);
        classType = userClass.classNum();
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
        imageName = userClass.classImg(classType);
        imgDisp.displayImage(imageName);
        delayDisplay.delayedPrint("You have chosen: " + classArr[classType]);
        weapon = weaponArr[classType];
        gameDelay();
        textName = "loadScreen.txt";
        delayDisplay.scrollScreen(textName);
        clearConsole();


        
    // Begin first 'level', exposition and image loading
        //Everything from now on is based on if you live or die
        while (charHealth > 0 && choice2<2) {
            delayDisplay.delayedPrint("\n                    Welcome to ...");
            textName = "gateTitle.txt";
            delayDisplay.titleDisplay(textName);
            textName = "gateText.txt";
            delayDisplay.fileDelayPrint(textName);
            while (choice1 < 3) {
                choice1 = firstEvent.gateChoice(classType);
            }
            if (choice1 == 3) {
                charHealth = wallChoice1(classType, charHealth);
                delayDisplay.delayedPrint(
                        "...\nAfter your brief game of stone wall Jenga, you brush stone dust off of your shoulder, and move onward to the Castle proper...");
            } else if (choice1 == 4) {
                delayDisplay.delayedPrint(
                        "...\nAs the dust settles from the collapsing door, you gently hop over the debris and move onward to the Castle proper...");
            }
            gameDelay();
            textName = "loadScreen.txt";
            delayDisplay.scrollScreen(textName);
            clearConsole();

        // Begin 2nd level
            //Text load for level
            delayDisplay.delayedPrint("                                  You are now entering...");
            textName = "insideTitle.txt";
            delayDisplay.titleDisplay(textName);
            textName = "insideText.txt";
            delayDisplay.fileDelayPrint(textName);

            //Loop logic for pre combat choices
            while (choice2 == 0) {
                choice2 = battle1(classType);
            }
            
            //Begin combat phase
            gameDelay();
            textName="loadScreen.txt";
            delayDisplay.scrollScreen(textName);
            clearConsole();
            if(choice2==1){
                textName="fightTitle.txt";
                delayDisplay.scrollScreen(textName);
            }

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
            delayDisplay.delayedPrint("You vanquish the boney undead guard! The skeleton crumbles to the ground and turns to ash! You freely approach the open door, heading further inside to the throne room. Who knows what will await you there...");    
            }
            else if (charHealth<=0){
                delayDisplay.delayedPrint("You have fallen to your undead adversary... Your corpse dragged to the Inner Sanctum to be added to the army of undead... YOU LOSE");
            }
                delayDisplay.delayedPrint("\nTO BE CONTINUED");
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
            Thread.sleep(1500); // 1500 milliseconds = 1.5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


//Switch logic for first obstacle, some choices have class dependent results and flavor text i.e. item in inventory

//Result for getting by first obstacle by inspecting, outcomes are class depenedent
    public static int wallChoice1(int classType, int charHealth) {
        delayDisplay.delayedPrint("You push against the decaying stonework with all you might and..!\n");
        if (classType != 0) {
            delayDisplay.delayedPrint(
                    "Your slam your body into the wall, but you are no knight... The bricks crumble enough to pass through...\n but you take 10 damage from the impact!");
            charHealth = charHealth - 10;
        } else {
            delayDisplay.delayedPrint(
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
                delayDisplay.delayedPrint(
                        "\nYou take up arms with a fire in your eyes... This skeleton will meet its end at your hand! Prepare for battle!");
                result = 1;
                break;
            case 2:
                if (classType == 2) {
                    delayDisplay.delayedPrint(
                            "\nYou quietly step into the shadows at the edge of the room, as you silently step across the hall...\nYou gracefully duck between cover, and slink through the open door, gently closing it behind you as the skeleton chatters, ignorant to your presence...");
                    result = 2;
                    break;
                } else {
                    delayDisplay.delayedPrint(
                            "\nYou take a few steps forward, the sound of your footsteps echoing through the chamber... The skeleton quickly faces towards you, clacks its jaw, and runs to engage! Prepare for battle!");
                    result = 1;
                }
                break;
            case 3:
                delayDisplay.delayedPrint(
                        "\nYou look around to find a path to escape... but the only doors around consist of the main entrance, and where the skeleton emerged...");
                result = 0;
                break;
            case 4:
                delayDisplay.delayedPrint(
                        "\nYou rummage through your pack... but deem nothing in there very useful for this situation...");
                result = 0;
                break;
                default:
                delayDisplay.delayedPrint("Invalid choice! Please try again");
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
        delayDisplay.delayedPrint("\nThe skeleton shuffles on its bony feet, ready to strike!\n");
        delayDisplay.fileDelayPrint("choicesText.txt");
        int choice = scanner.nextInt();
        
        switch (choice) {
            case 1:
                dmg = playerAttack(classType);
                delayDisplay.delayedPrint("You attack the skeleton with your " + weapon + "dealing " + dmg + " damage!\n");
                combatLoop=0;
                break;
            case 2:
                delayDisplay.delayedPrint("You try to move passed the skeleton... but it thrusts its sword blocking the way!\n");
                combatLoop=0;
                combatDelay();
                break;
            case 3:
                delayDisplay.delayedPrint("\nCurrent Player Health: " + charHealth +'\n');
                combatDelay();
                combatLoop = 1;
                break;
            case 4:
                delayDisplay.delayedPrint("You look into your pack and find... " + potNum
                        + " Potions of Health!\nWould you like to use one?(Y/N)");
                YN = scanner.next();
                YN = YN.toUpperCase();
                yn = YN.charAt(0);
                if (yn == 'Y') {
                    dmg = potRoll(potNum);
                    delayDisplay.delayedPrint("You drink the potion and heal for ["+dmg+"] health!");
                    dmg=dmg*-1;
                    combatLoop=0;
                    combatDelay();
                } else {
                    delayDisplay.delayedPrint("You quickly close your pack to resume combat...");
                    combatLoop=1;
                    combatDelay();
                }
                
                break;
                default:
                delayDisplay.delayedPrint("Invalid choice! Try again");
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
        delayDisplay.delayedPrint("The skeleton clacks its jaw in rage! It attacks you for " + attack + " damage!");
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
            delayDisplay.delayedPrint("You are out of healing potions!");
            heal = 0;
        }
        return heal;
    }
}
