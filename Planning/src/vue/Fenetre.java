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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//La classe Fenetre correspond a toute l'interface graphique contenant la page de connexion et le planning (+gestion)
public class Fenetre extends JFrame {  
    /*Attributs*/
    private FormConnexion connexion; //Page de connexion de l'utilisateur
    private EmploiDuTemps edt; //Planning (accessible après connexion avec un CardLayout)
    private CardLayout c; //Ledit CardLayout
    private JPanel content; //Contenu actif du CL
    private Controle controle; //Lien avec le controle -> Controle recup BDD via modele et envoie instructions ici

    public Fenetre(Controle controle) {
        this.controle = controle;
        this.connexion = new FormConnexion();
        this.edt = new EmploiDuTemps();
                
        initContent(); //Initialisation du contenu actif de la frame (CardLayout et JPanel)
        initFrame(); //Initialisation de la frame au 2/3 de l'écran
        initListeners(); //Ajout de listeners sur les différents composants des pages et onglets
        
        //TRICHE CO RAPIDE
        connexion.setEmailPassWord("admin@gmail.com", "admin");
    }
    
    //Getters
    //Onglet EDT
    public EmploiDuTemps getEdt() { 
        return this.edt;
    }
    
    //Tableau contenant l'emploi du temps sur une semaine
    public JTable getEdtCours() {
        return edt.getEdtCours(); 
    }
    
    //Tableau contenant la liste de seances sur une annee scolaire
    public JTable getRecapCours() {
        return edt.getRecapCours();
    }
    
    //Onglet Home
    //Tableau contenant l'emploi du temps sur une journée 
    public JTable getEdtHome() {
        return edt.getEdtHome();
    }
    
    //Méthodes
    //Initialisation du contenu de la frame (this)
    public void initContent() {
        c = new CardLayout(); //CardLayout pour "superposer" plusieurs pages (conteneurs)
        content = new JPanel(); //Contenu actif du CardLayout
        content.setLayout(c); //On définit le layout
        content.add(connexion); //On ajoute les conteneurs à la pile
        content.add(edt);
    }
    
    //Initialisation dimensions, titre, etc de la frame (this)
    public void initFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Récupérer la taille de l'écran
        this.setSize(screenSize.width*2/3, screenSize.height*2/3); //Donne une taille en hauteur et largeur à la fenêtre -> 2/3 de l'écran       
        this.setLocationRelativeTo(null); //Positionner au centre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Termine le processus lorsqu'on clique sur la croix rouge
        this.setTitle("Connexion"); //Titre de la frame
        this.getContentPane().add(content, BorderLayout.CENTER); //Affichage contenu actif
    }
    
    //Initialisation des listeners communs a tous les profils
    public void initListeners() {
        //Listeners
        //BOUTON CONNEXION
        connexion.getBouton().addActionListener((ActionEvent event) -> { //Définition de l'action du bouton connexion
            connect(connexion.getEmail(), connexion.getPassword());     
        });
        
        //BOUTON DECONNEXION
        edt.getBoutonDeco().addActionListener((ActionEvent event) -> { //Définition de l'action du bouton connexion
            c.previous(content); 
            rendreInvisible();
            //Nouveau titre de la frame
            //setTitle("Connexion"); 
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
            System.out.println("recherche : " + recherche);
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
        
        //TABLEAU RECAP
        edt.getRecapCours().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {                
                if (e.getClickCount() == 1) { //Un clic -> affichage complet des seances
                    controle.basicRowHeights();
                    controle.updateRowHeights();
                }
                
                if (e.getClickCount() == 2) { //Deux clics -> le contraire :)
                    controle.basicRowHeights();
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {}
            @Override
            public void mouseReleased(MouseEvent me) {}
            @Override
            public void mouseEntered(MouseEvent me) {}
            @Override
            public void mouseExited(MouseEvent me) {}
        });
    }
    
    //Initialisation des listener SEULEMENT pour l'admin
    public void initListenersForAdmin(){ 
    //L'onglet SP est initialisé dans edt que quand l'admin se connecte, iniListeners n'accepte pas mes Listeners 
    //car c'est avant la connection et donc l'onglet SP est vide (= pas de JMachin encore) ;'(...
        edt.getBtnValider().addActionListener((ActionEvent event)->{
            controle.demandeAddSeance(edt.getInfosAddSeance());
        });
     
        edt.getBtnValider2().addActionListener((ActionEvent event)->{
            System.out.println("Valider2: Tu veux quoi ? ");
        });
        
        edt.getBtnValider3().addActionListener((ActionEvent event)->{
            System.out.println("Valider3: Tu veux une tarte, c'est ça ? ");
        });
        
        edt.getListeSeances().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if(!lse.getValueIsAdjusting()){
                    if(!edt.getListeSeances().isSelectionEmpty())
                    {
                        JList source = (JList)lse.getSource();
                        String selected = source.getSelectedValue().toString();
                        System.out.println("Bon je fais quoi après ça : "+selected);
                    }
                }
            }
        });
    }
    
    //Connexion
    //Si l'utilisateur a saisi son email et son mdp et que cela correspond à la BDD, on passe à la suite
    public void connect(String email, String password) {
        if(controle.demandeConnexion(email, password)) { 
            //Via cette instruction, on passe au prochain conteneur de la pile
            c.next(content); 
            //Nouveau titre de la frame
            setTitle("Planning, " + calculAnneeScolaire() + " - " + controle.utilisateurCourant(email, password) 
                    + " (ECE Paris " + controle.recupInfo(email, password) + ")"); 
            //INITIALISATIONS COMBOBOX
            remplirComboRecherche(email, password); //Utile pour TOUS les profil car on set l'edt selon son contenu
            remplirComboGroupes(); //Juste pour le référent [?]
            remplirDonneesAdmin(); //Juste pour l'admin
            controle.seancesRecap(connexion.getEmail(), connexion.getPassword()); //Set edt de l'utilisateur courant (par défaut)
        }
    }
    
    //Rempli la combobox avec les utilisateurs de la BDD
    public void remplirComboRecherche(String email, String password) {
        ArrayList<String> ttLeMonde = controle.allUsersToStrings();
        edt.setRechercheCours(ttLeMonde); 
        
        String utilisateur = controle.utilisateurCourant(email, password);
        edt.getRechercheCours().setSelectedItem(utilisateur);
    }
    
    //Rempli la combobox avec les groupes de la BDD
    public void remplirComboGroupes() {
        ArrayList<String> ttLesGroupes = controle.allGroupsToStrings();
        edt.setGroupesCours(ttLesGroupes); 
    }
    
    //Remplissage des différents container de l'onglet Service Planification
    public void remplirDonneesAdmin() {
        if (controle.admin(connexion.getEmail(), connexion.getPassword())) { //Si c'est un admin
            remplirComboTypes();
            remplirComboCours();
            remplirListSalles();
            remplirListGroupes();
            remplirListEnseignants();
            remplirListSeances();
            //On active les listeners des Classes de OngletServicePlanification, 
            //car onglet SP est initialisé avec addOngletServicePlanification() de edt
            initListenersForAdmin();
        }
    }
    
    //Rempli la combobox avec les types de cours de la BDD
    public void remplirComboTypes(){
        ArrayList<String> ttLesTypes = controle.allTypeToStrings();
        edt.setTypes(ttLesTypes);
    }
    
    //Rempli la combobox avec les cours de la BDD
    public void remplirComboCours(){
        ArrayList<String> ttLesCours = controle.allCoursToStrings();
        edt.setCours(ttLesCours);
    }
    
    //Rempli la combobox avec les salles de la BDD
    public void remplirListSalles(){
        ArrayList<String> ttLesSalles = controle.allSallesToStrings();
        edt.setSalles(ttLesSalles);
    }
    
    //Rempli la combobox avec les groupes de la BDD
    public void remplirListGroupes(){//Je ne sais pas s'il y a myn de fusionner avec remplirComboGroupes
        ArrayList<String> ttLesGrps = controle.allGroupsToStrings();
        edt.setGroupes(ttLesGrps);
    }
    
    //Rempli la combobox avec les enseignants de la BDD
    public void remplirListEnseignants(){
        ArrayList<String> ttLesEnseignants = controle.allEnseignantsToStrings();
        edt.setEnseignants(ttLesEnseignants);
    }
    
    //Rempli la combobox avec les seances de la BDD
    public void remplirListSeances(){
        ArrayList<String> ttLesSeances = controle.allSeancesToStrings();
        edt.setSeances(ttLesSeances);
    }
    
    //Calcul de l'année scolaire en cours
    public String calculAnneeScolaire() { //Pour affichage dans titre de la frame
        int annee = Calendar.getInstance().get(Calendar.YEAR); //Année courante
        if(Calendar.getInstance().get(Calendar.MONTH)+1 >= 9 && Calendar.getInstance().get(Calendar.MONTH)+1 <= 12) //Entre septembre et décembre
            return annee + "/" + (annee+1);
        return (annee-1) + "/" + annee;
    }
    
    //Rend visible certaines composants selon le profil
    public void rendreVisible() {
        edt.getRechercheCours().setVisible(true);
        edt.getRechercheBarreCours().setVisible(true);
        edt.getRechercheBoutonCours().setVisible(true);
        edt.getGroupesCours().setVisible(true);
    }
    
    public void rendreInvisible() {
        edt.getRechercheCours().setVisible(false);
        edt.getRechercheBarreCours().setVisible(false);
        edt.getRechercheBoutonCours().setVisible(false);
        edt.getGroupesCours().setVisible(false);
    }
    
    //MAJ Edt de la personne contenue dans la JComboBox utilisateurs
    //Par defaut utilisateur courant
    //Un utilisateur ne peut pas le modifier sauf s'il est référent
    public void majEdt() {
        String user = edt.getRechercheCours().getSelectedItem().toString();
        //System.out.println("\njcombobox " + edt.getRechercheCours().getSelectedItem().toString());
           
        //Récupérer le nom et le nom de famille
        String prenom = new String();
        String nom = new String();

        int pos = user.indexOf(' ');
        //System.out.println(user + " position " + pos);

        prenom = user.substring(0, pos);
        nom = user.substring(pos+1);

        controle.majSeancesEdt(Integer.parseInt(edt.getSemaineCours().getSelectedItem().toString()), prenom, nom);
    }
    
    //MAJ Edt quand un referent cherche un groupe
    public void majEdtGroupe() {
        String recherche = edt.getGroupesCours().getSelectedItem().toString();
        if(recherche != "Groupes") 
            controle.majSeancesEdt(Integer.parseInt(edt.getSemaineCours().getSelectedItem().toString()), recherche);
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
            //System.out.println("Semaine selectionnee : " + semaine);
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
    
    //Recup semaine select puis maj edt pour un groupe (fonction référent)
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

