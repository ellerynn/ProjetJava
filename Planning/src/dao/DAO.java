package dao;

//source open classroom
//https://openclassrooms.com/fr/courses/26832-apprenez-a-programmer-en-java/26830-liez-vos-tables-avec-des-objets-java-le-pattern-dao?fbclid=IwAR2bB6wquWfT2fv42CgT5r9Hmc6c4He3MgjfQYwtIcK6ItSmwxg7cbQkf8c

import controleur.ConnexionBDD;
import java.sql.Connection;

public abstract class DAO<T> {
    public Connection connect = ConnexionBDD.getInstance();

    public abstract T create(T object);

    public abstract boolean delete(T object);

    public abstract T update(T object);
    

    public abstract T find(int id);
}