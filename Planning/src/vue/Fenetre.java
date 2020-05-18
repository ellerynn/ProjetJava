/*SOURCES :
*http://www.codeurjava.com/2015/05/comment-dimensionner-fenetre-selon-ecran.html
*https://openclassrooms.com/fr/courses/26832-apprenez-a-programmer-en-java/23108-creez-votre-premiere-fenetre
*/
package vue;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 

public class Fenetre extends JFrame {  
    private FormConnexion connexion;
    private EmploiDuTemps edt;
    
    /*Constructeur*/      
    public Fenetre() { 
        /*A CardLayout object is a layout manager for a container. It treats each component in the container as a 
        card. Only one card is visible at a time, and the container acts as a stack of cards. The first component 
        added to a CardLayout object is the visible component when the container is first displayed.
        The ordering of cards is determined by the container's own internal ordering of its component objects. 
        CardLayout defines a s²et of methods that allow an application to flip through these cards sequentially, or 
        to show a specified card. The addLayoutComponent(java.awt.Component, java.lang.Object) method can be used 
        to associate a string identifier with a given card for fast random access.
        */
        CardLayout c = new CardLayout();
        JPanel content = new JPanel();
        //Liste des noms de nos conteneurs pour la pile de cartes
        String[] listContent = new String[]{"FormConnexion", "EmploiDuTemps"};
        int indice = 0;
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width*2/3, screenSize.height*2/3); // donne une taille en hauteur et largeur à la fenêtre           
        //Nous demandons maintenant à notre objet de se positionner au centre
        this.setLocationRelativeTo(null);
        //Termine le processus lorsqu'on clique sur la croix rouge
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Connexion");

        //On crée deux conteneurs : un pour la connexion, l'autre pour le reste (frame)
        connexion = new FormConnexion();
        edt = new EmploiDuTemps();
        
        //Définition de l'action du bouton
        connexion.getBouton().addActionListener((ActionEvent event) -> {
            System.out.println(connexion.getEmail());
            System.out.println(connexion.getPassword());
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
}