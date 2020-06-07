package modele;

/**
 *
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */
public class Groupe {
    private int id;
    private String nom;
    private Promotion promotion;
    
    /**
     * cosntructeur
     */
    public Groupe() {
        id = 0;
        nom = new String();
        promotion = new Promotion();
    }
    
    /**
     * constructeur
     * @param nom Nom du groupe
     * @param promotion Promotion du groupe
     */
    public Groupe(String nom, Promotion promotion) {
        //Un administrateur peut créer un nouveau groupe
        this.nom = nom;
        this.promotion = promotion;
    }
    
    /**
     * retourne id groupe
     * @return retourne id groupe
     */
    public int getId() {
        return id;
    }
    
    /**
     * retourne nom groupe
     * @return retourne nom groupe
     */
    public String getNom() {
        return nom;
    }
    
    /**
     * retourne promo groupe
     * @return retourne promo groupe
     */
    public Promotion getPromotion() {
        return promotion;
    }
    
    /**
     * set id groupe
     * @param id id groupe
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * set nom groupe
     * @param nom nom groupe
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    /**
     * set promo groupe
     * @param promotion promo groupe
     */
    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
    
}

