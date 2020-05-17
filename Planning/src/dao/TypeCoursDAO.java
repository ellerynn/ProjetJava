package dao;

import java.sql.Connection;
import java.sql.*;
import modele.TypeCours;

public class TypeCoursDAO extends DAO<TypeCours> {
    public TypeCoursDAO(Connection conn) {
      super(conn);
    } 
    
    @Override
    public boolean create(TypeCours object) {
        return false;
    }

    @Override
    public boolean delete(TypeCours object) {
        return false;
    }

    @Override
    public boolean update(TypeCours object) {
        return false;
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