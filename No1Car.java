import java.awt.*;
import java.awt.geom.*;

public class No1Car extends Frame {
    No1Car() {
        addWindowListener(new MyFinishWindow());
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        BasicStroke bs = new BasicStroke(4.0f);
        g2d.setStroke(bs);

        GeneralPath gp = new GeneralPath();

        gp.moveTo(60, 120);

        gp.lineTo(80, 120);
        gp.quadTo(90, 140, 100, 120);
        gp.lineTo(160, 120);
        gp.quadTo(170, 140, 180, 120);
        gp.quadTo(195, 120, 200, 110);

        // Modify the curveTo coordinates to create rounded corners
        gp.curveTo(200, 100, 205, 90, 160, 90);
        gp.quadTo(120, 90, 115, 95);
        gp.lineTo(103, 108);
        gp.quadTo(98, 110, 95, 110);
        gp.lineTo(70, 110);
        gp.quadTo(65, 110, 60, 120);

        g2d.draw(gp);

        g2d.setStroke(new BasicStroke(1.0f));
        drawSimpleCoordinateSystem(200, 150, g2d);
    }

    public static void drawSimpleCoordinateSystem(int xmax, int ymax, Graphics2D g2d) {
        int xOffset = 30;
        int yOffset = 50;
        int step = 20;
        String s;
        Font fo = g2d.getFont();
        g2d.setFont(new Font("sansserif", Font.PLAIN, 9));
        g2d.drawLine(xOffset, yOffset, xmax, yOffset);

        for (int i = xOffset + step; i <= xmax; i = i + step) {
            g2d.drawLine(i, yOffset - 2, i, yOffset + 2);
            g2d.drawString(String.valueOf(i), i - 7, yOffset - 7);
        }

        g2d.drawLine(xOffset, yOffset, xOffset, ymax);

        s = "  ";
        for (int i = yOffset + step; i <= ymax; i = i + step) {
            g2d.drawLine(xOffset - 2, i, xOffset + 2, i);
            if (i > 99) {
                s = "";
            }
            g2d.drawString(s + String.valueOf(i), xOffset - 25, i + 5);
        }

        g2d.setFont(fo);
    }

    public static void main(String[] argv) {
        No1Car f = new No1Car();
        f.setTitle("GeneralPath example");
        f.setSize(250, 200);
        f.setVisible(true);
    }
}