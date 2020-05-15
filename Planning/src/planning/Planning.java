package planning;

import java.sql.SQLException;
import vue.*;

public class Planning {
    public static void main(String[] args) {
        MaFenetre fenetre = new MaFenetre();
        try {
            ConnexionBDD c = new ConnexionBDD("edt","root","");
        }
        catch(SQLException sql){
           System.out.println("exception");
        }
        catch(ClassNotFoundException tm) {
           System.out.println("class exception");
        }
    }
}
