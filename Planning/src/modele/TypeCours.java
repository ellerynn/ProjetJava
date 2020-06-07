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
     * retourne l'id d'un type de cours
     * @return retourne l'id d'un type de cours
     */
    public int getId() {
        return id;
    }
    
    /**
     * retourne le nom d'un type de cours
     * @return retourne le nom d'un type de cours
     */
    public String getNom() {
        return nom;
    }
    
    /**
     * set l'id d'un type de cours
     * @param id id d'un type de cours
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * set le nom d'un type de cours
     * @param nom nom d'un type de cours
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}
