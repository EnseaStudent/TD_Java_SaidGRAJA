import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class Princess extends SolidSprite {

    private LevelManager levelManager;

    public Princess(double x, double y, Image image, double width, double height, LevelManager levelManager) {
        super(x, y, image, width, height);
        this.levelManager = levelManager;
    }

    @Override
    public boolean intersect(Rectangle2D.Double hitBox) {
        if (super.intersect(hitBox)) {
            // victoire
            JOptionPane.showMessageDialog(null, "Félicitations, vous avez sauvé la princesse !");
            try {
                levelManager.nextLevel(); // niveau suivant
            } catch (Exception e) {
                e.printStackTrace();
            }

            return true;
        }
        return false;
    }
}
