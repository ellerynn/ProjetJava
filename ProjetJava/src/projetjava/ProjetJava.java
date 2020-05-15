package projetjava;

import java.sql.SQLException;
import vue.*;

public class ProjetJava {
    public static void main(String[] args) {
        //MaFenetre fenetre = new MaFenetre();
        try {
            Connexion c = new Connexion("edt","root","");
        }
        catch(SQLException sql){
           System.out.println("exception");
        }
        catch(ClassNotFoundException tm) {
           System.out.println("class exception");
        }
    }
}
