package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DateFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartPanel;

/**
 * https://codes-sources.commentcamarche.net/forum/affich-659587-date-formatee-jj-mm-aaaa-dans-jspinner
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */
public class OngletHome extends JSplitPane {
    //Variables onglet Home                     
    private JSpinner date;
    private JButton linkCours;
    private JTable tabEdt;
    private JPanel graphes;
    //private JButton boutonGraphe; //boutton graphe capacite des salles dans eiffel 1
    private TableLabelRendererPanel p;
    private JButton deconnexion;
    
    /**
     * constructeur
     */
    public OngletHome() {
        date = new JSpinner();
        linkCours = new JButton(); //Bouton
        tabEdt = new JTable(); //Tableau 
        p = new TableLabelRendererPanel(tabEdt);
        //boutonGraphe = new JButton ("Afficher la capacité des salles pour Eiffel 1");    
        deconnexion = new JButton();
        graphes = new JPanel();
               
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
        date.setEditor(new JSpinner.DateEditor(date, "dd/MM/yyyy"));
                
        System.out.print(DateFormat.getDateInstance(1).format(date.getValue())) ;
        
        c.gridx = 1;
        container1.add(date, c);
        
        linkCours.setIcon(new ImageIcon("images\\icon_redimensionner.png")); //Ajout d'une image dans le bouton
        c.gridx = 2;
        c.anchor = GridBagConstraints.LINE_END;
        c.fill = GridBagConstraints.NONE;
        linkCours.setPreferredSize(new Dimension(18,18));
        c.insets = new Insets(10,100,10,10);
        container1.add(linkCours, c);
        
        setEdt();
        c.insets = new Insets(10,10,10,10);
        c.gridwidth = 3;   //2 columns wide
        c.gridx = 0; c.gridy = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        container1.add(p, c);
                
        c.fill = GridBagConstraints.HORIZONTAL;
        this.setLeftComponent(container1);
        
        //Container de droite
        JPanel container2 = new JPanel();
        container2.setLayout(new GridBagLayout()); //Initialisation du container
        GridBagConstraints t = new GridBagConstraints(); //Contraintes d'ajout des composants
        t.fill = GridBagConstraints.BOTH;
        t.insets = new Insets(10,10,10,10);
        t.gridx = 0; t.gridy = 0;
        JLabel cursus = new JLabel("Graphes");
        container2.add(cursus, t); 
        
        t.gridx = 1;
        deconnexion.setIcon(new ImageIcon("images\\icon_deconnexion.png"));
        t.fill = GridBagConstraints.NONE;
        t.anchor = GridBagConstraints.LINE_END;
        t.weightx = 1;
        deconnexion.setPreferredSize(new Dimension(25,25));
        container2.add(deconnexion, t);
        
        t.gridy = 1; t.gridx = 0;
        t.fill = GridBagConstraints.BOTH;
        t.anchor = GridBagConstraints.FIRST_LINE_START;
        t.gridwidth = 2;
        t.weighty = 1;
        container2.add(graphes, t);
        
        this.setRightComponent(container2);
    }
    
    /**
     * @return le bouton-lien dans Home vers l'edt par semaine dans Cours
     */
    public JButton getBouton() {
        return linkCours; //Ajout d'un lien vers un des onglet de JTabbedPane sur un JButton
    }
    
    /**
     * @return le bouton de déconnexion dans Home
     */
    public JButton getBoutonDeco() {
        return deconnexion;
    }
    
    /**
     * @return le JSpinner contenant la date dans l'onglet Home
     */
    public JSpinner getDateHome() {
        return date;
    }
    
    /**
     * @return le Jtable contenant l'edt sur un jour dans l'onglet Home
     */
    public JTable getTabCoursHome() {
        return tabEdt;
    }
    
    /**
     * mise à jour de l'entête et de la première colonne de l'edt dans l'onglet Home
     */
    public void setEdt() {
        tabEdt.setModel(new DefaultTableModel(new Object [][] { {"08h00"},{"08h15"},{"08h30"},{"08h45"}, 
                                                                {"09h00"},{"09h15"},{"09h30"},{"09h45"}, 
                                                                {"10h00"},{"10h15"},{"10h30"},{"10h45"}, 
                                                                {"11h00"},{"11h15"},{"11h30"},{"11h45"}, 
                                                                {"12h00"},{"12h15"},{"12h30"},{"12h45"}, 
                                                                {"13h00"},{"13h15"},{"13h30"},{"13h45"}, 
                                                                {"14h00"},{"14h15"},{"14h30"},{"14h45"}, 
                                                                {"15h00"},{"15h15"},{"15h30"},{"15h45"}, 
                                                                {"16h00"},{"16h15"},{"16h30"},{"16h45"}, 
                                                                {"17h00"},{"17h15"},{"17h30"},{"17h45"}, 
                                                                {"18h00"},{"18h15"},{"18h30"},{"18h45"}, 
                                                                {"19h00"},{"19h15"},{"19h30"},{"19h45"}, 
                                                                {"20h00"},{"20h15"},{"20h30"},{"20h45"}},
                                                new String[]{ "Horaires", "Cours du jour"}) 
        { 
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        tabEdt.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (tabEdt.getColumnModel().getColumnCount() > 0) {
            tabEdt.getColumnModel().getColumn(0).setMaxWidth(55);
        }
        
        tabEdt.getTableHeader().setResizingAllowed(false); //On ne peut pas changer la taille des colonnes
        tabEdt.getTableHeader().setReorderingAllowed(false); //On ne peut pas échanger les colonnes de place
        tabEdt.setShowHorizontalLines(false); //On n'affiche pas les lignes horizontales
        
        DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
        custom.setHorizontalAlignment(JLabel.CENTER);
        tabEdt.getColumnModel().getColumn(0).setCellRenderer(custom);
    }    

    /**
     * ajout des graphes dans l'onglet Home
     * @param c
     */
    void ajouterGraphes(ArrayList<ChartPanel> c, ArrayList<ChartPanel> t) {
        graphes.removeAll();
        graphes.setLayout(new GridBagLayout()); //Initialisation du container
        GridBagConstraints g = new GridBagConstraints(); //Contraintes d'ajout des composants
        g.fill = GridBagConstraints.BOTH;
        //t.insets = new Insets(10,10,10,10);
        g.gridx = 0; g.gridy = 0;
        //t.anchor = GridBagConstraints.FIRST_LINE_START;
        g.weightx = 1; g.weighty = 1;
        
        graphes.setBackground(Color.yellow);
        
        for(int i=0;i<c.size();i++) {
            graphes.add(c.get(i), g);
            g.gridx++;
            System.out.println("gridx = " + g.gridx);
        }
        
        g.gridy = 1; g.gridx = 0;
        for(int i=0;i<t.size();i++) {
            graphes.add(t.get(i), g);
            g.gridx++;
            System.out.println("gridx = " + g.gridx);
        }
    }
}
