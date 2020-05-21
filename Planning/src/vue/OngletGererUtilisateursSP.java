package vue;

import java.util.*;
import javax.swing.*;

public class OngletGererUtilisateursSP extends JScrollPane {
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
    private JLabel groupeLabelSP;
    private JTextField groupeTFSP;
    private JLabel groupeTextSP;
    private JPanel paneGroupeSP;
    private JTextField promoTFSP;
    private JLabel promoTextSP;
    private JComboBox<String> selectionnerGroupeSP;
    private JComboBox<String> selectionnerPromoSP;
    
    public OngletGererUtilisateursSP() {
        groupeDroitSP = new ButtonGroup();
        groupeDroitModifSP = new ButtonGroup();
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
            c1.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(scrollListeSP, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
        );
        c1.setVerticalGroup(
            c1.createParallelGroup(GroupLayout.Alignment.LEADING)
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
            c2.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c2.createSequentialGroup()
                .addContainerGap()
                .addGroup(c2.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(c2.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(boutonSupp))
                    .addGroup(c2.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addComponent(prenomSP, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                        .addComponent(nomSP, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(emailSP, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(c2.createSequentialGroup()
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
        c2.setVerticalGroup(
            c2.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c2.createSequentialGroup()
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
                .addGroup(c2.createParallelGroup(GroupLayout.Alignment.BASELINE)
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

        GroupLayout c3 = new GroupLayout(paneAjoutSP);
        paneAjoutSP.setLayout(c3);
        c3.setHorizontalGroup(
            c3.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c3.createSequentialGroup()
                .addGroup(c3.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(c3.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(c3.createSequentialGroup()
                            .addComponent(emailTextSP)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
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
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(referentAddSP)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(enseignantAddSP)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(etudiantAddSP)))
                    .addComponent(ajouterTextSP))
                .addGap(0, 10, Short.MAX_VALUE))
            .addGroup(c3.createSequentialGroup()
                .addContainerGap()
                .addComponent(validerSP)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        c3.setVerticalGroup(
            c3.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c3.createSequentialGroup()
                .addContainerGap()
                .addComponent(ajouterTextSP)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(c3.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(emailTextSP)
                    .addComponent(emailAddSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(c3.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nomTextSP)
                    .addComponent(nomAddSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(c3.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(prenomTextSP)
                    .addComponent(prenomAddSP1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(c3.createParallelGroup(GroupLayout.Alignment.BASELINE)
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

        GroupLayout c4 = new GroupLayout(paneGroupeSP);
        paneGroupeSP.setLayout(c4);
        c4.setHorizontalGroup(
            c4.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c4.createSequentialGroup()
                .addGroup(c4.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(groupeTextSP)
                    .addGroup(c4.createSequentialGroup()
                        .addComponent(selectionnerGroupeSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectionnerPromoSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(c4.createSequentialGroup()
                        .addComponent(promoTextSP)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(promoTFSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(c4.createSequentialGroup()
                        .addComponent(groupeLabelSP)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(groupeTFSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(c4.createSequentialGroup()
                        .addComponent(boutonSuppSP)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(boutonAddSP)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(boutonModSP)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        c4.setVerticalGroup(
            c4.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c4.createSequentialGroup()
                .addComponent(groupeTextSP)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(c4.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(selectionnerGroupeSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectionnerPromoSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(c4.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(promoTextSP)
                    .addComponent(promoTFSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(c4.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(groupeLabelSP)
                    .addComponent(groupeTFSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(c4.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(boutonSuppSP)
                    .addComponent(boutonAddSP)
                    .addComponent(boutonModSP))
                .addGap(0, 32, Short.MAX_VALUE))
        );
        
        GroupLayout c5 = new GroupLayout(paneGererSP);
        paneGererSP.setLayout(c5);
        c5.setHorizontalGroup(
            c5.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c5.createSequentialGroup()
                .addComponent(paneModificationSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
            .addGroup(c5.createSequentialGroup()
                .addContainerGap()
                .addGroup(c5.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(paneAjoutSP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paneGroupeSP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        c5.setVerticalGroup(
            c5.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c5.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneModificationSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paneAjoutSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(paneGroupeSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        splitGererUtilisateursSP.setLeftComponent(paneGererSP);

        GroupLayout c6 = new GroupLayout(containerSP);
        containerSP.setLayout(c6);
        c6.setHorizontalGroup(
            c6.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, c6.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(splitGererUtilisateursSP)
                .addGap(0, 0, 0))
        );
        c6.setVerticalGroup(
            c6.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c6.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(splitGererUtilisateursSP, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        this.setViewportView(containerSP);
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
