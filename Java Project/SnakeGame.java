import javax.swing.JFrame;

public class SnakeGame {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Movement Test");

        GamePanel panel = new GamePanel();

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}