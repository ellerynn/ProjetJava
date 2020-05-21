package vue;

import java.util.*;
import javax.swing.*;

public class OngletGererCoursSP extends JSplitPane {
    //private JSplitPane paneGererCoursSP;
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
    
    public OngletGererCoursSP() {
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
        
        this.setResizeWeight(0.5);

        remplirListe(listeSeancesSP);
        
        scrollListeSeancesSP.setViewportView(listeSeancesSP);

        this.setRightComponent(scrollListeSeancesSP);

        dateSeanceSP.setModel(new SpinnerDateModel(new Date(1598940000000L), new Date(1598940000000L), new Date(1627797600000L), Calendar.DAY_OF_MONTH));

        groupeEtatSP.add(enCoursSP);
        groupeEtatSP.add(valideeSP);

        remplirComboBox(selectionnerCoursSP, "Veuillez sélectionner", "Cours");
        remplirComboBoxType(selectionnerTypeSP);
        
        GroupLayout c1 = new GroupLayout(paneAjouterSeanceSP);
        paneAjouterSeanceSP.setLayout(c1);
        c1.setHorizontalGroup(
            c1.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c1.createSequentialGroup()
                .addComponent(ajouterSeanceSP)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(c1.createSequentialGroup()
                .addContainerGap()
                .addGroup(c1.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(c1.createSequentialGroup()
                        .addComponent(dateHeureSP)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateSeanceSP, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
                    .addGroup(c1.createSequentialGroup()
                        .addComponent(etatSP)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(enCoursSP)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valideeSP))
                    .addGroup(c1.createSequentialGroup()
                        .addGroup(c1.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(coursSP)
                            .addComponent(typeSP))
                        .addGap(18, 18, 18)
                        .addGroup(c1.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(selectionnerCoursSP, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(selectionnerTypeSP, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                        .addComponent(ajouterBoutonSP))))
        );
        c1.setVerticalGroup(
            c1.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c1.createSequentialGroup()
                .addComponent(ajouterSeanceSP)
                .addGap(12, 12, 12)
                .addGroup(c1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(dateHeureSP)
                    .addComponent(dateSeanceSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(c1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(etatSP)
                    .addComponent(enCoursSP)
                    .addComponent(valideeSP))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(c1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(coursSP)
                    .addComponent(selectionnerCoursSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(c1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(typeSP)
                    .addComponent(selectionnerTypeSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(ajouterBoutonSP))
                .addGap(10, 10, 10))
        );

        GroupLayout c2 = new GroupLayout(paneAjouterCoursSP);
        paneAjouterCoursSP.setLayout(c2);
        c2.setHorizontalGroup(
            c2.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c2.createSequentialGroup()
                .addComponent(ajouterCoursSP)
                .addGap(0, 0, 0))
            .addGroup(c2.createSequentialGroup()
                .addContainerGap()
                .addComponent(intituleCoursSP)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(intituleTextP, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(validerCoursSP)
                .addContainerGap())
        );
        c2.setVerticalGroup(
            c2.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c2.createSequentialGroup()
                .addComponent(ajouterCoursSP)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(c2.createParallelGroup(GroupLayout.Alignment.BASELINE)
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
            c3.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c3.createSequentialGroup()
                .addGroup(c3.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(modifierSeanceSP)
                    .addGroup(c3.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(c3.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(c3.createSequentialGroup()
                                .addGroup(c3.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(coursModifSP)
                                    .addComponent(typeModifSP))
                                .addGap(18, 18, 18)
                                .addGroup(c3.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(selectionnerCoursModifSP, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(selectionnerTypeModifSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(c3.createSequentialGroup()
                                .addComponent(dateHeureModifSP)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dateSeanceModifSP, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
                            .addGroup(c3.createSequentialGroup()
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
        c3.setVerticalGroup(
            c3.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c3.createSequentialGroup()
                .addComponent(modifierSeanceSP)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(c3.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(dateHeureModifSP)
                    .addComponent(dateSeanceModifSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(c3.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(etatModifSP)
                    .addComponent(enCoursModifSP)
                    .addComponent(valideeModifSP)
                    .addComponent(anuleeBoutonModifSP))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(c3.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(coursModifSP)
                    .addComponent(selectionnerCoursModifSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(c3.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(typeModifSP)
                    .addComponent(selectionnerTypeModifSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(ajouterBoutonModifSP))
                .addGap(0, 9, Short.MAX_VALUE))
        );

        GroupLayout c4 = new GroupLayout(containerSP2);
        containerSP2.setLayout(c4);
        c4.setHorizontalGroup(
            c4.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c4.createSequentialGroup()
                .addContainerGap()
                .addGroup(c4.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(paneAjouterCoursSP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(c4.createSequentialGroup()
                        .addComponent(paneAjouterSeanceSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(paneModifSeanceSP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        c4.setVerticalGroup(
            c4.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c4.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneAjouterSeanceSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paneAjouterCoursSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paneModifSeanceSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        this.setLeftComponent(containerSP2);
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
