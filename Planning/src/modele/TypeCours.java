package modele;

/**
 *
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */


public class TypeCours {
    private int id;
    private String nom;
    
    /**
     * constructeur 
     */
    public TypeCours() {
        id = 0;
        nom = new String();
    }
    
    /**
     * constructeur
     * @param nom
     */
    public TypeCours (String nom) {
        this.nom = nom;
    }
    
    /**
     * retourne l'id d'un type de cours
     * @return
     */
    public int getId() {
        return id;
    }
    
    /**
     * retourne le nom d'un type de cours
     * @return
     */
    public String getNom() {
        return nom;
    }
    
    /**
     * set l'id d'un type de cours
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * set le nom d'un type de cours
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}
