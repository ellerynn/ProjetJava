package dao;

import java.sql.Connection;
import java.sql.*;
import modele.Site;
import modele.TypeCours;

public class TypeCoursDAO extends DAO<TypeCours> {
    @Override
    public TypeCours create(TypeCours object) {
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
}