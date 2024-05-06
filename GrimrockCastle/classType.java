import java.util.Scanner;
public class classType {
    public int classNum() {
        Scanner scanner = new Scanner(System.in);
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
                    delayDisplay.delayedPrint("\nInvalid class choice! Please try again.");
                    continue;
            }
            scanner.close();
            return classInt;
        }
        

    }
    public String classImg(int classType) {
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
    
}
