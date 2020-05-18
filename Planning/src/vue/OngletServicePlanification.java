package vue;

import java.util.*;
import javax.swing.*;
import javax.swing.GroupLayout.*;
import javax.swing.LayoutStyle.*;

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

        dateSeanceSP.setModel(new SpinnerDateModel(new Date(1598940000000L), new Date(1598940000000L), new Date(1627797600000L), Calendar.DAY_OF_MONTH));

        groupeEtatSP.add(enCoursSP);
        groupeEtatSP.add(valideeSP);

        remplirComboBox(selectionnerCoursSP, "Veuillez sélectionner", "Cours");
        remplirComboBoxType(selectionnerTypeSP);
        
        GroupLayout c1 = new GroupLayout(paneAjouterSeanceSP);
        paneAjouterSeanceSP.setLayout(c1);
        c1.setHorizontalGroup(
            c1.createParallelGroup(Alignment.LEADING)
            .addGroup(c1.createSequentialGroup()
                .addComponent(ajouterSeanceSP)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(c1.createSequentialGroup()
                .addContainerGap()
                .addGroup(c1.createParallelGroup(Alignment.LEADING)
                    .addGroup(c1.createSequentialGroup()
                        .addComponent(dateHeureSP)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(dateSeanceSP, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
                    .addGroup(c1.createSequentialGroup()
                        .addComponent(etatSP)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(enCoursSP)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(valideeSP))
                    .addGroup(c1.createSequentialGroup()
                        .addGroup(c1.createParallelGroup(Alignment.LEADING)
                            .addComponent(coursSP)
                            .addComponent(typeSP))
                        .addGap(18, 18, 18)
                        .addGroup(c1.createParallelGroup(Alignment.LEADING, false)
                            .addComponent(selectionnerCoursSP, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(selectionnerTypeSP, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                        .addComponent(ajouterBoutonSP))))
        );
        c1.setVerticalGroup(
            c1.createParallelGroup(Alignment.LEADING)
            .addGroup(c1.createSequentialGroup()
                .addComponent(ajouterSeanceSP)
                .addGap(12, 12, 12)
                .addGroup(c1.createParallelGroup(Alignment.BASELINE)
                    .addComponent(dateHeureSP)
                    .addComponent(dateSeanceSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(c1.createParallelGroup(Alignment.BASELINE)
                    .addComponent(etatSP)
                    .addComponent(enCoursSP)
                    .addComponent(valideeSP))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(c1.createParallelGroup(Alignment.BASELINE)
                    .addComponent(coursSP)
                    .addComponent(selectionnerCoursSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(c1.createParallelGroup(Alignment.BASELINE)
                    .addComponent(typeSP)
                    .addComponent(selectionnerTypeSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(ajouterBoutonSP))
                .addGap(10, 10, 10))
        );

        GroupLayout c2 = new GroupLayout(paneAjouterCoursSP);
        paneAjouterCoursSP.setLayout(c2);
        c2.setHorizontalGroup(
            c2.createParallelGroup(Alignment.LEADING)
            .addGroup(c2.createSequentialGroup()
                .addComponent(ajouterCoursSP)
                .addGap(0, 0, 0))
            .addGroup(c2.createSequentialGroup()
                .addContainerGap()
                .addComponent(intituleCoursSP)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(intituleTextP, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(validerCoursSP)
                .addContainerGap())
        );
        c2.setVerticalGroup(
            c2.createParallelGroup(Alignment.LEADING)
            .addGroup(c2.createSequentialGroup()
                .addComponent(ajouterCoursSP)
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addGroup(c2.createParallelGroup(Alignment.BASELINE)
                    .addComponent(intituleCoursSP)
                    .addComponent(intituleTextP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(validerCoursSP))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        dateSeanceModifSP.setModel(new SpinnerDateModel(new Date(1598940000000L), new Date(1598940000000L), new Date(1627797600000L), Calendar.DAY_OF_MONTH));

        groupeEtatModifSP.add(enCoursModifSP);
        groupeEtatModifSP.add(valideeModifSP);

        remplirComboBox(selectionnerCoursModifSP, "Veuillez sélectionner", "Cours");
        remplirComboBoxType(selectionnerTypeModifSP);

        groupeEtatModifSP.add(anuleeBoutonModifSP);

        GroupLayout c3 = new GroupLayout(paneModifSeanceSP);
        paneModifSeanceSP.setLayout(c3);
        c3.setHorizontalGroup(
            c3.createParallelGroup(Alignment.LEADING)
            .addGroup(c3.createSequentialGroup()
                .addGroup(c3.createParallelGroup(Alignment.LEADING)
                    .addComponent(modifierSeanceSP)
                    .addGroup(c3.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(c3.createParallelGroup(Alignment.LEADING)
                            .addGroup(c3.createSequentialGroup()
                                .addGroup(c3.createParallelGroup(Alignment.LEADING)
                                    .addComponent(coursModifSP)
                                    .addComponent(typeModifSP))
                                .addGap(18, 18, 18)
                                .addGroup(c3.createParallelGroup(Alignment.LEADING, false)
                                    .addComponent(selectionnerCoursModifSP, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(selectionnerTypeModifSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(c3.createSequentialGroup()
                                .addComponent(dateHeureModifSP)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(dateSeanceModifSP, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
                            .addGroup(c3.createSequentialGroup()
                                .addComponent(etatModifSP)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(enCoursModifSP)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(valideeModifSP)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(anuleeBoutonModifSP)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(32, 32, 32)
                .addComponent(ajouterBoutonModifSP)
                .addContainerGap())
        );
        c3.setVerticalGroup(
            c3.createParallelGroup(Alignment.LEADING)
            .addGroup(c3.createSequentialGroup()
                .addComponent(modifierSeanceSP)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(c3.createParallelGroup(Alignment.BASELINE)
                    .addComponent(dateHeureModifSP)
                    .addComponent(dateSeanceModifSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(c3.createParallelGroup(Alignment.BASELINE)
                    .addComponent(etatModifSP)
                    .addComponent(enCoursModifSP)
                    .addComponent(valideeModifSP)
                    .addComponent(anuleeBoutonModifSP))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(c3.createParallelGroup(Alignment.BASELINE)
                    .addComponent(coursModifSP)
                    .addComponent(selectionnerCoursModifSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(c3.createParallelGroup(Alignment.BASELINE)
                    .addComponent(typeModifSP)
                    .addComponent(selectionnerTypeModifSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(ajouterBoutonModifSP))
                .addGap(0, 9, Short.MAX_VALUE))
        );

        GroupLayout c4 = new GroupLayout(containerSP2);
        containerSP2.setLayout(c4);
        c4.setHorizontalGroup(
            c4.createParallelGroup(Alignment.LEADING)
            .addGroup(c4.createSequentialGroup()
                .addContainerGap()
                .addGroup(c4.createParallelGroup(Alignment.LEADING)
                    .addComponent(paneAjouterCoursSP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(c4.createSequentialGroup()
                        .addComponent(paneAjouterSeanceSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(paneModifSeanceSP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        c4.setVerticalGroup(
            c4.createParallelGroup(Alignment.LEADING)
            .addGroup(c4.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneAjouterSeanceSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(paneAjouterCoursSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
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

        GroupLayout c1 = new GroupLayout(paneAjouterSiteSP);
        paneAjouterSiteSP.setLayout(c1);
        c1.setHorizontalGroup(
            c1.createParallelGroup(Alignment.LEADING)
            .addGroup(c1.createSequentialGroup()
                .addComponent(ajouterSiteSP)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(c1.createSequentialGroup()
                .addComponent(siteAddSP)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(siteTFSP, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(validerSiteSP, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
        );
        c1.setVerticalGroup(
            c1.createParallelGroup(Alignment.LEADING)
            .addGroup(c1.createSequentialGroup()
                .addComponent(ajouterSiteSP)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(c1.createParallelGroup(Alignment.BASELINE)
                    .addComponent(siteAddSP)
                    .addComponent(siteTFSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(validerSiteSP))
                .addContainerGap())
        );

        selectionnerSiteSP2.setModel(new DefaultComboBoxModel<>(new String[] { "Site", "Eiffel 1", "Eiffel 2", "Eiffel 3", "Eiffel 4", "Eiffel 5", "CNAM" }));

        GroupLayout c2 = new GroupLayout(paneAjouterSalleSP);
        paneAjouterSalleSP.setLayout(c2);
        c2.setHorizontalGroup(
            c2.createParallelGroup(Alignment.LEADING)
            .addGroup(c2.createSequentialGroup()
                .addGroup(c2.createParallelGroup(Alignment.LEADING)
                    .addComponent(ajouterSalleSP)
                    .addComponent(selectionnerSiteSP2, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(c2.createSequentialGroup()
                .addComponent(capaciteAddSP)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(capaciteSpinnerSP, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(validerSalleSP, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
            .addGroup(c2.createSequentialGroup()
                .addComponent(salleAddSP)
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addComponent(salleTFSP, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        c2.setVerticalGroup(
            c2.createParallelGroup(Alignment.LEADING)
            .addGroup(c2.createSequentialGroup()
                .addComponent(ajouterSalleSP)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(selectionnerSiteSP2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addGroup(c2.createParallelGroup(Alignment.BASELINE)
                    .addComponent(salleAddSP)
                    .addComponent(salleTFSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(c2.createParallelGroup(Alignment.BASELINE)
                    .addComponent(capaciteAddSP)
                    .addComponent(validerSalleSP)
                    .addComponent(capaciteSpinnerSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        );

        GroupLayout c3 = new GroupLayout(paneSitesSP);
        paneSitesSP.setLayout(c3);
        c3.setHorizontalGroup(
            c3.createParallelGroup(Alignment.LEADING)
            .addGroup(c3.createSequentialGroup()
                .addComponent(siteSalleSP)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(c3.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneListeSallesSP, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(c3.createParallelGroup(Alignment.LEADING)
                    .addComponent(selectionnerSiteSP, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                    .addGroup(Alignment.TRAILING, c3.createSequentialGroup()
                        .addGroup(c3.createParallelGroup(Alignment.LEADING)
                            .addComponent(salleSP)
                            .addComponent(capaciteSP))
                        .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(c3.createParallelGroup(Alignment.LEADING, false)
                            .addComponent(supprimerSalleSP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(modifierCapaciteSP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(c3.createSequentialGroup()
                        .addComponent(siteSP)
                        .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(supprimerSiteSP))
                    .addComponent(paneAjouterSiteSP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paneAjouterSalleSP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        c3.setVerticalGroup(
            c3.createParallelGroup(Alignment.LEADING)
            .addGroup(c3.createSequentialGroup()
                .addComponent(siteSalleSP)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(c3.createParallelGroup(Alignment.LEADING)
                    .addGroup(c3.createSequentialGroup()
                        .addComponent(selectionnerSiteSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addGroup(c3.createParallelGroup(Alignment.BASELINE)
                            .addComponent(siteSP)
                            .addComponent(supprimerSiteSP))
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addGroup(c3.createParallelGroup(Alignment.BASELINE)
                            .addComponent(salleSP)
                            .addComponent(supprimerSalleSP))
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addGroup(c3.createParallelGroup(Alignment.BASELINE)
                            .addComponent(modifierCapaciteSP)
                            .addComponent(capaciteSP))
                        .addGap(18, 18, 18)
                        .addComponent(paneAjouterSiteSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(paneAjouterSalleSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 64, Short.MAX_VALUE))
                    .addComponent(paneListeSallesSP))
                .addContainerGap())
        );

        GroupLayout c4 = new GroupLayout(paneGererSiteSP);
        paneGererSiteSP.setLayout(c4);
        c4.setHorizontalGroup(
            c4.createParallelGroup(Alignment.LEADING)
            .addGroup(c4.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneSitesSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(448, Short.MAX_VALUE))
        );
        c4.setVerticalGroup(
            c4.createParallelGroup(Alignment.LEADING)
            .addGroup(c4.createSequentialGroup()
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

        GroupLayout c1 = new GroupLayout(paneListeSP);
        paneListeSP.setLayout(c1);
        c1.setHorizontalGroup(
            c1.createParallelGroup(Alignment.LEADING)
            .addComponent(scrollListeSP, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
        );
        c1.setVerticalGroup(
            c1.createParallelGroup(Alignment.LEADING)
            .addGroup(c1.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(scrollListeSP, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        splitGererUtilisateursSP.setRightComponent(paneListeSP);
        groupeDroitSP.add(referentSP);
        groupeDroitSP.add(enseignantSP);
        groupeDroitSP.add(etudiantSP);
  
        selectionProfilSP.setModel(new DefaultComboBoxModel<>(new String[] { "Veuillez sélectionner", "Référent", "Enseignant", "Etudiant" }));
    
        GroupLayout c2 = new GroupLayout(paneModificationSP);
        paneModificationSP.setLayout(c2);
        c2.setHorizontalGroup(
            c2.createParallelGroup(Alignment.LEADING)
            .addGroup(c2.createSequentialGroup()
                .addContainerGap()
                .addGroup(c2.createParallelGroup(Alignment.LEADING)
                    .addGroup(c2.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(boutonSupp))
                    .addGroup(c2.createParallelGroup(Alignment.TRAILING, false)
                        .addComponent(prenomSP, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                        .addComponent(nomSP, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(emailSP, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(c2.createSequentialGroup()
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
        c2.setVerticalGroup(
            c2.createParallelGroup(Alignment.LEADING)
            .addGroup(c2.createSequentialGroup()
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
                .addGroup(c2.createParallelGroup(Alignment.BASELINE)
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

        GroupLayout c3 = new GroupLayout(paneAjoutSP);
        paneAjoutSP.setLayout(c3);
        c3.setHorizontalGroup(
            c3.createParallelGroup(Alignment.LEADING)
            .addGroup(c3.createSequentialGroup()
                .addGroup(c3.createParallelGroup(Alignment.LEADING)
                    .addGroup(c3.createParallelGroup(Alignment.LEADING, false)
                        .addGroup(c3.createSequentialGroup()
                            .addComponent(emailTextSP)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(emailAddSP))
                        .addGroup(c3.createSequentialGroup()
                            .addComponent(nomTextSP)
                            .addGap(43, 43, 43)
                            .addComponent(nomAddSP))
                        .addGroup(c3.createSequentialGroup()
                            .addComponent(prenomTextSP)
                            .addGap(28, 28, 28)
                            .addComponent(prenomAddSP1))
                        .addGroup(c3.createSequentialGroup()
                            .addComponent(droitTextSP)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(referentAddSP)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(enseignantAddSP)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(etudiantAddSP)))
                    .addComponent(ajouterTextSP))
                .addGap(0, 10, Short.MAX_VALUE))
            .addGroup(c3.createSequentialGroup()
                .addContainerGap()
                .addComponent(validerSP)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        c3.setVerticalGroup(
            c3.createParallelGroup(Alignment.LEADING)
            .addGroup(c3.createSequentialGroup()
                .addContainerGap()
                .addComponent(ajouterTextSP)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(c3.createParallelGroup(Alignment.BASELINE)
                    .addComponent(emailTextSP)
                    .addComponent(emailAddSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(c3.createParallelGroup(Alignment.BASELINE)
                    .addComponent(nomTextSP)
                    .addComponent(nomAddSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(c3.createParallelGroup(Alignment.BASELINE)
                    .addComponent(prenomTextSP)
                    .addComponent(prenomAddSP1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(c3.createParallelGroup(Alignment.BASELINE)
                    .addComponent(droitTextSP)
                    .addComponent(referentAddSP)
                    .addComponent(enseignantAddSP)
                    .addComponent(etudiantAddSP))
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addComponent(validerSP)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        remplirComboBox(selectionnerGroupeSP, "Groupes", "Groupe");

        selectionnerPromoSP.setModel(new DefaultComboBoxModel<>(new String[] { "Promo", "ING1", "ING2", "ING3", "ING4", "ING5"}));

        promoTFSP.setBackground(new java.awt.Color(240, 240, 240));
        promoTFSP.setBorder(null);

        groupeTFSP.setBackground(new java.awt.Color(240, 240, 240));
        groupeTFSP.setBorder(null);

        GroupLayout c4 = new GroupLayout(paneGroupeSP);
        paneGroupeSP.setLayout(c4);
        c4.setHorizontalGroup(
            c4.createParallelGroup(Alignment.LEADING)
            .addGroup(c4.createSequentialGroup()
                .addGroup(c4.createParallelGroup(Alignment.LEADING)
                    .addComponent(groupeTextSP)
                    .addGroup(c4.createSequentialGroup()
                        .addComponent(selectionnerGroupeSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(selectionnerPromoSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(c4.createSequentialGroup()
                        .addComponent(promoTextSP)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(promoTFSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(c4.createSequentialGroup()
                        .addComponent(groupeLabelSP)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(groupeTFSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(c4.createSequentialGroup()
                        .addComponent(boutonSuppSP)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(boutonAddSP)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(boutonModSP)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        c4.setVerticalGroup(
            c4.createParallelGroup(Alignment.LEADING)
            .addGroup(c4.createSequentialGroup()
                .addComponent(groupeTextSP)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(c4.createParallelGroup(Alignment.BASELINE)
                    .addComponent(selectionnerGroupeSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectionnerPromoSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addGroup(c4.createParallelGroup(Alignment.BASELINE)
                    .addComponent(promoTextSP)
                    .addComponent(promoTFSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(c4.createParallelGroup(Alignment.BASELINE)
                    .addComponent(groupeLabelSP)
                    .addComponent(groupeTFSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(c4.createParallelGroup(Alignment.BASELINE)
                    .addComponent(boutonSuppSP)
                    .addComponent(boutonAddSP)
                    .addComponent(boutonModSP))
                .addGap(0, 32, Short.MAX_VALUE))
        );
        
        GroupLayout c5 = new GroupLayout(paneGererSP);
        paneGererSP.setLayout(c5);
        c5.setHorizontalGroup(
            c5.createParallelGroup(Alignment.LEADING)
            .addGroup(c5.createSequentialGroup()
                .addComponent(paneModificationSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
            .addGroup(c5.createSequentialGroup()
                .addContainerGap()
                .addGroup(c5.createParallelGroup(Alignment.LEADING, false)
                    .addComponent(paneAjoutSP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paneGroupeSP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        c5.setVerticalGroup(
            c5.createParallelGroup(Alignment.LEADING)
            .addGroup(c5.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneModificationSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(paneAjoutSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(paneGroupeSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        splitGererUtilisateursSP.setLeftComponent(paneGererSP);

        GroupLayout c6 = new GroupLayout(containerSP);
        containerSP.setLayout(c6);
        c6.setHorizontalGroup(
            c6.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, c6.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(splitGererUtilisateursSP)
                .addGap(0, 0, 0))
        );
        c6.setVerticalGroup(
            c6.createParallelGroup(Alignment.LEADING)
            .addGroup(c6.createSequentialGroup()
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
