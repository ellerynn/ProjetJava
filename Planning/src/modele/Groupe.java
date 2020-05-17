package modele;

import java.util.*;

/*GROUPE (ID, NOM, #ID_PROMOTION) Remarque : le groupe est affecté à une promotion*/

public class Groupe {
    private int id;
    private String nom;
    private Promotion promotion;
    private ArrayList<Seance> seances;
    
    //Constructeur par défaut
    public Groupe() {
        id = 0;
        nom = new String();
        promotion = new Promotion();
        seances = new ArrayList<>();
    }
    //Méthodes
    public void addSeances(Seance s){
        seances.add(s);
    }
    
    //Getters [en cours]
    public int getId() {
        return id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public Promotion getPromotion() {
        return promotion;
    }
    
    public ArrayList<Seance> getSeances() {
        return seances;
    }
    
    //Setters [en cours]
    public void setId(int id) {
        this.id = id;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
}

