import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.util.*;

@SuppressWarnings("unused")
public class imgDisp {
    private static JFrame currentFrame = null;
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
}
