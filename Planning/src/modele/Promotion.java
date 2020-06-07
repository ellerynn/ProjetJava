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
     * @param nom Nom de la promotion
     */
    public Promotion(String nom) {
        this.nom = nom;
    }
    
    /**
     * retourne id promo
     * @return retourne id promo
     */
    public int getId() {
        return id;
    }
    
    /**
     * retourne nom promo
     * @return retourne nom promo
     */
    public String getNom() {
        return nom;
    }
    
    /**
     * set id promo
     * @param id id promo
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * set nom promo
     * @param nom nom promo
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}

