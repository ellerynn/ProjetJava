package modele;

import java.util.ArrayList;

/**
 *
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */


public class Salle {
    private int id;
    private String nom;
    private int capacite;
    private Site site;
    private ArrayList<Seance> seances;
    
    /**
     * constructeur
     */
    public Salle() {
        id = 0;
        nom = new String();
        capacite = 0;
        site = new Site();
        seances = new ArrayList<>();
    }

    /**
     * ajouter seance
     * @param s
     */
    public void addSeances(Seance s){
        seances.add(s);
    }
    
    /**
     * cosntructeur
     * @param nom
     * @param capacite
     * @param site
     */
    public Salle(String nom, int capacite, Site site) {
        //Un administrateur peut créer une nouvelle salle
        this.nom = nom;
        this.capacite = capacite;
        this.site = site;
        seances = new ArrayList<>();
    }
    
    /**
     * retourne id salle
     * @return
     */
    public int getId() {
        return id;
    }
    
    /**
     * retourne nom salle
     * @return
     */
    public String getNom() {
        return nom;
    }
    
    /**
     * retourne capacite salle
     * @return
     */
    public int getCapacite() {
        return capacite;
    }
    
    /**
     * retourne site salle
     * @return
     */
    public Site getSite() {
        return site;
    }

    /** retourne seances salle
     *
     * @return
     */
    public ArrayList<Seance> getSeances() {
        return seances;
    }
    
    /**
     * set id salle
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * set nom salle
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    /**
     * set capacite salle
     * @param capacite
     */
    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
    
    /**
     * set site salle
     * @param site
     */
    public void setSite(Site site) {
        this.site = site;
    }
    
    /**
     * ajouter seance
     * @param seance
     */
    public void ajouterSeance(Seance seance) { //Un admin peut ajouter une séance à une salle
        this.seances.add(seance);
    }
}
