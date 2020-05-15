/*COURS (ID, NOM)*/

package projetjava;

public class Cours {
    private int id;
    private String nom;
    
    //Constructeur par défaut
    public Cours() {
        id = 0;
        nom = new String();
    }
    
    //Getters
    public int getId() {
        return id;
    }
    
    public String getNom() {
        return nom;
    }
    
    //Setters
    public void setId(int id) {
        this.id = id;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
}