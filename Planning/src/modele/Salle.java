package modele;

import java.util.*;

/*SALLE (ID, NOM, CAPACITE, #ID_SITE) 
Remarque : la salle indique sa capacité et le site, si l’école dispose de plusieurs sites
*/

public class Salle {
    private int id;
    private String nom;
    private int capacite;
    private Site site;
    private ArrayList<Seance> seances;
    
    //Constructeur par défaut
    public Salle() {
        id = 0;
        nom = new String();
        capacite = 0;
        site = new Site();
        seances = new ArrayList<>();
    }
    
    //Constructeur
    public Salle(String nom, int capacite, Site site) {
        //Un administrateur peut créer une nouvelle salle
        this.nom = nom;
        this.capacite = capacite;
        this.site = site;
    }
    
    //Getters [en cours]
    public int getId() {
        return id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public int getCapacite() {
        return capacite;
    }
    
    public Site getSite() {
        return site;
    }
    
    //Setters [en cours]
    public void setId(int id) {
        this.id = id;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
    
    public void setSite(Site site) {
        this.site = site;
    }
    
    //Méthodes
    public void ajouterSeance(Seance seance) { //Un admin peut ajouter une séance à une salle
        this.seances.add(seance);
    }
}
