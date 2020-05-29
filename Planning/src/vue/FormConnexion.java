package vue;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */
public class FormConnexion extends JPanel {
   //Attributs                    
    private JButton bouton; //Bouton connexion écouté dans Fenetre
    private JTextField email; //Champ de saisie utilisateur
    private JPasswordField password; //Champ de saisie utilisateur, mode mot de passe (caractères cachés)
    
    /**
     * constructeur
     */
    public FormConnexion() {
        bouton = new JButton("Se connecter");
        email = new JTextField();
        password = new JPasswordField();
        
        this.setLayout(new GridBagLayout()); //Initialisation du container
        GridBagConstraints c = new GridBagConstraints(); //Contraintes d'ajout des composants
        c.insets = new Insets(10,10,10,10);
        
        JLabel titre1 = new JLabel("Connexion"); //JLabel titre
        JLabel douille = new JLabel("              "); 
        JLabel titre2 = new JLabel("Identifiant   ");
        JLabel titre3 = new JLabel("Mot de passe  ");
        
        c.gridx = 1; c.gridy = 0; //Position
        this.add(titre1, c); //Ajout au conteneur
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 1; c.gridx = 0;
        this.add(titre2, c); //Ajout au conteneur
        c.gridx = 1;
        email.setPreferredSize(new Dimension(250, 20));
        this.add(email, c);
        
        c.gridx = 0; c.gridy = 2;
        this.add(titre3, c); //Ajout au conteneur
        c.gridx = 1;
        this.add(password, c);
        
        c.fill = GridBagConstraints.NONE;
        c.gridx = 1; c.gridy = 3; //Position
        this.add(bouton, c);
        
        c.gridx = 2; c.gridy = 1;
        this.add(douille, c);
    }
    
    //Getters
    /**
     * retourne le bouton de connexion
     * @return
     */
    public JButton getBouton() { //Pour passer au site lorsqu'on appuie sur le bouton connexion
        return bouton;
    }
    
    /**
     * retourne le texte saisi dans le champ email
     * @return
     */
    public String getEmail() {
        return email.getText();
    }
    
    /**
     * retourne le texte saisi dans le champ mot de passe
     * @return
     */
    public String getPassword() {
        return String.valueOf(password.getPassword()); //On renvoie une String et non pas un char[]
    }
    
    //Setters
    /**
     * "triche" connexion rapide dans main pour tests
     * fonction à supprimer ?
     * @param email
     * @param password
     */
    public void setEmailPassWord(String email, String password) {
        this.email.setText(email);
        this.password.setText(password);
    }
}