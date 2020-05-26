/*SOURCES :
*http://www.codeurjava.com/2015/05/comment-dimensionner-fenetre-selon-ecran.html
*https://openclassrooms.com/fr/courses/26832-apprenez-a-programmer-en-java/23108-creez-votre-premiere-fenetre
*/
package vue;

import controleur.Controle;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.util.*;
import javax.swing.*; 
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
                
        initContent(); //Initialisation du contenu actif de la frame (CardLayout et JPanel)
        initFrame(); //Initialisation de la frame au 2/3 de l'écran
        initListeners(); //Ajout de listeners sur les différents composants des pages et onglets
        
        //TRICHE CO RAPIDE
        connexion.setEmailPassWord("segado@edu.ece.fr", "referent");
    }
    
    //Getters
    public EmploiDuTemps getEdt() {
        return this.edt;
    }
    
    public JTable getEdtCours() {
        return edt.getEdtCours();
    }
    
    public JTable getEdtHome() {
        return edt.getEdtHome();
    }
    
    //Méthodes
    public void initContent() {
        c = new CardLayout(); //CardLayout pour "superposer" plusieurs pages (conteneurs)
        content = new JPanel(); //Contenu actif du CardLayout
        content.setLayout(c); //On définit le layout
        content.add(connexion); //On ajoute les conteneurs à la pile
        content.add(edt);
    }
    
    public void initFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Récupérer la taille de l'écran
        this.setSize(screenSize.width*2/3, screenSize.height*2/3); //Donne une taille en hauteur et largeur à la fenêtre -> 2/3 de l'écran       
        this.setLocationRelativeTo(null); //Positionner au centre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Termine le processus lorsqu'on clique sur la croix rouge
        this.setTitle("Connexion"); //Titre de la frame
        this.getContentPane().add(content, BorderLayout.CENTER); //Affichage contenu actif
    }
    
    public void initListeners() {
        //Listeners
        connexion.getBouton().addActionListener((ActionEvent event) -> { //Définition de l'action du bouton connexion
            connect(connexion.getEmail(), connexion.getPassword());     
        });
        
        //COMBOBOX DES SEMAINES dans Cours et dans Salles
        edt.getSemaineCours().addActionListener((ActionEvent event) -> {
            chargerEdt();
            edt.getGroupesCours().setSelectedItem("Groupes");
        });
        
        edt.getSemaineSalles().addActionListener((ActionEvent event) -> {
            //On récupère la semaine sélectionnée
            String semaine = edt.getSemaineSalles().getSelectedItem().toString();
            if (semaine.equals("Semaine")) 
                edt.setEdtSalles(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
            
            else
                edt.setEdtSalles(Integer.parseInt(semaine));
        });
        
        //COMBOBOX DE RECHERCHE POUR LES REFERENTS ET ADMIN
        edt.getRechercheCours().addActionListener((ActionEvent event) -> {
            chargerEdt();
        });
        
        edt.getGroupesCours().addActionListener((ActionEvent event) -> {
            chargerGroupeEdt();
        });
        
        //SPINNER DATE HOME
        edt.getDateHome().addChangeListener((ChangeEvent ce) -> {
            chargerEdtJour();
        });
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
            remplirComboRecherche(email, password);
            remplirComboGroupes();
        }
    }
    
    public void remplirComboRecherche(String email, String password) {
        ArrayList<String> ttLeMonde = controle.allUsersToStrings();
        edt.setRechercheCours(ttLeMonde); 
        
        String utilisateur = controle.utilisateurCourant(email, password);
        edt.getRechercheCours().setSelectedItem(utilisateur);
    }
    
    public void remplirComboGroupes() {
        ArrayList<String> ttLesGroupes = controle.allGroupsToStrings();
        edt.setGroupesCours(ttLesGroupes); 
    }
    
    public String calculAnneeScolaire() { //Pour affichage dans titre de la frame
        int annee = Calendar.getInstance().get(Calendar.YEAR); //Année courante
        if(Calendar.getInstance().get(Calendar.MONTH)+1 >= 9 && Calendar.getInstance().get(Calendar.MONTH)+1 <= 12) //Entre septembre et décembre
            return annee + "/" + (annee+1);
        return (annee-1) + "/" + annee;
    }
    
    public void rendreVisible() {
        edt.getRechercheCours().setVisible(true);
        edt.getRechercheBarreCours().setVisible(true);
        edt.getRechercheBoutonCours().setVisible(true);
    }

    public void majEdt() {
        String user = edt.getRechercheCours().getSelectedItem().toString();
           
        //Récupérer le nom et le nom de famille
        String prenom = new String();
        String nom = new String();

        int pos = user.indexOf(' ');
        System.out.println(user + " position " + pos);

        prenom = user.substring(0, pos);
        nom = user.substring(pos+1);

        controle.majSeancesEdt(Integer.parseInt(edt.getSemaineSalles().getSelectedItem().toString()), prenom, nom);
    }
    
    public void chargerEdt() {
        //On récupère la semaine sélectionnée
        String semaine = edt.getSemaineCours().getSelectedItem().toString();
        if (semaine == "Semaine") {
            edt.setEdtCours(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
            majEdt();
        }

        else {
            edt.setEdtCours(Integer.parseInt(semaine));
            majEdt();
        }
    }
    
    public void chargerGroupeEdt() {
        //On récupère la semaine sélectionnée
        String semaine = edt.getSemaineCours().getSelectedItem().toString();
        if (semaine == "Semaine") {
            edt.setEdtCours(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
            majEdtGroupe();
        }

        else {
            edt.setEdtCours(Integer.parseInt(semaine));
            majEdtGroupe();
        }
    }
    
    public void majEdtJour() {
        //Récup de la date du JSpinner
        String date = DateFormat.getDateInstance(1).format(edt.getDateHome().getValue()) ;
        System.out.println(date);
 
        controle.majSeancesEdt(date, connexion.getEmail(), connexion.getPassword());
    }
    
    public void chargerEdtJour() {
        edt.setEdtHome();
        majEdtJour();
    }
    
    public void majEdtGroupe() {
        String recherche = edt.getGroupesCours().getSelectedItem().toString();
        
        if(recherche != "Groupes") {
            controle.majSeancesEdt(Integer.parseInt(edt.getSemaineSalles().getSelectedItem().toString()), recherche);
        }
    }
}

