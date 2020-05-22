package vue;

//https://bbclone.developpez.com/fr/java/tutoriels/uiswing/gridbaglayout/?page=page_2

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class OngletGererCoursSP extends JSplitPane {
    //Ajouter une seance
    private JList<String> listeSeances;
    private JSpinner date;
    private JRadioButton etatEC;
    private JRadioButton etatV;
    private JComboBox<String> selectCours;
    private JComboBox<String> selectType;
    private JList<String> listeEnseignants;
    private JList<String> listeGroupes;
    private JList<String> listeSalles;
    private JButton valider;
    //Modifier une seance
    private JList<String> listeSeances2;
    private JSpinner date2;
    private JRadioButton etatEC2;
    private JRadioButton etatV2;
    private JRadioButton etatA;
    private JComboBox<String> selectCours2;
    private JComboBox<String> selectType2;
    private JList<String> listeEnseignants2;
    private JList<String> listeGroupes2;
    private JList<String> listeSalles2;
    private JButton valider2;
    //Ajouter un cours
    private JTextField intitule;
    private JButton valider3;
    //Supprimer un cours
    private JComboBox<String> selectCours3;
    private JButton supprimer;
    
    public OngletGererCoursSP() {
        listeSeances = new JList<>();
        date = new JSpinner();
        etatEC = new JRadioButton("En cours de validation");
        etatV = new JRadioButton("Validée");
        selectCours = new JComboBox<>();
        selectType = new JComboBox<>();
        listeEnseignants = new JList<>();
        listeGroupes = new JList<>();
        listeSalles = new JList<>();
        valider = new JButton("Valider");
        
        listeSeances2 = new JList<>();
        date2 = new JSpinner();
        etatEC2 = new JRadioButton("En cours de validation");
        etatV2 = new JRadioButton("Validée");
        etatA = new JRadioButton("Annulée");
        selectCours2 = new JComboBox<>();
        selectType2 = new JComboBox<>();
        listeEnseignants2 = new JList<>();
        listeGroupes2 = new JList<>();
        listeSalles2 = new JList<>();
        valider2 = new JButton("Valider");
        
        intitule = new JTextField();
        valider3 = new JButton("Valider");
        
        selectCours3 = new JComboBox<String>();
        supprimer = new JButton("Supprimer");
                
        //Liste des séances a droite
        JScrollPane container1 = new JScrollPane(listeSeances);
        remplirListe(listeSeances);
        this.setRightComponent(container1);
        
        //A gauche
        JPanel container2 = new JPanel();
        container2.setLayout(new GridBagLayout()); //Initialisation du container
        GridBagConstraints c = new GridBagConstraints(); //Contraintes d'ajout des composants
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 1;
        
        /***********************************AJOUTER UNE SEANCE***********************************/
        c.insets = new Insets(0,10,0,0);
        JLabel titre1 = new JLabel("Ajouter une seance"); //JLabel titre
        
        c.gridx = 0; c.gridy = 0; //Position
        container2.add(titre1, c); //Ajout au conteneur
        
        c.insets = new Insets(0,0,0,0);
        JLabel dateHeure = new JLabel("Date et heure :"); //Sous-titre
        c.gridx = 1; c.gridy = 1; //On décalle tout de 1
        container2.add(dateHeure, c);
        
        c.gridx = 2; //On décalle juste la position en x -> alignement avec sous-titre
        date.setModel(new SpinnerDateModel(new Date(1598940000000L), new Date(1598940000000L), new Date(1627797600000L), Calendar.DAY_OF_MONTH));
        container2.add(date, c);
        
        JLabel etats = new JLabel("Etat :");
        c.gridy = 2; c.gridx  = 1;
        container2.add(etats, c);
        
        ButtonGroup groupeEtat = new ButtonGroup();
        groupeEtat.add(etatEC);
        groupeEtat.add(etatV);
        
        c.gridx  = 2;
        container2.add(etatEC, c);
        c.gridx = 3;
        container2.add(etatV, c);
        
        JLabel cours = new JLabel("Cours :");
        c.gridy = 3; c.gridx = 1;
        container2.add(cours, c);
        
        c.gridx = 2;
        remplirComboBox(selectCours, "Veuillez sélectionner", "Cours");
        container2.add(selectCours, c);
        
        JLabel type = new JLabel("Type du cours : ");
        c.gridy = 4; c.gridx = 1;
        container2.add(type, c);
        
        c.gridx = 2;
        remplirComboBoxType(selectType);
        container2.add(selectType, c);
        
        c.gridy = 5; c.gridx = 1;
        JLabel profs = new JLabel("Enseignants : ");
        container2.add(profs, c);
        
        c.gridx = 2;
        JLabel tds = new JLabel("Groupes : ");
        container2.add(tds, c);
        
        c.gridx = 3;
        JLabel salles = new JLabel("Salles : ");
        container2.add(salles, c);
        
        JScrollPane container3 = new JScrollPane(listeEnseignants);
        listeEnseignants.setVisibleRowCount(2);
        remplirListe(listeEnseignants);
	c.insets = new Insets(5,0,10,10);  //padding
	c.gridx = 1;       //aligned with date
	c.gridwidth = 1;   //2 columns wide
        c.gridy = 6;       //third row
        container2.add(container3, c);
        
        JScrollPane container4 = new JScrollPane(listeGroupes);
        listeGroupes.setVisibleRowCount(2);
        remplirListe(listeGroupes);
	c.gridx = 2;       //aligned with date
        container2.add(container4, c);
        
        JScrollPane container5 = new JScrollPane(listeSalles);
        listeSalles.setVisibleRowCount(2);
        remplirListe(listeSalles);
        c.gridwidth = 2;
	c.gridx = 3;       //aligned with date
        container2.add(container5, c);
        
        c.gridwidth = 1;  
        c.gridx = 7;
        c.gridy = 6;
        c.insets = new Insets(0,0,10,10);
        container2.add(valider, c);
        
        c.insets = new Insets(0,0,0,0);  //padding
        
        /***********************************MODIFIER UNE SEANCE***********************************/
        c.insets = new Insets(0,10,0,0);
        JLabel titre2 = new JLabel("Modifier une seance"); //JLabel titre

        c.gridx = 0; c.gridy = 7; //Position
        container2.add(titre2, c); //Ajout au conteneur
        
        c.insets = new Insets(0,0,0,0);
        JLabel dateHeure2 = new JLabel("Date et heure :"); //Sous-titre
        c.gridx = 1; c.gridy = 8; //On décalle tout de 1
        container2.add(dateHeure2, c);
        
        c.gridx = 2; //On décalle juste la position en x -> alignement avec sous-titre
        date2.setModel(new SpinnerDateModel(new Date(1598940000000L), new Date(1598940000000L), new Date(1627797600000L), Calendar.DAY_OF_MONTH));
        container2.add(date2, c);
        
        JLabel etats2 = new JLabel("Etat :");
        c.gridy = 9; c.gridx  = 1;
        container2.add(etats2, c);
        
        ButtonGroup groupeEtat2 = new ButtonGroup();
        groupeEtat2.add(etatEC2);
        groupeEtat2.add(etatV2);
        groupeEtat2.add(etatA);
        
        c.gridx  = 2;
        container2.add(etatEC2, c);
        c.gridx = 3;
        container2.add(etatV2, c);
        c.gridx = 4;
        container2.add(etatA, c);
        
        JLabel cours2 = new JLabel("Cours :");
        c.gridy = 10; c.gridx = 1;
        container2.add(cours2, c);
        
        c.gridx = 2;
        remplirComboBox(selectCours2, "Veuillez sélectionner", "Cours");
        container2.add(selectCours2, c);
        
        JLabel type2 = new JLabel("Type du cours : ");
        c.gridy = 11; c.gridx = 1;
        container2.add(type2, c);
        
        c.gridx = 2;
        remplirComboBoxType(selectType2);
        container2.add(selectType2, c);
        
        c.gridy = 12; c.gridx = 1;
        JLabel profs2 = new JLabel("Enseignants : ");
        container2.add(profs2, c);
        
        c.gridy = 12; c.gridx = 2;
        JLabel tds2 = new JLabel("Groupes : ");
        container2.add(tds2, c);
        
        c.gridy = 12; c.gridx = 3;
        JLabel salles2 = new JLabel("Salles : ");
        container2.add(salles2, c);
        
        JScrollPane container6 = new JScrollPane(listeEnseignants2);
        listeEnseignants2.setVisibleRowCount(2);
        remplirListe(listeEnseignants2);
	c.insets = new Insets(5,0,10,10);  //padding
	c.gridx = 1;       //aligned with date
        c.gridy = 13;       //third row
        container2.add(container6, c);
        
        JScrollPane container7 = new JScrollPane(listeGroupes2);
        listeGroupes2.setVisibleRowCount(2);
        remplirListe(listeGroupes2);
	c.gridx = 2;       //aligned with date
        container2.add(container7, c);
        
        JScrollPane container8 = new JScrollPane(listeSalles2);
        listeSalles2.setVisibleRowCount(2);
        remplirListe(listeSalles2);
        c.gridwidth = 2;   //2 columns wide
	c.gridx = 3;       //aligned with date
        container2.add(container8, c);  
        
        c.gridwidth = 1;  
        c.gridx = 7;
        c.gridy = 13;
        c.insets = new Insets(0,0,10,10);
        container2.add(valider2, c);
        
        c.insets = new Insets(0,10,0,0);  //padding
        
        /***********************************AJOUTER UN COURS***********************************/
        JLabel titre3 = new JLabel("Ajouter un cours"); //JLabel titre

        c.gridx = 0; c.gridy = 14; //Position
        container2.add(titre3, c); //Ajout au conteneur
        
        c.insets = new Insets(0,0,0,0);
        JLabel cours3 = new JLabel("Intitulé du cours : "); //Sous-titre
        c.gridx = 1; c.gridy = 15;  //On décalle tout de 1
        container2.add(cours3, c);
        
        c.gridx = 2; c.gridwidth = 4;
        c.insets = new Insets(0,0,0,10);
        container2.add(intitule, c);
        
        c.gridwidth = 1;  
        c.gridx = 7;
        c.gridy = 15;
        c.insets = new Insets(0,0,0,10);
        container2.add(valider3, c);
        
        /***********************************SUPPRIMER UN COURS***********************************/
        c.insets = new Insets(0,10,0,0);
        JLabel titre4 = new JLabel("Supprimer un cours"); //JLabel titre

        c.gridx = 0; c.gridy = 16; //Position
        container2.add(titre4, c); //Ajout au conteneur
        
        c.insets = new Insets(0,0,0,0);
        remplirComboBox(selectCours3, "Veuillez sélectionner", "Cours");
        c.gridx = 1; c.gridy = 17; c.gridwidth = 1; //On décalle tout de 1
        container2.add(selectCours3, c);
        
        c.gridwidth = 1;  
        c.gridx = 7;
        c.gridy = 17;
        c.insets = new Insets(0,0,0,10);
        container2.add(supprimer, c);
                
        this.setLeftComponent(container2);
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
