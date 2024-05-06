import java.util.Scanner;

public class firstEvent {
    public static int gateChoice(int classType) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        try {
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    delayDisplay.delayedPrint("You kick the door, but the only outcome is a stubbed toe... Ouch!");
                    break;
                case 2:
                    delayDisplay.delayedPrint("The door is locked tight, and there is nowhere to go!");
                    break;
                case 3:
                    delayDisplay.delayedPrint("Upon closer inspection, there are loose bricks around one of the hinges...\nWould you like to try to push the bricks apart? (Y/N):");
                    char yn = Character.toUpperCase(scanner.next().charAt(0));
                    if (yn == 'Y') {
                        break;
                    } else {
                        delayDisplay.delayedPrint("You back away from the ruined wall to gather your thoughts...");
                        choice = 0;
                    }
                    break;
                case 4:
                    delayDisplay.delayedPrint("You open your pack and look inside...\nYou found a...");
                    switch (classType) {
                        case 0:
                            delayDisplay.delayedPrint("[GREATAXE]");
                            break;
                        case 1:
                            delayDisplay.delayedPrint("[FIREBALL SCROLL]");
                            break;
                        case 2:
                            delayDisplay.delayedPrint("[CHERRY BOMB]");
                            break;
                    }
                    delayDisplay.delayedPrint("Would you like to use it? (Y/N)");
                    yn = Character.toUpperCase(scanner.next().charAt(0));
                    if (yn == 'Y') {
                        break;
                    } else {
                        delayDisplay.delayedPrint("You quickly repack your bag to ponder another solution...");
                        choice = 0;
                    }
                    break;
                default:
                    delayDisplay.delayedPrint("Invalid choice! Please try again");
                    break;
            }
        } catch (java.util.InputMismatchException e) {
            delayDisplay.delayedPrint("Invalid input! Please enter a valid integer.");
        } catch (java.util.NoSuchElementException e) {
            delayDisplay.delayedPrint("No input found! Please provide an input.");
        } finally {
            scanner.close();
        }
        return choice;
    }
}
