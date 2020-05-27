package dao;

import java.sql.Connection;
import java.sql.*;
import modele.Site;
import modele.TypeCours;
import java.util.ArrayList;

public class TypeCoursDAO extends DAO<TypeCours> {
    @Override
    public TypeCours create(TypeCours object) {
        try{
            //On insère les nouvelles données dans la BDD
            PreparedStatement requete = this.connect
                                            .prepareStatement(
                                                "INSERT INTO type_cours (Nom) VALUES(?)"
                                            );
            requete.setString(1, object.getNom());
            requete.executeUpdate();
            
            //On récupère l'Id
             ResultSet result = connect.createStatement().executeQuery("SELECT MAX(ID) FROM type_cours");
             if (result.first())
             {
                 //On récupère tout les données lié à cette objet pour être sûr qu'on a tous
                 object = this.find(result.getInt("MAX(ID)"));
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public boolean delete(TypeCours object) {
        return false;
    }

    @Override
    public TypeCours update(TypeCours object) {
        try {

            this.connect	
                 .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                    "UPDATE type_cours SET Nom = '" + object.getNom() + "'"+
                    " WHERE ID = " + object.getId()
                 );
                object = this.find(object.getId());
                
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return object;
    }
    
    @Override
    public TypeCours find(int id) {
        TypeCours typecours = new TypeCours();      

        try {
            Statement st;
            ResultSet result;
            //creation ordre SQL
            st = connect.createStatement();

            result = st.executeQuery("SELECT * FROM type_cours WHERE ID = " + id);
            if(result.first())
            {
                typecours.setId(result.getInt("ID"));
                typecours.setNom(result.getString("Nom"));
            }
        }
        catch (SQLException e) {
          e.printStackTrace();
          System.out.println("pas trouvé");
        }
        return typecours;
    }
    
    //Trouver tous les types de cours
    //Pour admin
    public ArrayList<TypeCours> findAllTypes()
    {
        ArrayList<TypeCours> tcours = new ArrayList<>();
        try {
            ResultSet result=connect.createStatement().executeQuery("SELECT DISTINCT ID FROM type_cours ORDER BY ID"); // Récup tout types
            
                while(result.next()) {
                    tcours.add(find(result.getInt("ID")));
                }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé");
        }
        return tcours;
    }
}