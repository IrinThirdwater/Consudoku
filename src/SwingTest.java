import javax.swing.*;
import java.awt.*;

/**
 * Test for GUI prototype using Swing.
 */
public class SwingTest {

    private static int WIDTH  = 600;
    private static int HEIGHT = 500;

    private static void createAndShowGUI () {
        JFrame frame = new JFrame("Test GUI");
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Hello, world!");
        frame.getContentPane().add(label);

        frame.pack();
        frame.setLocationRelativeTo(null); // centre
        frame.setVisible(true);
    }

    public static void main (String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run () {
                createAndShowGUI();
            }
        });
    }

}
