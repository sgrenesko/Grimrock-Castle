public class timeDelays {
    public static void gameDelay() {
        try {
            Thread.sleep(3000);// 3000 milliseconds = 3 secondss
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

}
