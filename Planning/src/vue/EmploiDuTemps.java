package vue;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.GroupLayout.*;
import javax.swing.LayoutStyle.*;
import javax.swing.table.*;

public class EmploiDuTemps extends JTabbedPane implements ActionListener {
    //Variables onglet Home                     
    private JPanel container1Home;
    private JPanel container2Home;
    private JTextField cursusTextHome;
    private JSpinner dateHome;
    private JTextField edtTextHome;
    private JButton linkCoursHome;
    private JSplitPane paneHome;
    private JScrollPane scrollPaneHome;
    private JTable tabCoursHome;
    //Onglet Cours
    private JPanel paneCours;
    private JComboBox<String> vueEdt;
    private JTextField rechercheBarreCours;
    private JButton rechercheBoutonCours;
    private JComboBox<String> rechercheCours;
    private JScrollPane scrollPaneCours;
    private JScrollPane scrollPaneRecapCours;
    private JComboBox<String> semaineCours;
    private JTable tabCours;
    private JTable tabRecapCours;
    private JTabbedPane tabbedPaneCours;
    //Onglet Salles
    private JPanel paneSalle;
    private JTextField rechercheBarreSalle;
    private JButton rechercheBoutonSalle;
    private JComboBox<String> rechercheSalle;
    private JScrollPane scrollPaneRecapSalle;
    private JScrollPane scrollPaneSalle;
    private JComboBox<String> semaineSalle;
    private JTable tabLibresSalle;
    private JTable tabSalle;
    private JTabbedPane tabbedPaneSalle;
    private JComboBox<String> vueSalle;
    //Onglet Service planification
    private JLabel ajouterTextSP;
    private JButton boutonSupp;
    private JPanel containerSP;
    private JLabel droitSP;
    private JLabel droitTextSP;
    private JTextField emailAddSP;
    private JLabel emailSP;
    private JLabel emailTextSP;
    private JRadioButton enseignantAddSP;
    private JRadioButton enseignantSP;
    private JRadioButton etudiantAddSP;
    private JRadioButton etudiantSP;
    private ButtonGroup groupeDroitModifSP;
    private ButtonGroup groupeDroitSP;
    private JList<String> listeUtilisateursSP;
    private JLabel modifierTextSP;
    private JTextField nomAddSP;
    private JLabel nomSP;
    private JLabel nomTextSP;
    private JLabel numeroSP;
    private JPanel paneGererSP;
    private JScrollPane paneGererUtilisateursSP;
    private JPanel paneListeSP;
    private JPanel paneModificationSP;
    private JPanel paneAjoutSP;
    private JTextField prenomAddSP1;
    private JLabel prenomSP;
    private JLabel prenomTextSP;
    private JRadioButton referentAddSP;
    private JRadioButton referentSP;
    private JScrollPane scrollListeSP;
    private JComboBox<String> selectionProfilSP;
    private JSplitPane splitGererUtilisateursSP;
    private JTabbedPane tabbedPaneServicePlanification;
    private JButton validerSP;
    private JScrollPane paneGererCoursSP;
    private JScrollPane paneGererSiteSP;

    /*Constructeur*/
    public EmploiDuTemps() {
        initialisation();
    }

    public void initialisation() {
        //Home
        paneHome = new JSplitPane(); //Panneau divisé en deux
        container1Home = new JPanel(); //Panneau
        edtTextHome = new JTextField("Emploi du temps"); //Texte
        dateHome = new JSpinner();
        linkCoursHome = new JButton(); //Bouton
        scrollPaneHome = new JScrollPane(); //Panneau avec scroll vertical
        tabCoursHome = new JTable(); //Tableau
        container2Home = new JPanel(); 
        cursusTextHome = new JTextField("Mon cursus"); 
        //Cours
        tabbedPaneCours = new JTabbedPane(); //Panneau avec plusieurs onglets
        paneCours = new JPanel();
        vueEdt = new JComboBox<>(); //Menu déroulant
        rechercheCours = new JComboBox<>();
        rechercheBarreCours = new JTextField(); 
        rechercheBoutonCours = new JButton();
        semaineCours = new JComboBox<>(); 
        scrollPaneCours = new JScrollPane(); 
        tabCours = new JTable(); 
        scrollPaneRecapCours = new JScrollPane(); 
        tabRecapCours = new JTable(); 
        //Salle
        tabbedPaneSalle = new JTabbedPane(); 
        paneSalle = new JPanel(); 
        vueSalle = new JComboBox<>(); 
        rechercheSalle = new JComboBox<>(); 
        rechercheBarreSalle = new JTextField(); 
        rechercheBoutonSalle = new JButton();
        semaineSalle = new JComboBox<>(); 
        scrollPaneSalle = new JScrollPane(); 
        tabSalle = new JTable(); 
        scrollPaneRecapSalle = new JScrollPane(); 
        tabLibresSalle = new JTable(); 
        //Service planification
        groupeDroitSP = new ButtonGroup();
        groupeDroitModifSP = new ButtonGroup();
        tabbedPaneServicePlanification = new JTabbedPane();
        paneGererUtilisateursSP = new JScrollPane();
        containerSP = new JPanel();
        splitGererUtilisateursSP = new JSplitPane();
        paneListeSP = new JPanel();
        scrollListeSP = new JScrollPane();
        listeUtilisateursSP = new JList<>();
        paneGererSP = new JPanel();
        paneModificationSP = new JPanel();
        boutonSupp = new JButton("Supprimer");
        selectionProfilSP = new JComboBox<>();
        paneAjoutSP = new JPanel();
        emailTextSP = new JLabel("Adresse mail :");
        nomTextSP = new JLabel  ("Nom :      ");
        prenomTextSP = new JLabel("Prenom :     ");
        droitTextSP = new JLabel("Droit d'accès :");
        referentAddSP = new JRadioButton("Référent pédagogique");
        enseignantAddSP = new JRadioButton("Enseignant");
        etudiantAddSP = new JRadioButton("Etudiant");
        emailAddSP = new JTextField();
        nomAddSP = new JTextField();
        prenomAddSP1 = new JTextField();
        validerSP = new JButton("Valider");
        modifierTextSP = new JLabel("Modifier les utilisateurs");
        emailSP = new JLabel("Adresse mail :        [email]");
        nomSP = new JLabel("Nom :                        [NOM]");
        prenomSP = new JLabel("Prénom :                  [Prénom]");
        droitSP = new JLabel("Droit d'accès :");
        referentSP = new JRadioButton("Référent pédagogique");
        enseignantSP = new JRadioButton("Enseignant");
        etudiantSP = new JRadioButton("Etudiant");
        numeroSP = new JLabel("Numéro étudiant : [numéro]");
        ajouterTextSP = new JLabel("Ajouter un utilisateur");

        /*----------------------------------------------------------HOME----------------------------------------------------------*/
        edtTextHome.setEditable(false); //L'utilisateur ne peut pas éditer le texte
        edtTextHome.setBorder(null); //Pas de bordure

        dateHome.setModel(new SpinnerDateModel()); //Contient une date
        dateHome.setBorder(null); 

        scrollPaneHome.setBackground(new Color(255, 255, 255)); //Blanc
        scrollPaneHome.setForeground(new Color(255, 255, 255));
        scrollPaneHome.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); //Scrollbar toujours présente même quand pas utile

        tabCoursHome.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Horaires", "Cours du jour"})); //Contenu = Objet, entêtes = String
        tabCoursHome.getTableHeader().setReorderingAllowed(false); //A TESTER
        
        scrollPaneHome.setViewportView(tabCoursHome); //Rend le tableau visible
        
        linkCoursHome.addActionListener(this); //Ajout d'un lien vers un des onglet de JTabbedPane sur un JButton
        linkCoursHome.setIcon(new ImageIcon("images\\icon_redimensionner.png")); //Ajout d'une image dans le bouton

        GroupLayout c1 = new GroupLayout(container1Home); //Plusieurs éléments dans un container global --> gérer les alignements
        container1Home.setLayout(c1);
        c1.setHorizontalGroup(
                c1.createParallelGroup(Alignment.LEADING)
                        .addGroup(c1.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(edtTextHome, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(dateHome, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                                .addComponent(linkCoursHome, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                        .addComponent(scrollPaneHome, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        c1.setVerticalGroup(
                c1.createParallelGroup(Alignment.LEADING)
                        .addGroup(c1.createSequentialGroup()
                                .addGroup(c1.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(edtTextHome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dateHome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(linkCoursHome, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(scrollPaneHome, GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                                .addGap(0, 0, 0))
        );

        paneHome.setLeftComponent(container1Home); //Container gauche

        cursusTextHome.setEditable(false);
        cursusTextHome.setBackground(new Color(255, 255, 255));
        cursusTextHome.setHorizontalAlignment(JTextField.CENTER); //Texte centré
        cursusTextHome.setBorder(null);

        GroupLayout c2 = new GroupLayout(container2Home);
        container2Home.setLayout(c2);
        c2.setHorizontalGroup(
                c2.createParallelGroup(Alignment.LEADING)
                        .addComponent(cursusTextHome, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        c2.setVerticalGroup(
                c2.createParallelGroup(Alignment.LEADING)
                        .addGroup(c2.createSequentialGroup()
                                .addComponent(cursusTextHome, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(464, Short.MAX_VALUE))
        );

        paneHome.setRightComponent(container2Home); //Container droit

        this.addTab("Home", paneHome); //On ajoute a EmpoiDuTemps l'onglet Home
        /*----------------------------------------------------------HOME----------------------------------------------------------*/

        /*----------------------------------------------------------COURS---------------------------------------------------------*/
        vueEdt.setModel(new DefaultComboBoxModel<>(new String[]{"en grille", "en liste"})); //Ajout des items au menu déroulant

        rechercheCours.setModel(new DefaultComboBoxModel<>(new String[]{"[NOM Prénom]", "[NOM Prénom]", "[NOM Prénom]"}));

        rechercheBoutonCours.setIcon(new ImageIcon("images\\icon_recherche.png"));
        
        semaineCours.setModel(new DefaultComboBoxModel<>(new String[]{"Semaine"})); 
        for(int i = 1; i < 53; i++) {
            semaineCours.addItem(String.valueOf(i));
        }
        
        scrollPaneCours.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tabCours.setModel(new DefaultTableModel(new Object[][]{}, new String[]{ "Horaires", "[Date]", "[Date]", "[Date]", "[Date]", "[Date]", "[Date]"}));
        tabCours.getTableHeader().setReorderingAllowed(false); //A TESTER
        
        scrollPaneCours.setViewportView(tabCours);
        
        GroupLayout c3 = new GroupLayout(paneCours);
        paneCours.setLayout(c3);
        c3.setHorizontalGroup(
                c3.createParallelGroup(Alignment.LEADING)
                        .addGroup(c3.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(c3.createParallelGroup(Alignment.LEADING)
                                        .addComponent(scrollPaneCours)
                                        .addGroup(c3.createSequentialGroup()
                                                .addComponent(vueEdt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(rechercheCours, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(rechercheBarreCours, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(rechercheBoutonCours, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                                                .addComponent(semaineCours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        c3.setVerticalGroup(
                c3.createParallelGroup(Alignment.LEADING)
                        .addGroup(c3.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(c3.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(vueEdt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rechercheCours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rechercheBarreCours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rechercheBoutonCours, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(semaineCours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(scrollPaneCours, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                                .addContainerGap())
        );

        tabbedPaneCours.addTab("Emploi du temps", paneCours);

        scrollPaneRecapCours.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tabRecapCours.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Matière", "Première séance", "Dernière séance", "Durée", "Nb."}));
        tabRecapCours.getTableHeader().setReorderingAllowed(false);
        scrollPaneRecapCours.setViewportView(tabRecapCours);

        tabbedPaneCours.addTab("Récapitulatif cours", scrollPaneRecapCours);

        this.addTab("Cours", tabbedPaneCours);
        /*----------------------------------------------------------COURS---------------------------------------------------------*/

        /*---------------------------------------------------------SALLES---------------------------------------------------------*/
        vueSalle.setModel(new DefaultComboBoxModel<>(new String[]{"en grille", "en liste"}));

        rechercheSalle.setModel(new DefaultComboBoxModel<>(new String[]{"[Bat. N°]", "[Bat. N°]", "[Bat. N°]"}));

        semaineSalle.setModel(new DefaultComboBoxModel<>(new String[]{"Semaine"})); 
        for(int i = 1; i < 53; i++) {
            semaineSalle.addItem(String.valueOf(i));
        }

        scrollPaneSalle.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tabSalle.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Horaires", "[Date]", "[Date]", "[Date]", "[Date]", "[Date]", "[Date]"}));
        tabSalle.getTableHeader().setReorderingAllowed(false);
        
        scrollPaneSalle.setViewportView(tabSalle);
        
        rechercheBoutonSalle.setIcon(new ImageIcon("images\\icon_recherche.png"));
        
        GroupLayout c4 = new GroupLayout(paneSalle);
        paneSalle.setLayout(c4);
        c4.setHorizontalGroup(
                c4.createParallelGroup(Alignment.LEADING)
                        .addGroup(c4.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(c4.createParallelGroup(Alignment.LEADING)
                                        .addComponent(scrollPaneSalle)
                                        .addGroup(c4.createSequentialGroup()
                                                .addComponent(vueSalle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(rechercheSalle, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(rechercheBarreSalle, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(rechercheBoutonSalle, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                                                .addComponent(semaineSalle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        c4.setVerticalGroup(
                c4.createParallelGroup(Alignment.LEADING)
                        .addGroup(c4.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(c4.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(vueSalle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rechercheSalle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rechercheBarreSalle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rechercheBoutonSalle, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(semaineSalle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(scrollPaneSalle, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                                .addContainerGap())
        );

        tabbedPaneSalle.addTab("Emploi du temps", paneSalle);

        scrollPaneRecapSalle.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tabLibresSalle.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Site", "Date", "Durée", "Capacité"}));
        scrollPaneRecapSalle.setViewportView(tabLibresSalle);

        tabbedPaneSalle.addTab("Salles libres", scrollPaneRecapSalle);

        this.addTab("Salles", tabbedPaneSalle);
        /*---------------------------------------------------------SALLES---------------------------------------------------------*/
    
        /*--------------------------------------------------SERVICE PLANIFICATION-------------------------------------------------*/
        splitGererUtilisateursSP.setResizeWeight(0.5); //La séparation du split pane est au milieu
        splitGererUtilisateursSP.setOneTouchExpandable(true); //Petites flècles pour fermer un coté

        /*setListData(Vector<? extends E> listData)
        Constructs a read-only ListModel from a Vector and calls setModel with this model.*/
        Vector<String> listData = new Vector();
        for(int i=1;i<101;i++)
            listData.add("Eleve "+i);
        listeUtilisateursSP.setListData(listData);
        
        scrollListeSP.setViewportView(listeUtilisateursSP);

        GroupLayout c5 = new GroupLayout(paneListeSP);
        paneListeSP.setLayout(c5);
        c5.setHorizontalGroup(
            c5.createParallelGroup(Alignment.LEADING)
            .addComponent(scrollListeSP, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
        );
        c5.setVerticalGroup(
            c5.createParallelGroup(Alignment.LEADING)
            .addGroup(c5.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(scrollListeSP, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        splitGererUtilisateursSP.setRightComponent(paneListeSP);
        groupeDroitSP.add(referentSP);
        groupeDroitSP.add(enseignantSP);
        groupeDroitSP.add(etudiantSP);
  
        selectionProfilSP.setModel(new DefaultComboBoxModel<>(new String[] { "Veuillez sélectionner", "Référent", "Enseignant", "Etudiant" }));
    
        GroupLayout c6 = new GroupLayout(paneModificationSP);
        paneModificationSP.setLayout(c6);
        c6.setHorizontalGroup(
            c6.createParallelGroup(Alignment.LEADING)
            .addGroup(c6.createSequentialGroup()
                .addContainerGap()
                .addGroup(c6.createParallelGroup(Alignment.LEADING)
                    .addGroup(c6.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(boutonSupp))
                    .addGroup(c6.createParallelGroup(Alignment.TRAILING, false)
                        .addComponent(prenomSP, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                        .addComponent(nomSP, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(emailSP, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(c6.createSequentialGroup()
                        .addComponent(droitSP)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(referentSP)
                        .addGap(0, 0, 0)
                        .addComponent(enseignantSP)
                        .addGap(0, 0, 0)
                        .addComponent(etudiantSP))
                    .addComponent(numeroSP, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifierTextSP, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectionProfilSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        c6.setVerticalGroup(
            c6.createParallelGroup(Alignment.LEADING)
            .addGroup(c6.createSequentialGroup()
                .addContainerGap()
                .addComponent(modifierTextSP)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(selectionProfilSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addComponent(emailSP)
                .addGap(13, 13, 13)
                .addComponent(nomSP)
                .addGap(13, 13, 13)
                .addComponent(prenomSP)
                .addGap(13, 13, 13)
                .addComponent(numeroSP)
                .addGap(13, 13, 13)
                .addGroup(c6.createParallelGroup(Alignment.BASELINE)
                    .addComponent(droitSP)
                    .addComponent(referentSP)
                    .addComponent(enseignantSP)
                    .addComponent(etudiantSP))
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addComponent(boutonSupp)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        groupeDroitModifSP.add(referentAddSP);
        groupeDroitModifSP.add(enseignantAddSP);
        groupeDroitModifSP.add(etudiantAddSP);

        GroupLayout c7 = new GroupLayout(paneAjoutSP);
        paneAjoutSP.setLayout(c7);
        c7.setHorizontalGroup(
            c7.createParallelGroup(Alignment.LEADING)
            .addGroup(c7.createSequentialGroup()
                .addGroup(c7.createParallelGroup(Alignment.LEADING)
                    .addGroup(c7.createParallelGroup(Alignment.LEADING, false)
                        .addGroup(c7.createSequentialGroup()
                            .addComponent(emailTextSP)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(emailAddSP))
                        .addGroup(c7.createSequentialGroup()
                            .addComponent(nomTextSP)
                            .addGap(43, 43, 43)
                            .addComponent(nomAddSP))
                        .addGroup(c7.createSequentialGroup()
                            .addComponent(prenomTextSP)
                            .addGap(28, 28, 28)
                            .addComponent(prenomAddSP1))
                        .addGroup(c7.createSequentialGroup()
                            .addComponent(droitTextSP)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(referentAddSP)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(enseignantAddSP)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(etudiantAddSP)))
                    .addComponent(ajouterTextSP))
                .addGap(0, 10, Short.MAX_VALUE))
            .addGroup(c7.createSequentialGroup()
                .addContainerGap()
                .addComponent(validerSP)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        c7.setVerticalGroup(
            c7.createParallelGroup(Alignment.LEADING)
            .addGroup(c7.createSequentialGroup()
                .addContainerGap()
                .addComponent(ajouterTextSP)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(c7.createParallelGroup(Alignment.BASELINE)
                    .addComponent(emailTextSP)
                    .addComponent(emailAddSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(c7.createParallelGroup(Alignment.BASELINE)
                    .addComponent(nomTextSP)
                    .addComponent(nomAddSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(c7.createParallelGroup(Alignment.BASELINE)
                    .addComponent(prenomTextSP)
                    .addComponent(prenomAddSP1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(c7.createParallelGroup(Alignment.BASELINE)
                    .addComponent(droitTextSP)
                    .addComponent(referentAddSP)
                    .addComponent(enseignantAddSP)
                    .addComponent(etudiantAddSP))
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addComponent(validerSP)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout c8 = new GroupLayout(paneGererSP);
        paneGererSP.setLayout(c8);
        c8.setHorizontalGroup(
            c8.createParallelGroup(Alignment.LEADING)
            .addGroup(c8.createSequentialGroup()
                .addComponent(paneModificationSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(c8.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneAjoutSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        c8.setVerticalGroup(
            c8.createParallelGroup(Alignment.LEADING)
            .addGroup(c8.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneModificationSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(paneAjoutSP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        splitGererUtilisateursSP.setLeftComponent(paneGererSP);

        GroupLayout c9 = new GroupLayout(containerSP);
        containerSP.setLayout(c9);
        c9.setHorizontalGroup(
            c9.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, c9.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(splitGererUtilisateursSP)
                .addGap(0, 0, 0))
        );
        c9.setVerticalGroup(
            c9.createParallelGroup(Alignment.LEADING)
            .addGroup(c9.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(splitGererUtilisateursSP, GroupLayout.PREFERRED_SIZE, 422, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        paneGererUtilisateursSP.setViewportView(containerSP);
        
        tabbedPaneServicePlanification.addTab("Gérer les utilisateurs", paneGererUtilisateursSP);
        
        tabbedPaneServicePlanification.addTab("Gérer les cours", paneGererCoursSP);
        tabbedPaneServicePlanification.addTab("Gérer le site", paneGererSiteSP); 
        
        this.addTab("Service planification", tabbedPaneServicePlanification); //On ajoute a EmpoiDuTemps l'onglet Service planification
        /*--------------------------------------------------SERVICE PLANIFICATION-------------------------------------------------*/
    }
    
    //Pour le lien du bouton linkCoursHome
    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.setSelectedIndex(1);
  }   
}
