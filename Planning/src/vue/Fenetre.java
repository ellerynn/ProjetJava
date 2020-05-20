/*SOURCES :
*http://www.codeurjava.com/2015/05/comment-dimensionner-fenetre-selon-ecran.html
*https://openclassrooms.com/fr/courses/26832-apprenez-a-programmer-en-java/23108-creez-votre-premiere-fenetre
*/
package vue;

import controleur.Controle;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*; 

//La classe Fenetre correspond a toute l'interface graphique contenant la page de connexion et le planning (+gestion)
public class Fenetre extends JFrame {  
    /*Attributs*/
    private FormConnexion connexion; //Page de connexion de l'utilisateur
    private EmploiDuTemps edt; //Planning (accessible après connexion avec un CardLayout)
    private Controle controle; //Lien avec le controle
    
    /*Constructeur*/      
    public Fenetre(Controle controle) { 
        this.controle = controle;
        connexion = new FormConnexion();
        edt = new EmploiDuTemps();
        
        //Autres déclarations
        CardLayout c = new CardLayout(); //CardLayout pour "superposer" plusieurs pages (conteneurs)
        JPanel content = new JPanel(); //Contenu actif du CardLayout
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Récupérer la taille de l'écran
        this.setSize(screenSize.width*2/3, screenSize.height*2/3); //Donne une taille en hauteur et largeur à la fenêtre -> 2/3 de l'écran       
        this.setLocationRelativeTo(null); //Positionner au centre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Termine le processus lorsqu'on clique sur la croix rouge
        this.setTitle("Connexion"); //Titre de la frame
       
        connexion.getBouton().addActionListener((ActionEvent event) -> { //Définition de l'action du bouton connexion
            if(actionBouton()) { 
                c.next(content); //Via cette instruction, on passe au prochain conteneur de la pile
                setTitle("Planning, " + calculAnneeScolaire() + " - " + utilisateurCourant() + " (ECE Paris " + recupInfo() + ")"); //Nouveau titre de la frame
            }     
        });
        
        content.setLayout(c); //On définit le layout
        content.add(connexion); //On ajoute les conteneurs à la pile
        content.add(edt);

        this.getContentPane().add(content, BorderLayout.CENTER); //Affichage contenu actif
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
    public Boolean actionBouton() { //actionBouton renvoie true si on trouve dans la BDD un utilisateur après clic
        return controle.demandeConnexion();
    }
    
    public String calculAnneeScolaire() {
        Calendar cal = Calendar.getInstance(); //Date du jour
        int annee = cal.get(Calendar.YEAR); //Année courante
        if(cal.get(Calendar.MONTH)+1 >= 9 && cal.get(Calendar.MONTH)+1 <= 12) //Entre septembre et décembre
            return annee + "/" + (annee+1);
        return (annee-1) + "/" + annee;
    }
    
    public String utilisateurCourant() {
        return controle.getUtilisateur().getNom() + " " + controle.getUtilisateur().getPrenom();
    }
    
    public String recupInfo() {
        String info = new String();
        //Si etudiant -> Promo
        if(controle.getUtilisateur().getDroit() == 4) {
            //Récupérer controle, puis utilisateur, groupe, puis promotion
            info = controle.getEtudiant().getGroupe().getPromotion().getNom();
        }
        //Si enseignant -> "Enseignant"
        if(controle.getUtilisateur().getDroit() == 3) {
            info = "Enseignant";
        }
        //Si référent pédagogique -> "Enseignant - Référent pédagogique"
        if(controle.getUtilisateur().getDroit() == 2) {
            info = "Enseignant - Référent pédagogique";
        }
        //Si admin -> "Administrateur"
        if(controle.getUtilisateur().getDroit() == 1) {
            info = "Administrateur";
        }
        
        return info;
    }
}