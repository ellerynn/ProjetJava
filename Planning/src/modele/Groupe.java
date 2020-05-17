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
    
    //Constructeur
    public Groupe(String nom, Promotion promotion) {
        //Un administrateur peut créer un nouveau groupe
        this.nom = nom;
        this.promotion = promotion;
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
    
    //Méthodes
    public void ajouterSeance(Seance seance) { //Un admin peut ajouter une séance à un groupe
        this.seances.add(seance);
    }
}

