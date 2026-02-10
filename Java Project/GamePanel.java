import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel
        implements ActionListener, KeyListener, Movable {

    static final int SIZE = 25;
    static final int WIDTH = 600;
    static final int HEIGHT = 600;

    ArrayList<Point> snake = new ArrayList<>();
    ArrayList<Point> apples = new ArrayList<>();
    Random rand = new Random();

    char direction = 'R';
    Timer timer = new Timer(120, this);

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(this);

        snake.add(new Point(300, 300));
        spawnApple();

        timer.start();
    }

    void spawnApple() {
        apples.add(new Point(
                rand.nextInt(WIDTH / SIZE) * SIZE,
                rand.nextInt(HEIGHT / SIZE) * SIZE
        ));
    }

    @Override
    public void move() {
        Point head = snake.get(0);
        Point newHead = new Point(head);

        switch (direction) {
            case 'U': newHead.y -= SIZE; break;
            case 'D': newHead.y += SIZE; break;
            case 'L': newHead.x -= SIZE; break;
            case 'R': newHead.x += SIZE; break;
        }

        snake.add(0, newHead);

        boolean ateApple = false;

        for (Point a : apples) {
            if (newHead.equals(a)) {
                ateApple = true;
                spawnApple();
            }
        }

        if (!ateApple) {
            snake.remove(snake.size() - 1);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.red);
        for (Point a : apples)
            g.fillOval(a.x, a.y, SIZE, SIZE);

        g.setColor(Color.green);
        for (Point p : snake)
            g.fillRect(p.x, p.y, SIZE, SIZE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) direction = 'U';
        if (e.getKeyCode() == KeyEvent.VK_DOWN) direction = 'D';
        if (e.getKeyCode() == KeyEvent.VK_LEFT) direction = 'L';
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) direction = 'R';
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}