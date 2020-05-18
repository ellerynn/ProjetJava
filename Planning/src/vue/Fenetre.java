/*SOURCES :
*http://www.codeurjava.com/2015/05/comment-dimensionner-fenetre-selon-ecran.html
*https://openclassrooms.com/fr/courses/26832-apprenez-a-programmer-en-java/23108-creez-votre-premiere-fenetre
*/
package vue;

import controleur.Controle;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 

public class Fenetre extends JFrame {  
    private FormConnexion connexion;
    private EmploiDuTemps edt;
    private Controle controle;
    
    /*Constructeur*/      
    public Fenetre(Controle controle) { 
        this.controle = controle;
        connexion = new FormConnexion();
        edt = new EmploiDuTemps();
        
        //Autres déclarations
        CardLayout c = new CardLayout();
        JPanel content = new JPanel();
        String[] listContent = new String[]{"FormConnexion", "EmploiDuTemps"}; //Liste des noms de nos conteneurs pour la pile de cartes
        int indice = 0;
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width*2/3, screenSize.height*2/3); // donne une taille en hauteur et largeur à la fenêtre          
        this.setLocationRelativeTo(null); //Nous demandons maintenant à notre objet de se positionner au centre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Termine le processus lorsqu'on clique sur la croix rouge
        this.setTitle("Connexion");
        
        //Définition de l'action du bouton
        connexion.getBouton().addActionListener((ActionEvent event) -> {
            actionBouton();
            //Via cette instruction, on passe au prochain conteneur de la pile
            c.next(content);
            setTitle("Planning, [Année scolaire] - [icone] [NOM Prénom] (ECE Paris [Promo])");
        });

        //On définit le layout
        content.setLayout(c);
        //On ajoute les cartes à la pile avec un nom pour les retrouver
        content.add(connexion, listContent[0]);
        content.add(edt, listContent[1]);

        this.getContentPane().add(content, BorderLayout.CENTER);
        this.setVisible(true);        
    }	  
    
    //Getters
    public JButton getBoutonConnexion() {
        return connexion.getBouton();
    }
    
    public FormConnexion getConnexion() {
        return connexion;
    }
    
    //Méthodes
    public void actionBouton() {
        controle.demandeConnexion();
    }
}