package controleur;

//Librairies importées
import java.sql.*;
import java.util.ArrayList;
import java.sql.SQLException;

public class ConnexionBDD {
    //attribut privé
    private Connection con;
    private Statement stat;
    private ResultSet rst;
    private ResultSetMetaData rsmd;

    //array list pour les tables
    public ArrayList<String> tables = new ArrayList<>();//array selection
    public ArrayList<String> requetes = new ArrayList<>();//array requete selection
    public ArrayList<String> requetesMaj = new ArrayList<>();//array requete maj

    /**
     * 3 parametre pour login dans bdd: nom, login et password
     *
     * @param nameDatabase
     * @param loginDatabase
     * @param passwordDatabase
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public ConnexionBDD(String nameDatabase, String loginDatabase, String passwordDatabase) throws SQLException, ClassNotFoundException {
        //chargement du driver
        Class.forName("com.mysql.jdbc.Driver");
        //url de connexion : Camille 3307, Sutharsan 330?, Emilie 330?
        String urlDatabase = "jdbc:mysql://localhost:3307/" + nameDatabase;
        //création connexion JDBC a la base
        con = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);
        //creation ordre SQL
        stat = con.createStatement();
    }

    /**
     * methode ajout table en paramtre arraylist
     *
     * @param table
     */
    public void ajouterTable(String table) {
        tables.add(table);
    }

    /**
     * methode ajoute requete selection en parametre Arraylist
     *
     * @param requete
     */
    public void ajouterRequete(String requete) {
        requetes.add(requete);
    }

    /**
     * methode ajoute requete maj en parametre Arraylist
     *
     * @param requete
     */
    public void ajouterRequeteMaj(String requete) {
        requetesMaj.add(requete);
    }
}

