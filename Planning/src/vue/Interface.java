/*
    private JPanel coursPan;
    private JPanel edtPan;
    private JPanel edtPan1;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane3;
    private JScrollPane jScrollPane4;
    private JScrollPane jScrollPane5;
    private JTabbedPane panSalles;
    private JPanel recapCoursPan;
    private JPanel recapCoursPan1;*/
package vue;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class Interface extends JPanel {
    //Panneau principal, plusieurs onglets
    private JTabbedPane tabbedPane;
    //Onglet Home
    private JPanel paneHome;
    private JTextField edtTextHome;
    private JSpinner dateHome;
    private JButton iconeHome;
    private JScrollPane scrollPaneHome;
    private JTable tabCoursHome;
    private JTextField cursusTextHome;
    //Onglet Cours
    private JTabbedPane tabbedPaneCours;
    private JPanel paneCours;
    private JComboBox<String> vueEdt;
    private JComboBox<String> rechercheCours;
    private JTextField rechercheBarreCours;
    private JButton rechercheBoutonCours;
    private JComboBox<String> semaineCours;
    private JScrollPane scrollPaneCours;
    private JTable tabCours;
    private JScrollPane scrollPaneRecapCours;
    private JTable tabRecapCours;
    //Onglet Salles
    private JPanel paneSalle;
    private JComboBox<String> vueSalle;
    private JComboBox<String> rechercheSalle;
    private JTextField rechercheBarreSalle;
    private JButton rechercheBoutonSalle;
    private JComboBox<String> semaineSalle;
    private JScrollPane scrollPaneSalle;
    private JTable tabSalle;
    private JScrollPane scrollPaneRecapSalle;
    private JTable tabLibresSalle;
    
    public Interface() {
        initialisation();
    }
    
    private void initialisation() {
        tabbedPane = new JTabbedPane();
        //Home
        paneHome = new JPanel();
        cursusTextHome = new JTextField("Mon cursus");
        edtTextHome = new JTextField("Emploi du temps");
        dateHome = new JSpinner();
        iconeHome = new JButton("Icon");
        scrollPaneHome = new JScrollPane();
        tabCoursHome = new JTable();
        //Cours
        tabbedPaneCours = new JTabbedPane();
        paneCours = new JPanel();
        vueEdt = new JComboBox<>();
        rechercheCours = new JComboBox<>();
        rechercheBarreCours = new JTextField();
        rechercheBoutonCours = new JButton();
        semaineCours = new JComboBox<>();
        scrollPaneCours = new JScrollPane();
        tabCours = new JTable();
        scrollPaneRecapCours = new JScrollPane();
        tabRecapCours = new JTable();
        //Salle
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
        
        //Conditions d'affichage
        cursusTextHome.setEditable(false); //L'utilisateur ne peut pas le modifier
        cursusTextHome.setHorizontalAlignment(JTextField.CENTER); //Texte centré
        
        edtTextHome.setEditable(false); //L'utilisateur ne peut pas le modifier
        
        dateHome.setModel(new SpinnerDateModel());
        
        scrollPaneHome.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        tabCoursHome.setModel(new DefaultTableModel(new Object [][] {}, new String [] {
                "Horaires", "Cours"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPaneHome.setViewportView(tabCoursHome);
    }
}
