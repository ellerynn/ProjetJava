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
     * @param nom
     * @param promotion
     */
    public Groupe(String nom, Promotion promotion) {
        //Un administrateur peut créer un nouveau groupe
        this.nom = nom;
        this.promotion = promotion;
    }
    
    /**
     * retourne id groupe
     * @return
     */
    public int getId() {
        return id;
    }
    
    /**
     * retourne nom groupe
     * @return
     */
    public String getNom() {
        return nom;
    }
    
    /**
     * retourne promo groupe
     * @return
     */
    public Promotion getPromotion() {
        return promotion;
    }
    
    /**
     * set id groupe
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * set nom groupe
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    /**
     * set promo groupe
     * @param promotion
     */
    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
    
}

