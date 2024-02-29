import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class No5 extends JFrame {

    private int x = 50;
    private int y = 200;
    private int direction = 1;
    private int heartSize = 25;  // Initial heart size
    private BufferedImage bufferImage;

    public No5() {
        setTitle("Beating Heart Animation");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bufferImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateHeartSize();
                repaint();
            }
        });

        timer.start();
        
        Timer animationTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePosition();
                repaint();
            }
        });

        animationTimer.start();
    }

    private void updatePosition() {
        x += direction;
        if (x >= getWidth() - heartSize || x <= 0) {
            direction *= -1;
        }
    }

    private void updateHeartSize() {
        // Increase or decrease the heart size
        if (heartSize == 25) {
            heartSize = 50;
        } else {
            heartSize = 25;
        }
    }

    @Override
    public void paint(Graphics g) {
        // Use double buffering
        Graphics2D g2d = bufferImage.createGraphics();
        super.paint(g2d);
        drawHeart(g2d, x, y, heartSize);

        // Draw the off-screen image to the screen
        g.drawImage(bufferImage, 0, 0, this);
    }

    private void drawHeart(Graphics2D g2d, int x, int y, int size) {
        // Draw left half of the heart
        g2d.setColor(Color.RED);
        g2d.fillArc(x, y, size, size, 0, 180);
        // Draw right half of the heart
        g2d.fillArc(x + size, y, size, size, 0, 180);
        // Draw triangle at the bottom of the heart
        int[] xPoints = {x, x + size, x + size * 2};
        int[] yPoints = {y + size, y + size * 2, y + size};
        g2d.fillPolygon(xPoints, yPoints, 3);

        // Draw the line
        g2d.setColor(Color.BLACK);
        g2d.drawLine(0, y, getWidth(), y);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new No5().setVisible(true);
            }
        });
    }
}
