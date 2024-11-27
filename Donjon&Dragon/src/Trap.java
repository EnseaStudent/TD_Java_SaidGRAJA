import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class Trap extends SolidSprite {

    public Trap(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
    }

    @Override
    public boolean intersect(Rectangle2D.Double hitBox) {
        if (super.intersect(hitBox)) {
            // effet du piège
            JOptionPane.showMessageDialog(null, "Vous êtes tombé dans un piège. Game Over !");
            System.exit(0); // fin du game
            return true;
        }
        return false;
    }
}
