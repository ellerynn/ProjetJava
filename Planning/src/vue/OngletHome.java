package vue;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class OngletHome extends JSplitPane {
    //Variables onglet Home                     
    private JSpinner date;
    private JButton linkCours;
    private JTable tabEdt;
    
    public OngletHome() {
        date = new JSpinner();
        linkCours = new JButton(); //Bouton
        tabEdt = new JTable(); //Tableau 
              
        //Gauche
        JPanel container1 = new JPanel();
        container1.setLayout(new GridBagLayout()); //Initialisation du container
        GridBagConstraints c = new GridBagConstraints(); //Contraintes d'ajout des composants
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,10,10,10);
        
        JLabel edt = new JLabel("Emploi du temps");
        c.gridx = 0; c.gridy = 0;
        container1.add(edt, c);
        
        date.setModel(new SpinnerDateModel()); //Contient une date
        c.gridx = 1;
        container1.add(date, c);
        
        linkCours.setIcon(new ImageIcon("images\\icon_redimensionner.png")); //Ajout d'une image dans le bouton
        c.gridx = 2;
        c.anchor = GridBagConstraints.LINE_END;
        c.fill = GridBagConstraints.NONE;
        linkCours.setPreferredSize(new Dimension(18,18));
        c.insets = new Insets(10,100,10,10);
        container1.add(linkCours, c);
        
        c.insets = new Insets(10,10,10,10);
        c.gridwidth = 3;   //2 columns wide
        c.gridx = 0; c.gridy = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        
        tabEdt.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Horaires", "Cours du jour"})); //Contenu = Objet, entêtes = String
        tabEdt.getTableHeader().setReorderingAllowed(false);
        
        container1.add(new JScrollPane(tabEdt), c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        this.setLeftComponent(container1);
        
        //Droite
        JPanel container2 = new JPanel();
        
        JLabel cursus = new JLabel("Graphes");
        container2.add(cursus, c);
        
        this.setRightComponent(container2);
    }
    
    //Getter    
    public JButton getBouton() {
        return linkCours; //Ajout d'un lien vers un des onglet de JTabbedPane sur un JButton
    }
    
    public JSpinner getDateHome() {
        return date;
    }
    
    public JTable getTabCoursHome() {
        return tabEdt;
    }
}
