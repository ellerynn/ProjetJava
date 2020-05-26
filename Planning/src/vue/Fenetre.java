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
        connexion.setEmailPassWord("palasi@edu.ece.fr", "enseignant");
    }
    
    //Getters
    public EmploiDuTemps getEdt() {
        return this.edt;
    }
    
    public JTable getEdtCours() {
        return edt.getEdtCours();
    }
    
    public JTable getRecapCours() {
        return edt.getRecapCours();
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
            majEdtCoursParSemaine();
        });
        
        edt.getSemaineSalles().addActionListener((ActionEvent event) -> {
            majEdtSallesParSemaine();
        });
        
        //COMBOBOX DE RECHERCHE POUR LES REFERENTS ET ADMIN
        edt.getRechercheCours().addActionListener((ActionEvent event) -> {
            String recherche = edt.getRechercheCours().getSelectedItem().toString();
            if (!recherche.equals("Veuillez sélectionner")) 
                majEdtCoursParSemaine();
        });
        
        edt.getGroupesCours().addActionListener((ActionEvent event) -> {  
            majEdtGroupeCoursParSemaine();
        });
        
        //SPINNER DATE HOME
        edt.getDateHome().addChangeListener((ChangeEvent ce) -> {
            edt.setEdtHome();
            majEdtJour();
        });
    }
    
    public void connect(String email, String password) {
        if(controle.demandeConnexion(email, password)) { 
            //Via cette instruction, on passe au prochain conteneur de la pile
            c.next(content); 
            //Nouveau titre de la frame
            setTitle("Planning, " + calculAnneeScolaire() + " - " + controle.utilisateurCourant(email, password) 
                    + " (ECE Paris " + controle.recupInfo(email, password) + ")"); 
            //INITIALISATIONS COMBOBOX
            remplirComboRecherche(email, password);
            remplirComboGroupes();
            
            //controle.seancesRecap(connexion.getEmail(), connexion.getPassword());
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
    
    //MAJ Edt quand référent recherche quelqu'un vi JComboBox, par defaut utilisateur courant
    public void majEdt() {
        String user = edt.getRechercheCours().getSelectedItem().toString();
        System.out.println("\njcombobox " + edt.getRechercheCours().getSelectedItem().toString());
           
        //Récupérer le nom et le nom de famille
        String prenom = new String();
        String nom = new String();

        int pos = user.indexOf(' ');
        //System.out.println(user + " position " + pos);

        prenom = user.substring(0, pos);
        nom = user.substring(pos+1);
        System.out.println(prenom + " " + nom);

        controle.majSeancesEdt(Integer.parseInt(edt.getSemaineCours().getSelectedItem().toString()), prenom, nom);
    }
    
    //MAJ Edt quand un referent cherche un groupe
    public void majEdtGroupe() {
        String recherche = edt.getGroupesCours().getSelectedItem().toString();
        if(recherche != "Groupes") {
            controle.majSeancesEdt(Integer.parseInt(edt.getSemaineCours().getSelectedItem().toString()), recherche);
        }
        else;
    }
        
    //MAJ Edt quand on change le jour dans Home
    public void majEdtJour() {
        //Récup de la date du JSpinner
        String date = DateFormat.getDateInstance(1).format(edt.getDateHome().getValue()) ;
        //System.out.println(date);
 
        controle.seancesEdt(date, connexion.getEmail(), connexion.getPassword());
    }
    
    //Recup semaine select puis maj edt Cours
    public void majEdtCoursParSemaine() {
        //On récupère la semaine sélectionnée
        String semaine = edt.getSemaineCours().getSelectedItem().toString();
        if (semaine == "Semaine") {
            edt.setEdtCours(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
            majEdt();
        }

        else {
            System.out.println("Semaine selectionnee : " + semaine);
            edt.setEdtCours(Integer.parseInt(semaine));
            majEdt();
        }
    }
    
    //Recup semaine select puis maj edt Salles
    public void majEdtSallesParSemaine() {
        //On récupère la semaine sélectionnée
        String semaine = edt.getSemaineSalles().getSelectedItem().toString();
        if (semaine.equals("Semaine")) 
            edt.setEdtSalles(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));

        else
            edt.setEdtSalles(Integer.parseInt(semaine));
    }
    
    public void majEdtGroupeCoursParSemaine() {
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
}

