package modele;

import controleur.ConnexionBDD;
import java.sql.Connection;

/**
 * https://openclassrooms.com/fr/courses/26832-apprenez-a-programmer-en-java/26830-liez-vos-tables-avec-des-objets-java-le-pattern-dao?fbclid=IwAR2bB6wquWfT2fv42CgT5r9Hmc6c4He3MgjfQYwtIcK6ItSmwxg7cbQkf8c
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 * @param <T>
 */
public abstract class DAO<T> {

    /**
     * connexion à la BDD
     */
    public Connection connect = ConnexionBDD.getInstance();

    /**
     * create
     * @param object Objet à créer dans la BDD
     * @return Retourne l'objet trouvé
     */
    public abstract T create(T object);

    /**
     * delete
     * @param object Objet à supprimer dans la BDD
     * @return Retourne un boolean qui indique si l'objet a été supprimé
     */
    public abstract boolean delete(T object);

    /**
     * update
     * @param object Objet à MAJ dans la BDD
     * @return Retourne l'objet qui a été mise à jours
     */
    public abstract T update(T object);
    
    /**
     * find
     * @param id Id de l'objet à trouver dans la BDD
     * @return Retourne l'objet qui a été trouvé
     */
    public abstract T find(int id);
}