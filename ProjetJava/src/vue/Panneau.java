package vue;

import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
 
public class Panneau extends JPanel { 
    private int posX = -50;
    private int posY = -50;
    
    @Override
    public void paintComponent(Graphics g){
        /*
        System.out.println("Cercle ! "); 
        int x = this.getWidth()/4;
        int y = this.getHeight()/4;                      
        g.fillOval(x, y, this.getWidth()/2, this.getHeight()/2);
        */
        
        /*
        System.out.println("Phrase rouge ! ");
        Font font = new Font("Courier", Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.red);
        g.drawString("Wesh alors, on fait pas du graphisme nous ?", 10, 20);
        */
        
        /*
        System.out.println("Photo ! ");
        try {
            Image img = ImageIO.read(new File("logo_ECE.jpg"));
            g.drawImage(img, 0, 30, this);
            //Pour une image de fond
            //g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        } 
        catch (IOException e) {
            e.printStackTrace();
        } 
        */
        
        /*
        System.out.println("Gradient super moche ! ");
        Graphics2D g2d = (Graphics2D)g;
        GradientPaint gp, gp2, gp3, gp4, gp5, gp6; 
        gp = new GradientPaint(0, 0, Color.RED, 20, 0, Color.magenta, true);
        gp2 = new GradientPaint(20, 0, Color.magenta, 40, 0, Color.blue, true);
        gp3 = new GradientPaint(40, 0, Color.blue, 60, 0, Color.green, true);
        gp4 = new GradientPaint(60, 0, Color.green, 80, 0, Color.yellow, true);
        gp5 = new GradientPaint(80, 0, Color.yellow, 100, 0, Color.orange, true);
        gp6 = new GradientPaint(100, 0, Color.orange, 120, 0, Color.red, true);

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, 20, this.getHeight());               
        g2d.setPaint(gp2);
        g2d.fillRect(20, 0, 20, this.getHeight());
        g2d.setPaint(gp3);
        g2d.fillRect(40, 0, 20, this.getHeight());
        g2d.setPaint(gp4);
        g2d.fillRect(60, 0, 20, this.getHeight());
        g2d.setPaint(gp5);
        g2d.fillRect(80, 0, 20, this.getHeight());
        g2d.setPaint(gp6);
        g2d.fillRect(100, 0, 40, this.getHeight());
        */
        
        //Les 4 lignes suivantes pour effacer le cercle précédentb
        //On choisit une couleur de fond pour le rectangle
        g.setColor(Color.white);
        //On le dessine de sorte qu'il occupe toute la surface
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        g.setColor(Color.red);
        g.fillOval(posX, posY, 50, 50);
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }                   
}
