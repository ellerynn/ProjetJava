package dao;

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
     * @param object
     * @return
     */
    public abstract T create(T object);

    /**
     * delete
     * @param object
     * @return
     */
    public abstract boolean delete(T object);

    /**
     * update
     * @param object
     * @return
     */
    public abstract T update(T object);
    
    /**
     * find
     * @param id
     * @return
     */
    public abstract T find(int id);
}