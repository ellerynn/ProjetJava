/*SOURCES :
*http://www.codeurjava.com/2015/05/comment-dimensionner-fenetre-selon-ecran.html
*https://openclassrooms.com/fr/courses/26832-apprenez-a-programmer-en-java/23108-creez-votre-premiere-fenetre
*/
package vue;

import controleur.Controle;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*; 
import javax.swing.border.Border;
import javax.swing.table.*;
import modele.Seance;

//La classe Fenetre correspond a toute l'interface graphique contenant la page de connexion et le planning (+gestion)
public class Fenetre extends JFrame {  
    /*Attributs*/
    private FormConnexion connexion; //Page de connexion de l'utilisateur
    private EmploiDuTemps edt; //Planning (accessible après connexion avec un CardLayout)
    private Controle controle; //Lien avec le controle
    
    /*Constructeur*/      
    public Fenetre(Controle controle) { 
        this.controle = controle;
        connexion = new FormConnexion();
        edt = new EmploiDuTemps();
        
        //Autres déclarations
        CardLayout c = new CardLayout(); //CardLayout pour "superposer" plusieurs pages (conteneurs)
        JPanel content = new JPanel(); //Contenu actif du CardLayout
        Calendar cal = Calendar.getInstance();  //Date du jour  
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Récupérer la taille de l'écran
        this.setSize(screenSize.width*2/3, screenSize.height*2/3); //Donne une taille en hauteur et largeur à la fenêtre -> 2/3 de l'écran       
        this.setLocationRelativeTo(null); //Positionner au centre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Termine le processus lorsqu'on clique sur la croix rouge
        this.setTitle("Connexion"); //Titre de la frame
                
        content.setLayout(c); //On définit le layout
        content.add(connexion); //On ajoute les conteneurs à la pile
        content.add(edt);

        this.getContentPane().add(content, BorderLayout.CENTER); //Affichage contenu actif
        this.setVisible(true);  
        
        //TRICHE CO RAPIDE
        connexion.setEmailPassWord("nous@edu.ece.fr", "etudiant");
        
        //Listeners
        connexion.getBouton().addActionListener((ActionEvent event) -> { //Définition de l'action du bouton connexion
            if(controle.demandeConnexion()) { 
                c.next(content); //Via cette instruction, on passe au prochain conteneur de la pile
                setTitle("Planning, " + calculAnneeScolaire() + " - " + utilisateurCourant() + " (ECE Paris " + recupInfo() + ")"); //Nouveau titre de la frame
                edt.setEdtCours(cal.get(Calendar.WEEK_OF_YEAR));
                        System.out.println(cal.get(Calendar.WEEK_OF_YEAR));
                seancesEdt(cal.get(Calendar.WEEK_OF_YEAR));
            }     
        });
        
       edt.getSemaineCours().addActionListener((ActionEvent event) -> {
            //On récupère la semaine sélectionnée
            String semaine = edt.getSemaineCours().getSelectedItem().toString();
            if (semaine == "Semaine") {
                edt.setEdtCours(cal.get(Calendar.WEEK_OF_YEAR));
                seancesEdt(cal.get(Calendar.WEEK_OF_YEAR));
            }
            
            else {
                edt.setEdtCours(Integer.parseInt(semaine));
                seancesEdt(Integer.parseInt(semaine));
            }
            
       });
       
       edt.getSemaineSalles().addActionListener((ActionEvent event) -> {
            //On récupère la semaine sélectionnée
            String semaine = edt.getSemaineSalles().getSelectedItem().toString();
            System.out.println(edt.getSemaineSalles().getSelectedItem().toString());
            if (semaine.equals("Semaine")) 
                edt.setEdtSalles(cal.get(Calendar.WEEK_OF_YEAR));
            
            else
                edt.setEdtSalles(Integer.parseInt(semaine));
            
       });
    }	  
    
    //Getters    
    public FormConnexion getConnexion() {
        return connexion;
    }
    
    public EmploiDuTemps getEDT() {
        return edt;
    }
    
    //Méthodes    
    public String calculAnneeScolaire() { //Pour affichage dans titre de la frame
        Calendar cal = Calendar.getInstance(); //Date du jour
        int annee = cal.get(Calendar.YEAR); //Année courante
        if(cal.get(Calendar.MONTH)+1 >= 9 && cal.get(Calendar.MONTH)+1 <= 12) //Entre septembre et décembre
            return annee + "/" + (annee+1);
        return (annee-1) + "/" + annee;
    }
    
    public String utilisateurCourant() { //Pour affichage dans titre de la frame
        return controle.getUtilisateur().getNom() + " " + controle.getUtilisateur().getPrenom();
    }
    
    public String recupInfo() { //Pour affichage dans titre de la frame
        String info = new String();
        //Si etudiant -> Promo
        if(controle.getUtilisateur().getDroit() == 4) {
            //Récupérer controle, puis utilisateur, groupe, puis promotion
            info = controle.getEtudiant().getGroupe().getPromotion().getNom();
        }
        //Si enseignant -> "Enseignant"
        if(controle.getUtilisateur().getDroit() == 3) {
            info = "Enseignant";
        }
        //Si référent pédagogique -> "Enseignant - Référent pédagogique"
        if(controle.getUtilisateur().getDroit() == 2) {
            info = "Enseignant - Référent pédagogique";
            rechercheVisible();
        }
        //Si admin -> "Administrateur"
        if(controle.getUtilisateur().getDroit() == 1) {
            info = "Administrateur";
            edt.addOngletServicePlanification(); //On ajoute l'onglet administration = service planification
            rechercheVisible();
        }
        
        return info;
    }
    
    public void rechercheVisible() {
        edt.getRechercheBarreCours().setVisible(true);
        edt.getRechercheBoutonCours().setVisible(true);
        edt.getRechercheCours().setVisible(true);
    }
    
    public void seancesEdt(int semaine) {
        controle.recupSeances(semaine);
        
        ArrayList<Seance> seances = new ArrayList<>();
        ArrayList<String> strSeances = new ArrayList<>();
        
        //Récup des séances de mon utilisateur selon profil
        if(controle.getUtilisateur().getDroit() == 3) { //Enseignant
            seances = controle.getEnseignant().getSeances();
        }
            
        if(controle.getUtilisateur().getDroit() == 4) { //Etudiant
            seances = controle.getEtudiant().getSeances();
            for(int i=0;i<seances.size();i++) 
                System.out.println(seances.get(i).toString());
        }
        
        //Trouver la ligne -> HEURE - Récuperer string, comparer a ce qui est dans seances de l'utilisateur
        int ligne1 = 0; int ligne2 = 0; int colonne = 0; 
        
        for(int j=0;j<seances.size();j++) { //Pour toutes les seances
            String heure1 = seances.get(j).getHeureDebut();
            System.out.println("Heure début de la seance " + heure1);
            
            String heure2 = seances.get(j).getHeureFin();
            System.out.println("Heure fin de la seance " + heure2);
            
            //Convertir l'heure1BDD en heureEdt : 12:00:00 -> 12h00
            String heure1BDD = heure1.substring(0, 2) + "h" + heure1.substring(3, 5); 
            System.out.println(heure1BDD);
            
            //Convertir l'heure2BDD en heureEdt : 12:00:00 -> 12h00
            String heure2BDD = heure2.substring(0, 2) + "h" + heure2.substring(3, 5); 
            System.out.println(heure2BDD);
            
            String date = seances.get(j).getDate();
            System.out.println(date); //AAAA-MM-JJ
                
            //Convertir la dateBDD en jour
            String jourBDD = date.substring(8, 10); 
            System.out.println(jourBDD);
            
            //LIGNE DEBUT
            for(int i=0;i<edt.getEdtCours().getRowCount();i++) { 
                String heureEdt = edt.getEdtCours().getValueAt(i, 0).toString(); //08h00 dans EDT -> 08:00:00 dans BDD
                System.out.println(heureEdt);
                
                if(heureEdt.equals(heure1BDD)) { //Si l'heure correspond, récupérer la ligne
                    System.out.println("DEBUT - Ces deux heures sont pareilles : " + heure1BDD + " et " + heureEdt);
                    ligne1 = i;
                }
            }
            
            //LIGNE FIN
            for(int i=0;i<edt.getEdtCours().getRowCount();i++) { 
                String heureEdt = edt.getEdtCours().getValueAt(i, 0).toString(); //08h00 dans EDT -> 08:00:00 dans BDD
                System.out.println(heureEdt);
                
                if(heureEdt.equals(heure2BDD)) { //Si l'heure correspond, récupérer la ligne
                    System.out.println("FIN - Ces deux heures sont pareilles : " + heure2BDD + " et " + heureEdt);
                    ligne2 = i;
                }
            }
            
            for(int i=0;i<edt.getEdtCours().getColumnCount();i++) { //Pour chaque ligne
                String entete = edt.getEdtCours().getModel().getColumnName(i);
                System.out.println(entete);

                String jourEdt = entete.substring(5, 7);
                System.out.println(jourEdt);
                
                if(jourEdt.equals(jourBDD)) { //Si l'heure correspond, récupérer la ligne
                    System.out.println("Ces deux jour sont pareils : " + jourBDD + " et " + jourEdt);
                    colonne = i;
                }
            }
            
            //Un cours dure forcément 1h30 = 6 cases
            strSeances = seances.get(j).toArrayListOfString();
            //RAPPEL cf. méthode dans Seance.java
            //seance[0] = etat ; seance[1] = intitulé du cours; seance[2] = enseignants ; 
            //seance[3] = groupes; seance[4] = salles; seance[5] = type du cours;
            
            edt.getEdtCours().setShowHorizontalLines(false);
             
            
            
            if(colonne != 0)  {
                for(int i=0;i<6;i++) {
                    edt.getEdtCours().setValueAt(strSeances.get(i), ligne1+i, colonne);
                }
            }
        }
    }
    
    
}