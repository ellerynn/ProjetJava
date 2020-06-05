package vue;

import controleur.Controle;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import org.jfree.chart.ChartPanel;

/**
 *http://www.codeurjava.com/2015/05/comment-dimensionner-fenetre-selon-ecran.html
 *https://openclassrooms.com/fr/courses/26832-apprenez-a-programmer-en-java/23108-creez-votre-premiere-fenetre
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */
public class Fenetre extends JFrame {  
    /*Attributs*/
    private FormConnexion connexion; //Page de connexion de l'utilisateur
    private EmploiDuTemps edt; //Planning (accessible après connexion avec un CardLayout)
    private CardLayout c; //Ledit CardLayout
    private JPanel content; //Contenu actif du CL
    private Controle controle; //Lien avec le controle -> Controle recup BDD via modele et envoie instructions ici

    /**
     *
     * @param controle
     */
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
    
    /**
     * @return l'onglet Emploi du temps
     */
    public EmploiDuTemps getEdt() { 
        return this.edt;
    }
    
    /**
     * @return le tableau contenant l'emploi du temps sur une semaine dans l'onglet Cours
     */
    public JTable getEdtCours() {
        return edt.getEdtCours(); 
    }
    
    /**
     * @return le tableau contenant l'emploi du temps sur une semaine dans l'onglet Cours
     */
    public JTable getListeCours() {
        return edt.getJTListeCours(); 
    }
    
    /**
     * @return le tableau contenant l'emploi du temps sur une semaine dans l'onglet Cours
     */
    public JTable getEdtSalles() {
        return edt.getEdtSalles(); 
    }
    
    /**
     * @return le tableau contenant l'emploi du temps sur une semaine dans l'onglet Salles
     */
    public JTable getListeSalles() {
        return edt.getJTListeSalles(); 
    }
    
    /**
     * @return le tableau contenant la liste de seances sur une annee scolaire
     */
    public JTable getRecapCours() {
        return edt.getRecapCours();
    }
    
    /**
     * @return le tableau contenant l'emploi du temps sur une journée dans l'onglet Home
     */
    public JTable getEdtHome() {
        return edt.getEdtHome();
    }
    
    /**
     * initialisation du contenu de la frame (this)
     */
    public void initContent() {
        c = new CardLayout(); //CardLayout pour "superposer" plusieurs pages (conteneurs)
        content = new JPanel(); //Contenu actif du CardLayout
        content.setLayout(c); //On définit le layout
        content.add(connexion); //On ajoute les conteneurs à la pile
        content.add(edt);
    }
    
    /**
     * initialisation dimensions, titre, etc de la frame (this)
     */
    public void initFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Récupérer la taille de l'écran
        this.setSize(screenSize.width*2/3, screenSize.height*2/3); //Donne une taille en hauteur et largeur à la fenêtre -> 2/3 de l'écran       
        this.setLocationRelativeTo(null); //Positionner au centre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Termine le processus lorsqu'on clique sur la croix rouge
        this.setTitle("Connexion"); //Titre de la frame
        this.getContentPane().add(content, BorderLayout.CENTER); //Affichage contenu actif
    }
    
    /**
     * initialisation des listeners communs a tous les profils
     */
    public void initListeners() {
        //Listeners
        //BOUTON CONNEXION
        connexion.getBouton().addActionListener((ActionEvent event) -> {
            //Définition de l'action du bouton connexion
            connect(connexion.getEmail(), connexion.getPassword());
        });
        
        //BOUTON DECONNEXION
        edt.getBoutonDeco().addActionListener((ActionEvent event) -> { //Définition de l'action du bouton connexion
            c.previous(content); 
            rendreInvisible();
            edt.remove(edt.getOngletSP());
            //Nouveau titre de la frame
            setTitle("Connexion"); 
        });
        
        //COMBOBOX DE VUE SOUHAITEE EDT
        edt.getVueCours().addActionListener((ActionEvent event) -> {
            if(edt.getVueCours().getSelectedItem() == "en grille") {
                majEdtCoursParSemaine();
                edt.getGrilleCours().setVisible(true);
                edt.getListeCours().setVisible(false);
            }
            else {
                majListeCoursParSemaine();
                edt.getListeCours().setVisible(true);
                edt.getGrilleCours().setVisible(false);
            }
        });
        
        edt.getVueSalles().addActionListener((ActionEvent event) -> {
            String recherche = edt.getRechercheSalles().getSelectedItem().toString();
            if(edt.getVueSalles().getSelectedItem() == "en grille") {
                if (!recherche.equals("Veuillez sélectionner"))
                    edt.getRechercheSalles().setSelectedItem(recherche); //On enclenche l'écouteur du menu, qui va update, afin d'éviter les doublons
                edt.getGrilleSalles().setVisible(true);
                edt.getListeSalles().setVisible(false);
            }
            else {
                if (!recherche.equals("Veuillez sélectionner"))
                    edt.getRechercheSalles().setSelectedItem(recherche); //On enclenche l'écouteur du menu, qui va update, afin d'éviter les doublons
                edt.getListeSalles().setVisible(true);
                edt.getGrilleSalles().setVisible(false);
            }
        });
        
        //COMBOBOX DES SEMAINES dans Cours et dans Salles
        edt.getSemaineCours().addActionListener((ActionEvent event) -> {
            //S'il s'agit d'une recherche par user
            if(!edt.getRechercheCours().getSelectedItem().toString().equals("Veuillez sélectionner")){
                if(edt.getVueCours().getSelectedItem() == "en grille") {
                    System.out.println("grille");
                    majEdtCoursParSemaine();
                }
                else {
                    System.out.println("liste");
                    majListeCoursParSemaine();
                }
            }
            //S'il s'agit d'une recherche par Groupe
            
            if(!edt.getGroupesCours().getSelectedItem().toString().equals("Groupes")){
                if(edt.getVueCours().getSelectedItem() == "en grille") {
                    System.out.println("grille");
                    majEdtGroupeCoursParSemaine();
                }
                else {
                    System.out.println("liste oé");
                    majListeGroupeCoursParSemaine();
                }
            }
            //S'il s'agit d'une recherch par promo
            if(!edt.getRecherchePromo().getSelectedItem().toString().equals("Promos")){
                if(edt.getVueCours().getSelectedItem() == "en liste") {
                    System.out.println("liste");
                    majListePromoParSemaine();
                }
            }
        });
        
        edt.getSemaineSalles().addActionListener((ActionEvent event) -> {
            if(edt.getVueSalles().getSelectedItem() == "en grille") {
                System.out.println("grille");
                majEdtSallesParSemaine();
            }
            else {
                System.out.println("liste");
                majListeSallesParSemaine();
            }
        });
        
        //COMBOBOX DE RECHERCHE POUR LES REFERENTS ET ADMIN
        edt.getRechercheCours().addActionListener((ActionEvent event) -> {
            String recherche = edt.getRechercheCours().getSelectedItem().toString();
            System.out.println("recherche : " + recherche);
            if (!recherche.equals("Veuillez sélectionner")) {
                //Si tu cliques dans le menu user, c'est que tu recherches par user et non par groupe
                edt.getGroupesCours().setSelectedItem("Groupes"); //Donc le menu groupe, on le remet par défaut
                //Si c'est un enseignant ou un étudiant, ce bouton existe, mais il n'a pas accès donc c'est bon
                edt.getRecherchePromo().setSelectedItem("Promos");
                if(edt.getVueCours().getSelectedItem() == "en grille") {
                    System.out.println("grille");
                    majEdtCoursParSemaine();
                }
                else { 
                    System.out.println("liste");
                    majListeCoursParSemaine();
                }
            }
        });
        
        edt.getRechercheRecapCours().addActionListener((ActionEvent event) -> {
            String recherche = edt.getRechercheRecapCours().getSelectedItem().toString();
            System.out.println("recherche : " + recherche);
            if (!recherche.equals("Veuillez sélectionner")) {
                controle.majRecapRecherche(recherche);
            }
        });
        
        edt.getRecherchePromo().addActionListener((ActionEvent event) -> {
            String recherche = edt.getRecherchePromo().getSelectedItem().toString();
            System.out.println("recherche : " + recherche);
            if (!recherche.equals("Promos")) {
                edt.getRechercheCours().setSelectedItem("Veuillez sélectionner");
                edt.getGroupesCours().setSelectedItem("Groupes");
                if(edt.getVueCours().getSelectedItem() == "en liste") {
                    System.out.println("liste");
                    majListePromoParSemaine();
                }
           } 
        });
        //Menu déroulant de salles
        edt.getRechercheSalles().addActionListener((ActionEvent event) -> {
            String recherche = edt.getRechercheSalles().getSelectedItem().toString();
            System.out.println("recherche: " + recherche);
            if (!recherche.equals("Veuillez sélectionner")) {
                if(edt.getVueSalles().getSelectedItem() == "en grille") {
                    System.out.println("grille");
                    majEdtSallesParSemaine();
                }
                else {
                    System.out.println("liste");
                    majListeSallesParSemaine();
                }
            } 
        });
        
        edt.getGroupesCours().addActionListener((ActionEvent event) -> {  
            String recherche = edt.getGroupesCours().getSelectedItem().toString();
            if (!recherche.equals("Groupes")) {
                //Si tu cliques dans le menu groupe, c'est que tu recherches par groupe et non par user
                edt.getRechercheCours().setSelectedItem("Veuillez sélectionner"); //Donc le menu user, on le remet par défaut
                edt.getRecherchePromo().setSelectedItem("Promos");
                if(edt.getVueCours().getSelectedItem() == "en grille") {
                    System.out.println("grille");
                    majEdtGroupeCoursParSemaine();
                }
                else {
                    System.out.println("liste");
                    majListeGroupeCoursParSemaine();
                }
            }
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
        
        edt.getRechercheBoutonCours().addActionListener((ActionEvent event) -> {
            String recherche = edt.getRechercheBarreCours().getText();
            String maRecherche = controle.rechercheUtilisateur(recherche);
            if(maRecherche != null)
            {
                edt.getRechercheCours().setSelectedItem(maRecherche);
            }
             else
                System.out.println("Aucun utilisateur trouvé");
            
        });
        
        edt.getRechercheBoutonRecapCours().addActionListener((ActionEvent event) -> {
            Boolean grille = false;
            
            String recherche = edt.getRechercheBarreRecapCours().getText();
            controle.majRecapRechercheBarre(recherche);
        });
        
        //Bouton de recherche salles
        edt.getRechercheBoutonSalles().addActionListener((ActionEvent event) -> {
            String recherche = edt.getRechercheBarreSalles().getText();
            String maRecherche = controle.rechercheSalle(recherche);
            if(maRecherche != null)
                edt.getRechercheSalles().setSelectedItem(maRecherche); //C'est l'écouteur qui va update les séances de la salle
            else
                System.out.println("Aucune salle trouvé");
        });
    }
    
    /**
     * initialisation des listener SEULEMENT pour l'admin
     */
    public void initListenersForAdmin(){ 
    //L'onglet SP est initialisé dans edt que quand l'admin se connecte, iniListeners n'accepte pas mes Listeners 
    //car c'est avant la connection et donc l'onglet SP est vide (= pas de JMachin encore) ;'(...
        edt.getBtnValider().addActionListener((ActionEvent event)->{
            if(!edt.getInfosAddSeance().isEmpty())
                controle.demandeAddSeance(edt.getInfosAddSeance());
        });
     
        edt.getBtnValider2().addActionListener((ActionEvent event)->{
            if(!edt.getListeSeances().isSelectionEmpty() && (edt.getInfosModifSeance().size() != 0) ){
                //Extraction de l'id de la séance
                String selected = edt.getListeSeances().getSelectedValue().toString();
                int start = selected.indexOf("°");
                int end = selected.indexOf(" ", start);
                int idSeance = Integer.parseInt(selected.substring(start+1, end));
                controle.demandeModifSeance(idSeance,edt.getInfosModifSeance());
            }
        });
        
        edt.getBtnValider3().addActionListener((ActionEvent event)->{
            String matiereToBeAdded = edt.getIntitule().getText();
            if(!matiereToBeAdded.isEmpty())
                controle.createMatiere(matiereToBeAdded);
            else
                System.out.println("Rien a été saisie");
        });
        
        edt.getListeSeances().addListSelectionListener((ListSelectionEvent lse) -> {
            if(!lse.getValueIsAdjusting()){
                if(!edt.getListeSeances().isSelectionEmpty())
                {
                    JList source = (JList)lse.getSource();
                        String selected = source.getSelectedValue().toString();
                        //Extraction de l'id de la séance
                        int start = selected.indexOf("°");
                        int end = selected.indexOf(" ", start);
                        int idSeance = Integer.parseInt(selected.substring(start+1, end));
                        edt.dataToBeSelectedByDefault(controle.demandeInfosSelectedSeance(idSeance));
                }
            }
        });
    }
    
    /**
     * intialisation des graphes dans Home
     */
    public void initGraphes() {
        controle.creationGraphe(connexion.getEmail(), connexion.getPassword());
    }
    
    /**
     * connexion
     * si l'utilisateur a saisi son email et son mdp et que cela correspond à la BDD, on passe à la suite
     * @param email
     * @param password
     */
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
            remplirComboSalles();
            remplirComboPromos();
            remplirDonneesAdmin(); //Juste pour l'admin
            initGraphes(); //Graphes dans Home
            //majEdtCoursParSemaine();
            edt.getGrilleCours().setVisible(true); //Affichage en grille par defaut
            controle.seancesRecap(connexion.getEmail(), connexion.getPassword()); //Set edt de l'utilisateur courant (par défaut)
            edt.setEdtHome();
            majEdtJour();
            edt.getGrilleSalles().setVisible(true);
        }
    }
    
    /**
     * rempli la JComboBox avec les utilisateurs de la BDD
     * @param email
     * @param password
     */
    public void remplirComboRecherche(String email, String password) {
        ArrayList<String> ttLeMonde = controle.allUsersToStrings();
        edt.setRechercheCours(ttLeMonde); 
        
        String utilisateur = controle.utilisateurCourant(email, password);
        edt.getRechercheCours().setSelectedItem(utilisateur);
        
        edt.setRechercheRecapCours(ttLeMonde); 
        edt.getRechercheRecapCours().setSelectedItem(utilisateur);
    }
    
    /**
     * rempli la combobox avec les groupes de la BDD
     */
    public void remplirComboGroupes() {
        ArrayList<String> ttLesGroupes = controle.allGroupsToStrings();
        edt.setGroupesCours(ttLesGroupes); 
    }
    
    /**
     * rempli la combobox avec les promos de la BDD
     */
    public void remplirComboPromos() {
        ArrayList<String> ttesLesPromos = controle.allPromosToStrings();
        edt.setPromosCours(ttesLesPromos); 
    }
    
    /**
     * remplissage des différents container de l'onglet Service Planification
     */
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
    
    /**
     * rempli la combobox avec les types de cours de la BDD
     */
    public void remplirComboTypes(){
        ArrayList<String> ttLesTypes = controle.allTypeToStrings();
        edt.setTypes(ttLesTypes);
    }
    
    /**
     * rempli la combobox avec les cours de la BDD
     */
    public void remplirComboCours(){
        ArrayList<String> ttLesCours = controle.allCoursToStrings();
        edt.setCours(ttLesCours);
    }
    
    /**
     * rempli la combobox avec les salles de la BDD
     */
    public void remplirComboSalles(){
        ArrayList<String> ttesLesSalles = controle.allSallesToStrings();
        edt.setRechercheSalles(ttesLesSalles);
        edt.getRechercheSalles().setSelectedIndex(0);
    }
    
    /**
     * rempli la combobox avec les salles de la BDD
     */
    public void remplirListSalles(){
        ArrayList<String> ttLesSalles = controle.allSallesToStrings();
        edt.setSalles(ttLesSalles);
    }
    
    /**
     * rempli la combobox avec les groupes de la BDD
     */
    public void remplirListGroupes(){//Je ne sais pas s'il y a myn de fusionner avec remplirComboGroupes
        ArrayList<String> ttLesGrps = controle.allGroupsToStrings();
        edt.setGroupes(ttLesGrps);
    }
    
    /**
     * rempli la combobox avec les enseignants de la BDD
     */
    public void remplirListEnseignants(){
        ArrayList<String> ttLesEnseignants = controle.allEnseignantsToStrings();
        edt.setEnseignants(ttLesEnseignants);
    }
    
    /**
     * rempli la combobox avec les seances de la BDD
     */
    public void remplirListSeances(){
        ArrayList<String> ttLesSeances = controle.allSeancesToStrings();
        edt.setSeances(ttLesSeances);
    }
    
    /**
     * @return l'année scolaire en cours (retourne une string)
     */
    public String calculAnneeScolaire() { //Pour affichage dans titre de la frame
        int annee = Calendar.getInstance().get(Calendar.YEAR); //Année courante
        if(Calendar.getInstance().get(Calendar.MONTH)+1 >= 9 && Calendar.getInstance().get(Calendar.MONTH)+1 <= 12) //Entre septembre et décembre
            return annee + "/" + (annee+1);
        return (annee-1) + "/" + annee;
    }
    
    /**
     * rend visible certains composants selon le profil
     */
    public void rendreVisible() {
        edt.getRechercheCours().setVisible(true);
        edt.getRechercheBarreCours().setVisible(true);
        edt.getRechercheBoutonCours().setVisible(true);
        edt.getGroupesCours().setVisible(true);
        edt.getRecherchePromo().setVisible(true);
        
        edt.getRechercheRecapCours().setVisible(true);
        edt.getRechercheBarreRecapCours().setVisible(true);
        edt.getRechercheBoutonRecapCours().setVisible(true);
    }
    
    /**
     * rend invisible certains composants selon le profil
     */
    public void rendreInvisible() {
        edt.getRechercheCours().setVisible(false);
        edt.getRechercheBarreCours().setVisible(false);
        edt.getRechercheBoutonCours().setVisible(false);
        edt.getGroupesCours().setVisible(false);
        edt.getRecherchePromo().setVisible(false);
        
        edt.getRechercheRecapCours().setVisible(false);
        edt.getRechercheBarreRecapCours().setVisible(false);
        edt.getRechercheBoutonRecapCours().setVisible(false);
    }

    /**
     * MAJ Edt de la personne contenue dans la JComboBox utilisateurs
     * par defaut utilisateur courant
     * un utilisateur ne peut pas le modifier sauf s'il est référent
     * @param semaine
     */
    public void majEdt(int semaine) {
        String user = edt.getRechercheCours().getSelectedItem().toString();
        //System.out.println("\njcombobox " + edt.getRechercheCours().getSelectedItem().toString());
           
        //Récupérer le nom et le nom de famille
        String prenom = new String();
        String nom = new String();

        int pos = user.indexOf(' ');
        //System.out.println(user + " position " + pos);

        prenom = user.substring(0, pos);
        nom = user.substring(pos+1);

        controle.majSeancesEdt(semaine, prenom, nom);
    }
    
    /**
     * MAJ Edt de la personne contenue dans la JComboBox utilisateurs
     * par defaut utilisateur courant
     * un utilisateur ne peut pas le modifier sauf s'il est référent
     * @param semaine
     */
    public void majEdtPromo(int semaine) {
        String promo = edt.getRecherchePromo().getSelectedItem().toString();
        System.out.println("\njcombobox " + edt.getRechercheCours().getSelectedItem().toString());
        controle.majSeancesPromo(semaine, promo); 
    }
    
    /**
     * MAJ Edt de la personne contenue dans la JComboBox utilisateurs
     * par defaut utilisateur courant
     * un utilisateur ne peut pas le modifier sauf s'il est référent
     * @param semaine
     */
    public void majSalles(int semaine) {
        String infos = edt.getRechercheSalles().getSelectedItem().toString();
        
        controle.majSeancesSalles(semaine, infos);
    }
    
    /**
     * MAJ Edt de la personne contenue dans la JComboBox utilisateurs
     * par defaut utilisateur courant
     * un utilisateur ne peut pas le modifier sauf s'il est référent
     * @param semaine
     */
    public void majListe(int semaine) {
        String user = edt.getRechercheCours().getSelectedItem().toString();
        //System.out.println("\njcombobox " + edt.getRechercheCours().getSelectedItem().toString());
           
        //Récupérer le nom et le nom de famille
        String prenom = new String();
        String nom = new String();

        int pos = user.indexOf(' ');
        //System.out.println(user + " position " + pos);

        prenom = user.substring(0, pos);
        nom = user.substring(pos+1);

        controle.majSeancesListe(semaine, prenom, nom);
    }
    
    /**
     * MAJ Edt de la personne contenue dans la JComboBox utilisateurs
     * par defaut utilisateur courant
     * un utilisateur ne peut pas le modifier sauf s'il est référent
     * @param semaine
     */
    public void majListeSalles(int semaine) {
        String salles = edt.getRechercheSalles().getSelectedItem().toString();
        System.out.println("\njcombobox " + edt.getRechercheSalles().getSelectedItem().toString());
               
        controle.majSallesListe(semaine, salles);
    }
    
    /**
     * MAJ Edt quand un referent cherche un groupe
     * @param semaine
     */
    public void majEdtGroupe(int semaine) {
        String recherche = edt.getGroupesCours().getSelectedItem().toString();
        if(!recherche.equals("Groupes")) 
            controle.majSeancesEdt(semaine, recherche);
    }
    /**
     * MAJ liste EDT quand un referent, admin cherche un groupe
     */
    public void majListeGroupe(){
        String recherche = edt.getGroupesCours().getSelectedItem().toString();
        if(!recherche.equals("Groupes")) 
            controle.majSeancesListe(Integer.parseInt(edt.getSemaineCours().getSelectedItem().toString()), recherche);
    }
        
    /**
     * MAJ Edt quand on change le jour dans Home
     */
    public void majEdtJour() {
        //Récup de la date du JSpinner
        String date = DateFormat.getDateInstance(1).format(edt.getDateHome().getValue()) ;
        //System.out.println(date);
 
        controle.seancesEdt(date, connexion.getEmail(), connexion.getPassword());
    }
    
    /**
     * recup semaine select puis maj edt Cours
     */
    public void majEdtCoursParSemaine() {
        //On récupère la semaine sélectionnée
        String semaine = edt.getSemaineCours().getSelectedItem().toString();
        if (semaine.equals("Semaine")) {
            edt.setEdtCours(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
            majEdt(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
        }

        else {
            //System.out.println("Semaine selectionnee : " + semaine);
            edt.setEdtCours(Integer.parseInt(semaine));
            majEdt(Integer.parseInt(semaine));
        }
    }
    
    /**
     * recup semaine select puis maj edt Cours
     */
    public void majListePromoParSemaine() {
        //On récupère la semaine sélectionnée
        String semaine = edt.getSemaineCours().getSelectedItem().toString();
        if (semaine.equals("Semaine")) {
            edt.setListeCours(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
            majEdtPromo(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
        }

        else {
            //System.out.println("Semaine selectionnee : " + semaine);
            edt.setListeCours(Integer.parseInt(semaine));
            majEdtPromo(Integer.parseInt(semaine));
        }
    }
    
    /**
     * recup semaine select puis maj edt Cours
     */
    public void majListeCoursParSemaine() {
        //On récupère la semaine sélectionnée
        String semaine = edt.getSemaineCours().getSelectedItem().toString();
        if (semaine.equals("Semaine")) {
            edt.setListeCours(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
            majListe(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
        }

        else {
            //System.out.println("Semaine selectionnee : " + semaine);
            edt.setListeCours(Integer.parseInt(semaine));
            majListe(Integer.parseInt(semaine));
        }
    }
    
    public void majListeSallesParSemaine() {
        //On récupère la semaine sélectionnée
        String semaine = edt.getSemaineSalles().getSelectedItem().toString();
        if (semaine.equals("Semaine")) {
            edt.setListeSalles(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
            majListeSalles(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
        }

        else {
            //System.out.println("Semaine selectionnee : " + semaine);
            edt.setListeSalles(Integer.parseInt(semaine));
            majListeSalles(Integer.parseInt(semaine));
        }
    }
    
    /**
     * recup semaine select puis maj edt Salles
     */
    public void majEdtSallesParSemaine() {
        //On récupère la semaine sélectionnée
        String semaine = edt.getSemaineSalles().getSelectedItem().toString();
        if (semaine.equals("Semaine")) {
            edt.setEdtSalles(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
            majSalles(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
        }
        else {
            edt.setEdtSalles(Integer.parseInt(semaine));
            majSalles(Integer.parseInt(semaine));
        }
    }
    
    /**
     * recup semaine select puis maj edt pour un groupe (fonction référent,admin)
     */
    public void majEdtGroupeCoursParSemaine() {
        //On récupère la semaine sélectionnée
        String semaine = edt.getSemaineCours().getSelectedItem().toString();
        if (semaine.equals("Semaine")) {
            edt.setEdtCours(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
            majEdtGroupe(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
        }

        else {
            edt.setEdtCours(Integer.parseInt(semaine));
            majEdtGroupe(Integer.parseInt(semaine));
        }
    }
    /**
     * recup semaine select puis maj liste edt pour un groupe (fonction référent, admin)
     */
    public void majListeGroupeCoursParSemaine() {
        //On récupère la semaine sélectionnée
        String semaine = edt.getSemaineCours().getSelectedItem().toString();
        if (semaine.equals("Semaine")) {
            edt.setListeCours(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
            majListeGroupe();
        }

        else {
            edt.setListeCours(Integer.parseInt(semaine));
            majListeGroupe();
        }
    }
    
    /**
     * ajouter les graphes de reporting
     * @param c
     * @param t
     */
    public void ajouterGraphes(ArrayList<ChartPanel> c, ArrayList<ChartPanel> t) {
        edt.ajouterGraphes(c, t);
    }
    /**
     * Retourne les infos unique de la personne qui est connecté
     * @return 
     */
    public ArrayList<String> recupMesInfos()
    {
        ArrayList<String> mesInfos = new ArrayList<>();
        mesInfos.add(connexion.getEmail());
        mesInfos.add(connexion.getPassword());
        return mesInfos;
    }
}

