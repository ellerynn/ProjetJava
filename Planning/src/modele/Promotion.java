package modele;

/*PROMOTION (ID, NOM)*/

public class Promotion {
    private int id;
    private String nom;
    
    //Constructeur par défaut
    public Promotion() {
        id = 0;
        nom = new String();
    }
    public Promotion(String nom) {
        this.nom = nom;
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

