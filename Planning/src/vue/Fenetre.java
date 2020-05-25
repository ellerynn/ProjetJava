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
    private CardLayout c;
    private JPanel content;
    private Controle controle; //Lien avec le controle

    public Fenetre(Controle controle) {
        this.controle = controle;
        connexion = new FormConnexion();
        edt = new EmploiDuTemps();
        c = new CardLayout(); //CardLayout pour "superposer" plusieurs pages (conteneurs)
        content = new JPanel(); //Contenu actif du CardLayout
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Récupérer la taille de l'écran
        this.setSize(screenSize.width*2/3, screenSize.height*2/3); //Donne une taille en hauteur et largeur à la fenêtre -> 2/3 de l'écran       
        this.setLocationRelativeTo(null); //Positionner au centre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Termine le processus lorsqu'on clique sur la croix rouge
        this.setTitle("Connexion"); //Titre de la frame
                
        content.setLayout(c); //On définit le layout
        content.add(connexion); //On ajoute les conteneurs à la pile
        content.add(edt);
        
        this.getContentPane().add(content, BorderLayout.CENTER); //Affichage contenu actif
    
        //TRICHE CO RAPIDE
        connexion.setEmailPassWord("segado@edu.ece.fr", "referent");
        
        //Listeners
        connexion.getBouton().addActionListener((ActionEvent event) -> { //Définition de l'action du bouton connexion
            connect(connexion.getEmail(), connexion.getPassword());     
        });
        
        //COMBOBOX DES SEMAINES
        edt.getSemaineCours().addActionListener((ActionEvent event) -> {
            //On récupère la semaine sélectionnée
            String semaine = edt.getSemaineCours().getSelectedItem().toString();
            if (semaine == "Semaine") {
                edt.setEdtCours(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
                controle.seancesEdt(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR), connexion.getEmail(), connexion.getPassword());
            }
            
            else {
                edt.setEdtCours(Integer.parseInt(semaine));
                controle.seancesEdt(Integer.parseInt(semaine), connexion.getEmail(), connexion.getPassword());
            }
        });
        
        edt.getSemaineSalles().addActionListener((ActionEvent event) -> {
            //On récupère la semaine sélectionnée
            String semaine = edt.getSemaineSalles().getSelectedItem().toString();
            System.out.println(edt.getSemaineSalles().getSelectedItem().toString());
            if (semaine.equals("Semaine")) 
                edt.setEdtSalles(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
            
            else
                edt.setEdtSalles(Integer.parseInt(semaine));
        });
        
        //COMBOBOX DE RECHERCHE POUR LES REFERENTS ET ADMIN
        edt.getRechercheCours().addActionListener((ActionEvent event) -> {
            String user = edt.getRechercheCours().getSelectedItem().toString();
           
            //Récupérer le nom et le nom de famille
            String prenom = new String();
            String nom = new String();
             
            int pos = user.indexOf(' ');
            System.out.println(user + " position " + pos);
            
            prenom = user.substring(0, pos);
            nom = user.substring(pos+1);
            
            //EN ATTENTE
        });
    }
    
    //Getters
    public JTable getEdtCours() {
        return edt.getEdtCours();
    }
    
    public void connect(String email, String password) {
        if(controle.demandeConnexion(email, password)) { 
            //Via cette instruction, on passe au prochain conteneur de la pile
            c.next(content); 
            //Nouveau titre de la frame
            setTitle("Planning, " + calculAnneeScolaire() + " - " + controle.utilisateurCourant(email, password) 
                    + " (ECE Paris " + controle.recupInfo(email, password) + ")"); 
            controle.seancesEdt(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR), email, password); //On ajoute les séances a l'edt
            //On rempli la combobox de recherche avec tous les utilisateurs de la BDD, pour un référent
            remplirComboRecherche();
        }
    }
    
    public void remplirComboRecherche() {
        ArrayList<String> ttLeMonde = controle.allUsersToStrings();
        edt.setRechercheCours(ttLeMonde);        
    }
    
    public String calculAnneeScolaire() { //Pour affichage dans titre de la frame
        int annee = Calendar.getInstance().get(Calendar.YEAR); //Année courante
        if(Calendar.getInstance().get(Calendar.MONTH)+1 >= 9 && Calendar.getInstance().get(Calendar.MONTH)+1 <= 12) //Entre septembre et décembre
            return annee + "/" + (annee+1);
        return (annee-1) + "/" + annee;
    }
}

