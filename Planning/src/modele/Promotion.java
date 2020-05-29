package modele;

/**
 *
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */
public class Promotion {
    private int id;
    private String nom;
    
    /**
     * constructeur
     */
    public Promotion() {
        id = 0;
        nom = new String();
    }

    /**
     * constructeur
     * @param nom
     */
    public Promotion(String nom) {
        this.nom = nom;
    }
    
    /**
     * retourne id promo
     * @return
     */
    public int getId() {
        return id;
    }
    
    /**
     * retourne nom promo
     * @return
     */
    public String getNom() {
        return nom;
    }
    
    /**
     * set id promo
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * set nom promo
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}

