import java.awt.*;

public class Sprite implements Displayable{
    protected double x;
    protected double y;
    protected final Image image;
    protected final double width;
    protected final double height;

    public Sprite(double x, double y, Image image, double width, double height) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return (int)x;
    }

    public int getY() {
        return (int)y;
    }

    public void setX(double x) {// BESOIN DE SETTER POUR DEPLACER LE HERO AU DEBUT DU NIVEAU
        this.x = x;
    }

    public void setY(double x) {
        this.x = y;
    }
    @Override
    public void draw(Graphics g) {
        g.drawImage(image,(int)x,(int)y,null);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,(int)width,(int)height);
    }
}
