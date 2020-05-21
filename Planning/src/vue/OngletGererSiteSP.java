package vue;

import java.util.*;
import javax.swing.*;

public class OngletGererSiteSP extends JPanel {
    //SP -> Gérer le site
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
    
    public OngletGererSiteSP() {
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
            c1.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c1.createSequentialGroup()
                .addComponent(ajouterSiteSP)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(c1.createSequentialGroup()
                .addComponent(siteAddSP)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(siteTFSP, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(validerSiteSP, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
        );
        c1.setVerticalGroup(
            c1.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c1.createSequentialGroup()
                .addComponent(ajouterSiteSP)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(c1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(siteAddSP)
                    .addComponent(siteTFSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(validerSiteSP))
                .addContainerGap())
        );

        selectionnerSiteSP2.setModel(new DefaultComboBoxModel<>(new String[] { "Site", "Eiffel 1", "Eiffel 2", "Eiffel 3", "Eiffel 4", "Eiffel 5", "CNAM" }));

        GroupLayout c2 = new GroupLayout(paneAjouterSalleSP);
        paneAjouterSalleSP.setLayout(c2);
        c2.setHorizontalGroup(
            c2.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c2.createSequentialGroup()
                .addGroup(c2.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(ajouterSalleSP)
                    .addComponent(selectionnerSiteSP2, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(c2.createSequentialGroup()
                .addComponent(capaciteAddSP)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(capaciteSpinnerSP, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(validerSalleSP, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
            .addGroup(c2.createSequentialGroup()
                .addComponent(salleAddSP)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(salleTFSP, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        c2.setVerticalGroup(
            c2.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c2.createSequentialGroup()
                .addComponent(ajouterSalleSP)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectionnerSiteSP2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(c2.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(salleAddSP)
                    .addComponent(salleTFSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(c2.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(capaciteAddSP)
                    .addComponent(validerSalleSP)
                    .addComponent(capaciteSpinnerSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        );

        GroupLayout c3 = new GroupLayout(paneSitesSP);
        paneSitesSP.setLayout(c3);
        c3.setHorizontalGroup(
            c3.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c3.createSequentialGroup()
                .addComponent(siteSalleSP)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(c3.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneListeSallesSP, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(c3.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(selectionnerSiteSP, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                    .addGroup(GroupLayout.Alignment.TRAILING, c3.createSequentialGroup()
                        .addGroup(c3.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(salleSP)
                            .addComponent(capaciteSP))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(c3.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(supprimerSalleSP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(modifierCapaciteSP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(c3.createSequentialGroup()
                        .addComponent(siteSP)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(supprimerSiteSP))
                    .addComponent(paneAjouterSiteSP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paneAjouterSalleSP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        c3.setVerticalGroup(
            c3.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c3.createSequentialGroup()
                .addComponent(siteSalleSP)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(c3.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(c3.createSequentialGroup()
                        .addComponent(selectionnerSiteSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(c3.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(siteSP)
                            .addComponent(supprimerSiteSP))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(c3.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(salleSP)
                            .addComponent(supprimerSalleSP))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(c3.createParallelGroup(GroupLayout.Alignment.BASELINE)
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

        GroupLayout c4 = new GroupLayout(this);
        this.setLayout(c4);
        c4.setHorizontalGroup(
            c4.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c4.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneSitesSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(448, Short.MAX_VALUE))
        );
        c4.setVerticalGroup(
            c4.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c4.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneSitesSP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }
    
    public void remplirListe(JList liste) {
        Vector<String> listData = new Vector();
        for(int i=1;i<101;i++)
            listData.add("Item liste "+i);
        liste.setListData(listData);
    }
}
