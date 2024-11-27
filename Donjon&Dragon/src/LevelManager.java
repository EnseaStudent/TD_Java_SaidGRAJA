import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;

public class LevelManager {
    private int currentLevel;
    private JFrame displayZoneFrame;
    private RenderEngine renderEngine;
    private PhysicEngine physicEngine;
    private GameEngine gameEngine;
    private DynamicSprite hero;

    public LevelManager(JFrame displayZoneFrame) throws Exception {
        this.displayZoneFrame = displayZoneFrame;
        this.currentLevel = 0;

        // moteurs et le héro
        hero = new DynamicSprite(200, 300,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 48, 50);
        renderEngine = new RenderEngine(displayZoneFrame);
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero);

        // clavier
        displayZoneFrame.addKeyListener(gameEngine);
    }

    public void startGame() throws Exception {
        loadLevel(1); //premier niveau
        setupTimers(); // timers
        JOptionPane.showMessageDialog(null, "Jafar a empoisonnée l'air, le temps vous est compté"); // comme il n'y a pas d'ennemie c'est comme ça qu'on va perdre de la vie
    }

    public void loadLevel(int level) throws Exception {
        currentLevel = level;
        if (level>3) { //il y a 3 nvx
            JOptionPane.showMessageDialog(null, "Bravo ! Vous avez déjoué les plans de Jafar ! Game Over !");
            System.exit(0);} // quitte le jeu};

        hero.setX(200); // position initiale
        hero.setY(300);
        hero.setHealth(100); //vie au max à chaque début de nv

        // SUPPRIME l'ancien contenu
        renderEngine.clearRenderList();
        physicEngine.clearMovingSpriteList();

        // NOUVEAY PLAYGRND
        String levelFilePath = "./data/level" + level + ".txt";
        Playground levelData = new Playground(levelFilePath,this);

        // Ajouter les éléments du niveau aux moteurs
        renderEngine.addToRenderList(levelData.getSpriteList());
        physicEngine.setEnvironment(levelData.getSolidSpriteList());


        renderEngine.addToRenderList(hero);
        physicEngine.addToMovingSpriteList(hero);


        displayZoneFrame.getContentPane().setLayout(new BorderLayout());
        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.requestFocusInWindow();
        displayZoneFrame.revalidate();
        displayZoneFrame.repaint();
    }

    public void nextLevel() throws Exception {
        loadLevel(currentLevel + 1);
    }

    private void setupTimers() { // config les timers

        // Timer pour mettre à jour les moteurs
        Timer renderTimer = new Timer(50, e -> renderEngine.update());
        Timer gameTimer = new Timer(50, e -> gameEngine.update());
        Timer physicTimer = new Timer(50, e -> physicEngine.update());


        Timer collisionTimer = new Timer(50, e -> {
            Rectangle2D.Double heroHitBox = (Rectangle2D.Double) hero.getHitBox();
        });



        // gestion la santé du héros
        Timer healthDecrementTimer = new Timer(1000, e -> {
            hero.takeDamage(2);
            System.out.println("Vie actuelle : " + hero.getHealth());
            if (hero.getHealth() <= 0) {
                System.out.println("Game Over!");
                JOptionPane.showMessageDialog(null, "Vous êtes asphyxié par l'ambre enchantée de jafar. Game Over !");
                System.exit(0);
            }
        });

        // dem les timers
        renderTimer.start();
        gameTimer.start();
        physicTimer.start();
        healthDecrementTimer.start();
        collisionTimer.start();
    }
}
