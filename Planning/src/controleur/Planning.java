package controleur;

import controleur.ConnexionBDD;
import vue.*;
import java.sql.*;

public class Planning {
    public static void main(String[] args) {
        Fenetre fenetre = new Fenetre();
        try {
            ConnexionBDD c = new ConnexionBDD("edt","root","");
            System.out.println("BDD identifiée");
        }
        catch(SQLException sql){
           System.out.println("exception");
        }
        catch(ClassNotFoundException tm) {
           System.out.println("class exception");
        }
    }
}
