package vue;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout.*;
import javax.swing.LayoutStyle.*;
import javax.swing.table.*;

public class EmploiDuTemps extends JTabbedPane {

    //Variables onglet Home                     
    private JPanel container1Home;
    private JPanel container2Home;
    private JTextField cursusTextHome;
    private JSpinner dateHome;
    private JTextField edtTextHome;
    private JButton iconeHome;
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
        iconeHome = new JButton("Icon"); //Bouton A DESIGN
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
        rechercheBoutonCours = new JButton("Icon"); //A DESIGN
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
        rechercheBoutonSalle = new JButton("Icon");
        semaineSalle = new JComboBox<>(); 
        scrollPaneSalle = new JScrollPane(); 
        tabSalle = new JTable(); 
        scrollPaneRecapSalle = new JScrollPane(); 
        tabLibresSalle = new JTable(); 

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

        GroupLayout c1 = new GroupLayout(container1Home); //Plusieurs éléments dans un container global --> gérer les alignements
        container1Home.setLayout(c1);
        c1.setHorizontalGroup(
                c1.createParallelGroup(Alignment.LEADING)
                        .addGroup(c1.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(edtTextHome, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateHome, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                                .addComponent(iconeHome, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                        .addComponent(scrollPaneHome, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        c1.setVerticalGroup(
                c1.createParallelGroup(Alignment.LEADING)
                        .addGroup(c1.createSequentialGroup()
                                .addGroup(c1.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(edtTextHome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dateHome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(iconeHome, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
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

        semaineCours.setModel(new DefaultComboBoxModel<>(new String[]{"Semaine", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "..."})); //CHANGER

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
                                        .addComponent(rechercheBoutonCours)
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

        semaineSalle.setModel(new DefaultComboBoxModel<>(new String[]{"Semaine", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "..."}));

        scrollPaneSalle.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tabSalle.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Horaires", "[Date]", "[Date]", "[Date]", "[Date]", "[Date]", "[Date]"}));
        tabSalle.getTableHeader().setReorderingAllowed(false);
        
        scrollPaneSalle.setViewportView(tabSalle);

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
                                        .addComponent(rechercheBoutonSalle)
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
    }
}
