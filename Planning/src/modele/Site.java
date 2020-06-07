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
     * retourne id site
     * @return retourne id site
     */
    public int getId() {
        return id;
    }
    
    /**
     * retourne nom site
     * @return retourne nom site
     */
    public String getNom() {
        return nom;
    }
    
    /**
     * set id site
     * @param id id site
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * set nom site
     * @param nom nom site
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}
