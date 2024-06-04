import java.util.Scanner;

public class firstEvent {
    public static int gateChoice(int classType) {
        Scanner scanner = GrimrockCastle.scanner; // Reuse the scanner from the main class
        int choice = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println("Enter your choice: ");
                if (scanner.hasNextInt()) { // Check if the next input is an integer
                    choice = scanner.nextInt();
                    validInput = true; // Assuming valid integer input
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
                                validInput = false; // Prompt again for main choice
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
                                validInput = false; // Prompt again for main choice
                            }
                            break;
                        default:
                            delayDisplay.delayedPrint("Invalid choice! Please try again");
                            validInput = false; // Prompt again for main choice
                            break;
                    }
                } else {
                    delayDisplay.delayedPrint("Invalid input! Please enter a valid integer.");
                    scanner.next(); // Clear the invalid input
                    validInput = false; // Prompt again for main choice
                }
            } catch (java.util.InputMismatchException e) {
                delayDisplay.delayedPrint("Invalid input! Please enter a valid integer.");
                scanner.next(); // Clear the invalid input
                validInput = false; // Prompt again for main choice
            }
        }

        return choice;
    }
}
