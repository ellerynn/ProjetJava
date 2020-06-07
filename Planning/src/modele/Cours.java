package modele;

/**
 *
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */
public class Cours {
    private int id;
    private String nom;
    
    /**
     * constructeur
     */
    public Cours() {
        id = 0;
        nom = new String();
    }
    
    /**
     * retourne id du cours
     * @return Retourne l'id du cours
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * retourne nom du cours
     * @return retourne nom du cours
     */
    public String getNom() {
        return this.nom;
    }
        
    /**
     * set id du cours
     * @param id Id du cours
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * set nom du cours
     * @param nom Nom du cours
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}
