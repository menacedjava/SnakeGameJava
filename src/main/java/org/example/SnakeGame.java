package org.example;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {
    private final int TILE_SIZE = 25;
    private final int WIDTH = 20;
    private final int HEIGHT = 20;
    private final int SCREEN_WIDTH = WIDTH * TILE_SIZE;
    private final int SCREEN_HEIGHT = HEIGHT * TILE_SIZE;

    private final int[] x = new int[WIDTH * HEIGHT];
    private final int[] y = new int[WIDTH * HEIGHT];

    private int bodyParts = 3;
    private int applesEaten;
    private int appleX;
    private int appleY;

    private char direction = 'R';
    private boolean running = false;
    private Timer timer;

    public SnakeGame() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);
        startGame();
    }

    private void startGame() {
        newApple();
        running = true;
        timer = new Timer(100, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            // Apple
            g.setColor(Color.RED);
            g.fillOval(appleX, appleY, TILE_SIZE, TILE_SIZE);
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(new Color(45, 180, 0));
                }
                g.fillRect(x[i], y[i], TILE_SIZE, TILE_SIZE);
            }
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("Score: " + applesEaten, 10, 20);
        } else {
            gameOver(g);
        }
    }

    private void newApple() {
        Random random = new Random();
        appleX = random.nextInt(WIDTH) * TILE_SIZE;
        appleY = random.nextInt(HEIGHT) * TILE_SIZE;
    }
    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

//        switch (direction) {
//            case 'U':
//                y[0] -= TILE_SIZE;
//                break;
//            case 'D':
//                y[0] += TILE_SIZE;
//                break;
//            case 'L':
//                x[0] -= TILE_SIZE;
//                break;
//            case 'R':
//                x[0] += TILE_SIZE;
//                break;
//        }
    }
//    public void checkApple() {
//        if (x[0] == appleX && y[0] == appleY) {
//            bodyParts++;
//            applesEaten++;
//            newApple();
//        }
//    }
//    public void checkCollisions() {
//        // Snake collides with itself
//        for (int i = bodyParts; i > 0; i--) {
//            if (x[0] == x[i] && y[0] == y[i]) {
//                running = false;
//            }
//        }

        // Snake hits border
//        if (x[0] < 0 || x[0] >= SCREEN_WIDTH || y[0] < 0 || y[0] >= SCREEN_HEIGHT) {
//            running = false;
//        }
//
//        if (!running) {
//            timer.stop();
//        }
    }
//    public void gameOver(Graphics g) {
//        g.setColor(Color.RED);
//        g.setFont(new Font("Arial", Font.BOLD, 40));
//        FontMetrics metrics = getFontMetrics(g.getFont());
//        g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);
//
//        g.setColor(Color.WHITE);
//        g.setFont(new Font("Arial", Font.BOLD, 18));
//        g.drawString("Score: " + applesEaten, SCREEN_WIDTH / 2 - 40, SCREEN_HEIGHT / 2 + 40);
//    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        SnakeGame game = new SnakeGame();

        frame.add(game);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (direction != 'R') {
                    direction = 'L';
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != 'L') {
                    direction = 'R';
                }
                break;
            case KeyEvent.VK_UP:
                if (direction != 'D') {
                    direction = 'U';
                }
                break;
            case KeyEvent.VK_DOWN:
                if (direction != 'U') {
                    direction = 'D';
                }
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}