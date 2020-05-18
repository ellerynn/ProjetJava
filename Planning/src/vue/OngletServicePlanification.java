package vue;

import java.util.Vector;
import javax.swing.*;

public class OngletServicePlanification extends JTabbedPane {
    //SP -> Gérer les utilisateurs
    private JSplitPane paneGererCoursSP;
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
    private JTextField nomAddSP;private JLabel nomSP;
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
    private JButton validerSP;
    private JButton boutonAddSP;
    private JButton boutonModSP;
    private JButton boutonSuppSP;
    //SP -> Gérer les cours
    private JLabel groupeLabelSP;
    private JTextField groupeTFSP;
    private JLabel groupeTextSP;
    private JPanel paneGroupeSP;
    private JTextField promoTFSP;
    private JLabel promoTextSP;
    private JComboBox<String> selectionnerGroupeSP;
    private JComboBox<String> selectionnerPromoSP;
    private JButton ajouterBoutonModifSP;
    private JButton ajouterBoutonSP;
    private JLabel ajouterCoursSP;
    private JLabel ajouterSeanceSP;
    private JRadioButton anuleeBoutonModifSP;
    private JPanel containerSP2;
    private JLabel coursModifSP;
    private JLabel coursSP;
    private JLabel dateHeureModifSP;
    private JLabel dateHeureSP;
    private JSpinner dateSeanceModifSP;
    private JSpinner dateSeanceSP;
    private JRadioButton enCoursModifSP;
    private JRadioButton enCoursSP;
    private JLabel etatModifSP;
    private JLabel etatSP;
    private ButtonGroup groupeEtatModifSP;
    private ButtonGroup groupeEtatSP;
    private JLabel intituleCoursSP;
    private JTextField intituleTextP;
    private JList<String> listeSeancesSP;
    private JLabel modifierSeanceSP;
    private JPanel paneAjouterCoursSP;
    private JPanel paneAjouterSeanceSP;
    private JPanel paneModifSeanceSP;
    private JScrollPane scrollListeSeancesSP;
    private JComboBox<String> selectionnerCoursModifSP;
    private JComboBox<String> selectionnerCoursSP;
    private JComboBox<String> selectionnerTypeModifSP;
    private JComboBox<String> selectionnerTypeSP;
    private JLabel typeModifSP;
    private JLabel typeSP;
    private JRadioButton valideeModifSP;
    private JRadioButton valideeSP;
    private JButton validerCoursSP;
    //SP -> Gérer le site
    private JPanel paneGererSiteSP;
    private JLabel ajouterSalleSP;
    private JLabel ajouterSiteSP;
    private JLabel capaciteAddSP;
    private JLabel capaciteSP;
    private JSpinner capaciteSpinnerSP;
    private JTabbedPane jTabbedPane1;
    private JList<String> listeSallesSP;
    private JButton modifierCapaciteSP;
    private JPanel paneAjouterSalleSP;
    private JPanel paneAjouterSiteSP;
    private JScrollPane paneListeSallesSP;
    private JPanel paneSitesSP;
    private JLabel salleAddSP;
    private JLabel salleSP;
    private JTextField salleTFSP;
    private JComboBox<String> selectionnerSiteSP;
    private JComboBox<String> selectionnerSiteSP2;
    private JLabel siteAddSP;
    private JLabel siteSP;
    private JLabel siteSalleSP;
    private JTextField siteTFSP;
    private JButton supprimerSalleSP;
    private JButton supprimerSiteSP;
    private JButton validerSalleSP;
    private JButton validerSiteSP;
    
    public OngletServicePlanification() {
        gererLesUtilisateurs();
        gererLesCours();
        gererLeSite();
    }
    
    public void gererLesCours() {
        paneGererCoursSP = new JSplitPane();
        scrollListeSeancesSP = new JScrollPane();
        listeSeancesSP = new JList<>();
        containerSP2 = new JPanel();
        paneAjouterSeanceSP = new JPanel();
        dateSeanceSP = new JSpinner();
        dateHeureSP = new JLabel("Date et heure :");
        etatSP = new JLabel("Etat :");
        enCoursSP = new JRadioButton("En cours de validation");
        valideeSP = new JRadioButton("Validée");
        coursSP = new JLabel("Cours :");
        selectionnerCoursSP = new JComboBox<>();
        typeSP = new JLabel("Type :");
        selectionnerTypeSP = new JComboBox<>();
        ajouterSeanceSP = new JLabel("Ajouter une seance");
        ajouterBoutonSP = new JButton("Valider");
        paneAjouterCoursSP = new JPanel();
        ajouterCoursSP = new JLabel("Ajouter un cours");
        intituleCoursSP = new JLabel("Intitulé du cours :");
        intituleTextP = new JTextField();
        validerCoursSP = new JButton("Valider");
        paneModifSeanceSP = new JPanel();
        modifierSeanceSP = new JLabel("Modifier une séance");
        dateHeureModifSP = new JLabel("Date et heure :");
        dateSeanceModifSP = new JSpinner();
        etatModifSP = new JLabel("Etat :");
        enCoursModifSP = new JRadioButton("En cours de validation");
        valideeModifSP = new JRadioButton("Validée");
        coursModifSP = new JLabel("Cours :");
        selectionnerCoursModifSP = new JComboBox<>();
        typeModifSP = new JLabel("Type :");
        selectionnerTypeModifSP = new JComboBox<>();
        anuleeBoutonModifSP = new JRadioButton("Annulée");
        ajouterBoutonModifSP = new JButton("Valider");
        groupeEtatSP = new ButtonGroup();
        groupeEtatModifSP = new ButtonGroup();
        
        paneGererCoursSP.setResizeWeight(0.5);

        remplirListe(listeSeancesSP);
        
        scrollListeSeancesSP.setViewportView(listeSeancesSP);

        paneGererCoursSP.setRightComponent(scrollListeSeancesSP);

        dateSeanceSP.setModel(new SpinnerDateModel(new java.util.Date(1598940000000L), new java.util.Date(1598940000000L), new java.util.Date(1627797600000L), java.util.Calendar.DAY_OF_MONTH));

        groupeEtatSP.add(enCoursSP);
        groupeEtatSP.add(valideeSP);

        remplirComboBox(selectionnerCoursSP, "Veuillez sélectionner", "Cours");
        remplirComboBoxType(selectionnerTypeSP);
        
        GroupLayout c11 = new GroupLayout(paneAjouterSeanceSP);
        paneAjouterSeanceSP.setLayout(c11);
        c11.setHorizontalGroup(
            c11.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c11.createSequentialGroup()
                .addComponent(ajouterSeanceSP)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(c11.createSequentialGroup()
                .addContainerGap()
                .addGroup(c11.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(c11.createSequentialGroup()
                        .addComponent(dateHeureSP)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateSeanceSP, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
                    .addGroup(c11.createSequentialGroup()
                        .addComponent(etatSP)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(enCoursSP)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valideeSP))
                    .addGroup(c11.createSequentialGroup()
                        .addGroup(c11.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(coursSP)
                            .addComponent(typeSP))
                        .addGap(18, 18, 18)
                        .addGroup(c11.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(selectionnerCoursSP, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(selectionnerTypeSP, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                        .addComponent(ajouterBoutonSP))))
        );
        c11.setVerticalGroup(
            c11.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c11.createSequentialGroup()
                .addComponent(ajouterSeanceSP)
                .addGap(12, 12, 12)
                .addGroup(c11.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(dateHeureSP)
                    .addComponent(dateSeanceSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(c11.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(etatSP)
                    .addComponent(enCoursSP)
                    .addComponent(valideeSP))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(c11.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(coursSP)
                    .addComponent(selectionnerCoursSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(c11.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(typeSP)
                    .addComponent(selectionnerTypeSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(ajouterBoutonSP))
                .addGap(10, 10, 10))
        );

        GroupLayout c12 = new GroupLayout(paneAjouterCoursSP);
        paneAjouterCoursSP.setLayout(c12);
        c12.setHorizontalGroup(
            c12.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c12.createSequentialGroup()
                .addComponent(ajouterCoursSP)
                .addGap(0, 0, 0))
            .addGroup(c12.createSequentialGroup()
                .addContainerGap()
                .addComponent(intituleCoursSP)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(intituleTextP, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(validerCoursSP)
                .addContainerGap())
        );
        c12.setVerticalGroup(
            c12.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c12.createSequentialGroup()
                .addComponent(ajouterCoursSP)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(c12.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(intituleCoursSP)
                    .addComponent(intituleTextP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(validerCoursSP))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        dateSeanceModifSP.setModel(new SpinnerDateModel(new java.util.Date(1598940000000L), new java.util.Date(1598940000000L), new java.util.Date(1627797600000L), java.util.Calendar.DAY_OF_MONTH));

        groupeEtatModifSP.add(enCoursModifSP);
        groupeEtatModifSP.add(valideeModifSP);

        remplirComboBox(selectionnerCoursModifSP, "Veuillez sélectionner", "Cours");
        remplirComboBoxType(selectionnerTypeModifSP);

        groupeEtatModifSP.add(anuleeBoutonModifSP);

        GroupLayout c13 = new GroupLayout(paneModifSeanceSP);
        paneModifSeanceSP.setLayout(c13);
        c13.setHorizontalGroup(
            c13.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c13.createSequentialGroup()
                .addGroup(c13.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(modifierSeanceSP)
                    .addGroup(c13.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(c13.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(c13.createSequentialGroup()
                                .addGroup(c13.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(coursModifSP)
                                    .addComponent(typeModifSP))
                                .addGap(18, 18, 18)
                                .addGroup(c13.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(selectionnerCoursModifSP, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(selectionnerTypeModifSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(c13.createSequentialGroup()
                                .addComponent(dateHeureModifSP)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dateSeanceModifSP, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
                            .addGroup(c13.createSequentialGroup()
                                .addComponent(etatModifSP)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(enCoursModifSP)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(valideeModifSP)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(anuleeBoutonModifSP)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(32, 32, 32)
                .addComponent(ajouterBoutonModifSP)
                .addContainerGap())
        );
        c13.setVerticalGroup(
            c13.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c13.createSequentialGroup()
                .addComponent(modifierSeanceSP)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(c13.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(dateHeureModifSP)
                    .addComponent(dateSeanceModifSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(c13.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(etatModifSP)
                    .addComponent(enCoursModifSP)
                    .addComponent(valideeModifSP)
                    .addComponent(anuleeBoutonModifSP))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(c13.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(coursModifSP)
                    .addComponent(selectionnerCoursModifSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(c13.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(typeModifSP)
                    .addComponent(selectionnerTypeModifSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(ajouterBoutonModifSP))
                .addGap(0, 9, Short.MAX_VALUE))
        );

        GroupLayout c14 = new GroupLayout(containerSP2);
        containerSP2.setLayout(c14);
        c14.setHorizontalGroup(
            c14.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c14.createSequentialGroup()
                .addContainerGap()
                .addGroup(c14.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(paneAjouterCoursSP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(c14.createSequentialGroup()
                        .addComponent(paneAjouterSeanceSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(paneModifSeanceSP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        c14.setVerticalGroup(
            c14.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c14.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneAjouterSeanceSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paneAjouterCoursSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paneModifSeanceSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        paneGererCoursSP.setLeftComponent(containerSP2);

        this.addTab("Gérer les cours", paneGererCoursSP);
    }
    
    public void gererLeSite() { 
        paneGererSiteSP = new JPanel();
        paneSitesSP = new JPanel();
        siteSalleSP = new JLabel("Sites et salles :");
        selectionnerSiteSP = new JComboBox<>();
        paneListeSallesSP = new JScrollPane();
        listeSallesSP = new JList<>();
        salleSP = new JLabel("Salle : [Salle]");
        capaciteSP = new JLabel("Capacité : [Cap.]");
        siteSP = new JLabel("Site : [Site]");
        supprimerSalleSP = new JButton("Supprimer");
        supprimerSiteSP = new JButton("Supprimer");
        modifierCapaciteSP = new JButton("Modifier");
        paneAjouterSiteSP = new JPanel();
        ajouterSiteSP = new JLabel("Ajouter un site :");
        siteAddSP = new JLabel("Site : ");
        siteTFSP = new JTextField();
        validerSiteSP = new JButton("Valider");
        paneAjouterSalleSP = new JPanel();
        ajouterSalleSP = new JLabel("Ajouter une salle :");
        salleAddSP = new JLabel("Salle :");
        salleTFSP = new JTextField();
        validerSalleSP = new JButton("Valider");
        selectionnerSiteSP2 = new JComboBox<>();
        capaciteAddSP = new JLabel("Capacité :");
        capaciteSpinnerSP = new JSpinner();
        
        selectionnerSiteSP.setModel(new DefaultComboBoxModel<>(new String[] { "Site", "Eiffel 1", "Eiffel 2", "Eiffel 3", "Eiffel 4", "Eiffel 5", "CNAM" }));

        remplirListe(listeSallesSP);
        
        paneListeSallesSP.setViewportView(listeSallesSP);

        GroupLayout paneAjouterSiteSPLayout = new GroupLayout(paneAjouterSiteSP);
        paneAjouterSiteSP.setLayout(paneAjouterSiteSPLayout);
        paneAjouterSiteSPLayout.setHorizontalGroup(
            paneAjouterSiteSPLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(paneAjouterSiteSPLayout.createSequentialGroup()
                .addComponent(ajouterSiteSP)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(paneAjouterSiteSPLayout.createSequentialGroup()
                .addComponent(siteAddSP)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(siteTFSP, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(validerSiteSP, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
        );
        paneAjouterSiteSPLayout.setVerticalGroup(
            paneAjouterSiteSPLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(paneAjouterSiteSPLayout.createSequentialGroup()
                .addComponent(ajouterSiteSP)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneAjouterSiteSPLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(siteAddSP)
                    .addComponent(siteTFSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(validerSiteSP))
                .addContainerGap())
        );

        selectionnerSiteSP2.setModel(new DefaultComboBoxModel<>(new String[] { "Site", "Eiffel 1", "Eiffel 2", "Eiffel 3", "Eiffel 4", "Eiffel 5", "CNAM" }));

        GroupLayout paneAjouterSalleSPLayout = new GroupLayout(paneAjouterSalleSP);
        paneAjouterSalleSP.setLayout(paneAjouterSalleSPLayout);
        paneAjouterSalleSPLayout.setHorizontalGroup(
            paneAjouterSalleSPLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(paneAjouterSalleSPLayout.createSequentialGroup()
                .addGroup(paneAjouterSalleSPLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(ajouterSalleSP)
                    .addComponent(selectionnerSiteSP2, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(paneAjouterSalleSPLayout.createSequentialGroup()
                .addComponent(capaciteAddSP)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(capaciteSpinnerSP, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(validerSalleSP, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
            .addGroup(paneAjouterSalleSPLayout.createSequentialGroup()
                .addComponent(salleAddSP)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(salleTFSP, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        paneAjouterSalleSPLayout.setVerticalGroup(
            paneAjouterSalleSPLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(paneAjouterSalleSPLayout.createSequentialGroup()
                .addComponent(ajouterSalleSP)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectionnerSiteSP2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneAjouterSalleSPLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(salleAddSP)
                    .addComponent(salleTFSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(paneAjouterSalleSPLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(capaciteAddSP)
                    .addComponent(validerSalleSP)
                    .addComponent(capaciteSpinnerSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        );

        GroupLayout paneSitesSPLayout = new GroupLayout(paneSitesSP);
        paneSitesSP.setLayout(paneSitesSPLayout);
        paneSitesSPLayout.setHorizontalGroup(
            paneSitesSPLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(paneSitesSPLayout.createSequentialGroup()
                .addComponent(siteSalleSP)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(paneSitesSPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneListeSallesSP, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneSitesSPLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(selectionnerSiteSP, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                    .addGroup(GroupLayout.Alignment.TRAILING, paneSitesSPLayout.createSequentialGroup()
                        .addGroup(paneSitesSPLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(salleSP)
                            .addComponent(capaciteSP))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(paneSitesSPLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(supprimerSalleSP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(modifierCapaciteSP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(paneSitesSPLayout.createSequentialGroup()
                        .addComponent(siteSP)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(supprimerSiteSP))
                    .addComponent(paneAjouterSiteSP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paneAjouterSalleSP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        paneSitesSPLayout.setVerticalGroup(
            paneSitesSPLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(paneSitesSPLayout.createSequentialGroup()
                .addComponent(siteSalleSP)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneSitesSPLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(paneSitesSPLayout.createSequentialGroup()
                        .addComponent(selectionnerSiteSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(paneSitesSPLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(siteSP)
                            .addComponent(supprimerSiteSP))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(paneSitesSPLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(salleSP)
                            .addComponent(supprimerSalleSP))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(paneSitesSPLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(modifierCapaciteSP)
                            .addComponent(capaciteSP))
                        .addGap(18, 18, 18)
                        .addComponent(paneAjouterSiteSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(paneAjouterSalleSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 64, Short.MAX_VALUE))
                    .addComponent(paneListeSallesSP))
                .addContainerGap())
        );

        GroupLayout paneGererSiteSPLayout = new GroupLayout(paneGererSiteSP);
        paneGererSiteSP.setLayout(paneGererSiteSPLayout);
        paneGererSiteSPLayout.setHorizontalGroup(
            paneGererSiteSPLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(paneGererSiteSPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneSitesSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(448, Short.MAX_VALUE))
        );
        paneGererSiteSPLayout.setVerticalGroup(
            paneGererSiteSPLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(paneGererSiteSPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneSitesSP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        
        this.addTab("Gérer le site", paneGererSiteSP);     
    }
    
    public void gererLesUtilisateurs() {
        //Service planification
        groupeDroitSP = new ButtonGroup();
        groupeDroitModifSP = new ButtonGroup();
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
        paneGroupeSP = new JPanel();
        groupeTextSP = new JLabel("Gérer les groupes");
        selectionnerGroupeSP = new JComboBox<>();
        selectionnerPromoSP = new JComboBox<>();
        promoTextSP = new JLabel("Promotion :");
        groupeLabelSP = new JLabel("Groupe :      ");
        boutonSuppSP = new JButton("Supprimer");
        boutonAddSP = new JButton("Ajouter");
        boutonModSP = new JButton("Modifier");
        promoTFSP = new JTextField("[Promo]");
        groupeTFSP = new JTextField("[Groupe]");
        
        splitGererUtilisateursSP.setResizeWeight(0.5); //La séparation du split pane est au milieu
        splitGererUtilisateursSP.setOneTouchExpandable(true); //Séparation mobile au milieu
     
        remplirListe(listeUtilisateursSP);
        
        scrollListeSP.setViewportView(listeUtilisateursSP);

        GroupLayout c5 = new GroupLayout(paneListeSP);
        paneListeSP.setLayout(c5);
        c5.setHorizontalGroup(
            c5.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(scrollListeSP, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
        );
        c5.setVerticalGroup(
            c5.createParallelGroup(GroupLayout.Alignment.LEADING)
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
            c6.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c6.createSequentialGroup()
                .addContainerGap()
                .addGroup(c6.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(c6.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(boutonSupp))
                    .addGroup(c6.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addComponent(prenomSP, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                        .addComponent(nomSP, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(emailSP, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(c6.createSequentialGroup()
                        .addComponent(droitSP)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
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
            c6.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c6.createSequentialGroup()
                .addContainerGap()
                .addComponent(modifierTextSP)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectionProfilSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(emailSP)
                .addGap(13, 13, 13)
                .addComponent(nomSP)
                .addGap(13, 13, 13)
                .addComponent(prenomSP)
                .addGap(13, 13, 13)
                .addComponent(numeroSP)
                .addGap(13, 13, 13)
                .addGroup(c6.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(droitSP)
                    .addComponent(referentSP)
                    .addComponent(enseignantSP)
                    .addComponent(etudiantSP))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(boutonSupp)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        groupeDroitModifSP.add(referentAddSP);
        groupeDroitModifSP.add(enseignantAddSP);
        groupeDroitModifSP.add(etudiantAddSP);

        GroupLayout c7 = new GroupLayout(paneAjoutSP);
        paneAjoutSP.setLayout(c7);
        c7.setHorizontalGroup(
            c7.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c7.createSequentialGroup()
                .addGroup(c7.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(c7.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(c7.createSequentialGroup()
                            .addComponent(emailTextSP)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
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
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(referentAddSP)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(enseignantAddSP)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(etudiantAddSP)))
                    .addComponent(ajouterTextSP))
                .addGap(0, 10, Short.MAX_VALUE))
            .addGroup(c7.createSequentialGroup()
                .addContainerGap()
                .addComponent(validerSP)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        c7.setVerticalGroup(
            c7.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c7.createSequentialGroup()
                .addContainerGap()
                .addComponent(ajouterTextSP)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(c7.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(emailTextSP)
                    .addComponent(emailAddSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(c7.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nomTextSP)
                    .addComponent(nomAddSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(c7.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(prenomTextSP)
                    .addComponent(prenomAddSP1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(c7.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(droitTextSP)
                    .addComponent(referentAddSP)
                    .addComponent(enseignantAddSP)
                    .addComponent(etudiantAddSP))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(validerSP)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        remplirComboBox(selectionnerGroupeSP, "Groupes", "Groupe");

        selectionnerPromoSP.setModel(new DefaultComboBoxModel<>(new String[] { "Promo", "ING1", "ING2", "ING3", "ING4", "ING5"}));

        promoTFSP.setBackground(new java.awt.Color(240, 240, 240));
        promoTFSP.setBorder(null);

        groupeTFSP.setBackground(new java.awt.Color(240, 240, 240));
        groupeTFSP.setBorder(null);

        GroupLayout c = new GroupLayout(paneGroupeSP);
        paneGroupeSP.setLayout(c);
        c.setHorizontalGroup(
            c.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c.createSequentialGroup()
                .addGroup(c.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(groupeTextSP)
                    .addGroup(c.createSequentialGroup()
                        .addComponent(selectionnerGroupeSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectionnerPromoSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(c.createSequentialGroup()
                        .addComponent(promoTextSP)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(promoTFSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(c.createSequentialGroup()
                        .addComponent(groupeLabelSP)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(groupeTFSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(c.createSequentialGroup()
                        .addComponent(boutonSuppSP)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(boutonAddSP)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(boutonModSP)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        c.setVerticalGroup(
            c.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c.createSequentialGroup()
                .addComponent(groupeTextSP)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(c.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(selectionnerGroupeSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectionnerPromoSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(c.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(promoTextSP)
                    .addComponent(promoTFSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(c.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(groupeLabelSP)
                    .addComponent(groupeTFSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(c.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(boutonSuppSP)
                    .addComponent(boutonAddSP)
                    .addComponent(boutonModSP))
                .addGap(0, 32, Short.MAX_VALUE))
        );
        
        GroupLayout c8 = new GroupLayout(paneGererSP);
        paneGererSP.setLayout(c8);
        c8.setHorizontalGroup(
            c8.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c8.createSequentialGroup()
                .addComponent(paneModificationSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
            .addGroup(c8.createSequentialGroup()
                .addContainerGap()
                .addGroup(c8.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(paneAjoutSP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paneGroupeSP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        c8.setVerticalGroup(
            c8.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c8.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneModificationSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paneAjoutSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(paneGroupeSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        splitGererUtilisateursSP.setLeftComponent(paneGererSP);

        GroupLayout c9 = new GroupLayout(containerSP);
        containerSP.setLayout(c9);
        c9.setHorizontalGroup(
            c9.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, c9.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(splitGererUtilisateursSP)
                .addGap(0, 0, 0))
        );
        c9.setVerticalGroup(
            c9.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c9.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(splitGererUtilisateursSP, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        paneGererUtilisateursSP.setViewportView(containerSP);
        
        this.addTab("Gérer les utilisateurs", paneGererUtilisateursSP);
    }
    
    public void remplirListe(JList liste) {
        Vector<String> listData = new Vector();
        for(int i=1;i<101;i++)
            listData.add("Item liste "+i);
        liste.setListData(listData);
    }
    
    public void remplirComboBox(JComboBox box, String intitule, Object objet) {
        box.setModel(new DefaultComboBoxModel<>(new String[]{intitule})); 
        for(int i = 1; i < 200; i++) {
            box.addItem(objet.toString());
        }
    }
    
    public void remplirComboBoxType(JComboBox box) {
        box.setModel(new DefaultComboBoxModel<>(new String[] { "Veuillez sélectionner", 
                                                               "Magistral", 
                                                               "TP", 
                                                               "TD", 
                                                               "Interactif", 
                                                               "Projet", 
                                                               "Soutien", 
                                                               "DS", 
                                                               "Partiel", 
                                                               "Rattrapage" }));
    }
}
