package dao;

//source open classroom
//https://openclassrooms.com/fr/courses/26832-apprenez-a-programmer-en-java/26830-liez-vos-tables-avec-des-objets-java-le-pattern-dao?fbclid=IwAR2bB6wquWfT2fv42CgT5r9Hmc6c4He3MgjfQYwtIcK6ItSmwxg7cbQkf8c

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.sql.SQLException;

public abstract class DAO<T> {
  protected Connection connect = null;
   
  public DAO(Connection con){
    this.connect = con;
  }
   
  /**
  * Méthode création
  * @param object
  * @return boolean 
  */
  public abstract boolean create(T object);

  /**
  * Méthode  effacer
  * @param object
  * @return boolean 
  */
  public abstract boolean delete(T object);

  /**
  * Méthode maj
  * @param object
  * @return boolean
  */
  public abstract boolean update(T object);

  /**
  * Méthode recherche info
  * @param id
  * @return T
  */
  public abstract T find(int id);
}