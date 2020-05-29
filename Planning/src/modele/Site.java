package modele;

/**
 *
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */
public class Site {
    private int id;
    private String nom;
    
    /**
     * constructeur
     */
    public Site() {
        id = 0;
        nom = new String();
    }
    
    /**
     * constructeur
     * @param nom
     */
    public Site(String nom) {
        this.nom = nom;
    }
    
    /**
     * retourne id site
     * @return
     */
    public int getId() {
        return id;
    }
    
    /**
     * retourne nom site
     * @return
     */
    public String getNom() {
        return nom;
    }
    
    /**
     * set id site
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * set nom site
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}
