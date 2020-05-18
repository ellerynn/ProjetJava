package controleur;

import vue.*;
import java.sql.*;

import modele.*;
import dao.*;
import java.awt.event.*;

public class Planning implements ActionListener {
    public static void main(String[] args) {
        //Connexion BDD
        Connection con;
        String nameDatabase = "edt";
        String loginDatabase = "root";
        String passwordDatabase = "";    
        
        //Ouverture interface graphique
        Fenetre fenetre = new Fenetre();
        String email = new String();
        String password = new String();
        //La fenetre ecoute le bouton connexion
        //fenetre.getBoutonConnexion().addActionListener((ActionListener)fenetre);
        fenetre.getBoutonConnexion().addActionListener((ActionEvent evt) -> {
            //email = fenetre.getConnexion().getEmail();
        });

        //UTILISATEURDAO recuperation de toutes les données
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        Utilisateur uti = utilisateurDAO.find("etudiant3@edu.ece.fr","etudiant");

        System.out.println("l'id : " + uti.getId());
        System.out.println("le mail : " + uti.getEmail());
        System.out.println("le password : " + uti.getPassword());
        System.out.println("le nom : " + uti.getNom());
        System.out.println("le prenom : " + uti.getPrenom());
        System.out.println("le droit : " + uti.getDroit());
        System.out.println("***********************************5");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
