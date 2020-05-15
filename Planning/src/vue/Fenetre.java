/*SOURCES :
*http://www.codeurjava.com/2015/05/comment-dimensionner-fenetre-selon-ecran.html
*https://openclassrooms.com/fr/courses/26832-apprenez-a-programmer-en-java/23108-creez-votre-premiere-fenetre
*/
package vue;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 

public class Fenetre extends JFrame {  
    /*A CardLayout object is a layout manager for a container. It treats each component in the container as a 
    card. Only one card is visible at a time, and the container acts as a stack of cards. The first component 
    added to a CardLayout object is the visible component when the container is first displayed.
    The ordering of cards is determined by the container's own internal ordering of its component objects. 
    CardLayout defines a s²et of methods that allow an application to flip through these cards sequentially, or 
    to show a specified card. The addLayoutComponent(java.awt.Component, java.lang.Object) method can be used 
    to associate a string identifier with a given card for fast random access.
    */
    private CardLayout c = new CardLayout();
    private JPanel content = new JPanel();
    //Liste des noms de nos conteneurs pour la pile de cartes
    private String[] listContent = {"FormConnexion", "CARD_2", "CARD_3"};
    private int indice = 0;
    
    /*Constructeur par défaut*/      
    public Fenetre() { 
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width*2/3, screenSize.height*2/3); // donne une taille en hauteur et largeur à la fenêtre           
        //Nous demandons maintenant à notre objet de se positionner au centre
        this.setLocationRelativeTo(null);
        //Termine le processus lorsqu'on clique sur la croix rouge
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Plusieurs panneaux superposés permet de ne pas avoir 50 pages
        this.setTitle("CardLayout");

        //On crée trois conteneurs de couleur différente
        FormConnexion connexion = new FormConnexion();
        
        JPanel card2 = new JPanel();
        card2.setBackground(Color.red);		
        JPanel card3 = new JPanel();
        card3.setBackground(Color.green);

        JPanel boutonPane = new JPanel();
        JButton bouton = new JButton("Contenu suivant");
        //Définition de l'action du bouton
        bouton.addActionListener((ActionEvent event) -> {
            //Via cette instruction, on passe au prochain conteneur de la pile
            c.next(content);
        });

        JButton bouton2 = new JButton("Contenu par indice");
        //Définition de l'action du bouton2
        bouton2.addActionListener((ActionEvent event) -> {				
            if(++indice > 2)
                indice = 0;
            //Via cette instruction, on passe au conteneur correspondant au nom fourni en paramètre
            c.show(content, listContent[indice]);
        });

        boutonPane.add(bouton);
        boutonPane.add(bouton2);
        //On définit le layout
        content.setLayout(c);
        //On ajoute les cartes à la pile avec un nom pour les retrouver
        content.add(connexion, listContent[0]);
        content.add(card2, listContent[1]);
        content.add(card3, listContent[2]);

        this.getContentPane().add(boutonPane, BorderLayout.NORTH);
        this.getContentPane().add(content, BorderLayout.CENTER);
        this.setVisible(true);        
    }	     
}