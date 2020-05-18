package vue;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.*;

public class OngletHome extends JSplitPane {
    //Variables onglet Home                     
    private JPanel container1Home;
    private JPanel container2Home;
    private JTextField cursusTextHome;
    private JSpinner dateHome;
    private JTextField edtTextHome;
    private JButton linkCoursHome;
    private JScrollPane scrollPaneHome;
    private JTable tabCoursHome;
    
    public OngletHome() {
        container1Home = new JPanel(); //Panneau
        edtTextHome = new JTextField("Emploi du temps"); //Texte
        dateHome = new JSpinner();
        linkCoursHome = new JButton(); //Bouton
        scrollPaneHome = new JScrollPane(); //Panneau avec scroll vertical
        tabCoursHome = new JTable(); //Tableau
        container2Home = new JPanel(); 
        cursusTextHome = new JTextField("Mon cursus"); 
        
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
        
        //DANS EMPLOI DU TEMPS
        //linkCoursHome.addActionListener(this); //Ajout d'un lien vers un des onglet de JTabbedPane sur un JButton
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

        this.setLeftComponent(container1Home); //Container gauche

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

        this.setRightComponent(container2Home); //Container droit
    }
    
    //Getter
    public JButton getBouton() {
        return linkCoursHome; //Ajout d'un lien vers un des onglet de JTabbedPane sur un JButton
    }
}
