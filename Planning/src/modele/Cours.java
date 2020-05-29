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
     * constructeur
     * @param nom
     */
    public Cours(String nom) { //Un administrateur peut créer un nouveau cours, id s'implémente seul dans BDD
        this.nom = nom;
    }
    
    /**
     * retourne id du cours
     * @return
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * retourne nom du cours
     * @return
     */
    public String getNom() {
        return this.nom;
    }
        
    /**
     * set id du cours
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * set nom du cours
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}
