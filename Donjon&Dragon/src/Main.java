import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;

public class Main {
    JFrame displayZoneFrame;
    LevelManager levelManager;

    public Main() throws Exception {
        //fenetre
        displayZoneFrame = new JFrame("Zelda");
        displayZoneFrame.setSize(1920, 1080);
        displayZoneFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // l'écran titre
        ImageIcon ecranTitre = new ImageIcon(ImageIO.read(new File("./img/GAME_Title.jpg")));
        JLabel ecranTitreLabel = new JLabel(ecranTitre);
        ecranTitreLabel.setLayout(null);

        // start
        ImageIcon startIcon = new ImageIcon("./img/start_button.png");
        JButton startButton = new JButton(startIcon);
        startButton.setBounds(540, 560, 403, 125);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.setOpaque(false);

        startButton.addActionListener(e -> {
            System.out.println("Bouton Start cliqué !");
            lancerJeu();
        });

        ecranTitreLabel.add(startButton);
        displayZoneFrame.add(ecranTitreLabel);
        displayZoneFrame.setVisible(true);
    }

    public void lancerJeu() {
        try {
            displayZoneFrame.getContentPane().removeAll();
            levelManager = new LevelManager(displayZoneFrame);
            levelManager.startGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        new Main();
    }
}
