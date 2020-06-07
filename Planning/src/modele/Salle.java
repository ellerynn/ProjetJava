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
     * @param s seance à ajouter parmis les séances de la salle
     */
    public void addSeances(Seance s){
        seances.add(s);
    }
    
    /**
     * cosntructeur
     * @param nom Nom de la salle
     * @param capacite capacité de la salle
     * @param site site de la salle
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
     * @return retourne id salle
     */
    public int getId() {
        return id;
    }
    
    /**
     * retourne nom salle
     * @return retourne nom salle
     */
    public String getNom() {
        return nom;
    }
    
    /**
     * retourne capacite salle
     * @return retourne capacite salle
     */
    public int getCapacite() {
        return capacite;
    }
    
    /**
     * retourne site salle
     * @return retourne site salle
     */
    public Site getSite() {
        return site;
    }

    /** retourne seances salle
     *
     * @return retourne seances salle
     */
    public ArrayList<Seance> getSeances() {
        return seances;
    }
    
    /**
     * set id salle
     * @param id id salle
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * set nom salle
     * @param nom nom salle
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    /**
     * set capacite salle
     * @param capacite capacite salle
     */
    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
    
    /**
     * set site salle
     * @param site site de la salle
     */
    public void setSite(Site site) {
        this.site = site;
    }
    
    /**
     * ajouter seance
     * @param seance seance à ajouter parmis les séances de la salle
     */
    public void ajouterSeance(Seance seance) { //Un admin peut ajouter une séance à une salle
        this.seances.add(seance);
    }
}
