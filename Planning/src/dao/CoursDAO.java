/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import modele.Cours;
import modele.Seance;

public class CoursDAO extends DAO<Cours> {
    @Override
    public Cours create(Cours object) {
        try{
            //On insère les données dans la BDD
            PreparedStatement requete = this.connect
                                            .prepareStatement(
                                                "INSERT INTO cours (Nom) VALUES(?)"
                                            );
            requete.setString(1, object.getNom());
            requete.executeUpdate();
            
            //On récupère l'id
            ResultSet result = connect.createStatement().executeQuery("SELECT MAX(ID) FROM cours");
            if (result.first())
            {
                //On récupère tout les données lié à cette objet pour être sûr qu'on a tous
                object = this.find(result.getInt("MAX(ID)")); //On récupère les nouvelles données
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public boolean delete(Cours object) {
        return false;
    }

    @Override
    public Cours update(Cours object) {
        try {

            this.connect	
                 .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                    "UPDATE cours SET Nom = '" + object.getNom() + "'"+
                    " WHERE ID = " + object.getId()
                 );
                object = this.find(object.getId());
                
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return object;
    }
    
    @Override
    public Cours find(int id) {
        Cours cours = new Cours();      

        try {
            Statement st;
            ResultSet result;
            //creation ordre SQL
            st = connect.createStatement();

            result = st.executeQuery("SELECT * FROM cours WHERE ID = " + id);
            if(result.first())
            {
                cours.setId(result.getInt("ID"));
                cours.setNom(result.getString("Nom"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé");
        }
        return cours;
    }
    /*methodes en plus pour ADMINISTRATEUR*/
    public ArrayList<Cours> findAllCours()
    {
        ArrayList<Cours> cours = new ArrayList<>();
        try {
            ResultSet result=connect.createStatement().executeQuery("SELECT DISTINCT ID FROM cours ORDER BY ID"); // Récup tout cours
            
                while(result.next()) {
                    cours.add(find(result.getInt("ID")));
                }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé");
        }
        return cours;
    }
}