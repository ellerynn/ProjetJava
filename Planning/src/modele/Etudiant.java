package modele;

import java.util.ArrayList;

/**
 *
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */
public class Etudiant extends Utilisateur {
    private int numero;
    private Groupe groupe;
    private ArrayList<Seance> seances;
    
    /**
     * constructeur
     */
    public Etudiant() {
        super();
        numero = 0;
        groupe = new Groupe();
        seances = new ArrayList();
    }
    
    /**
     * retourne le numero etudiant
     * @return retourne le numero etudiant
     */
    public int getNumero() {
        return numero;
    }
    
    /**
     * retourne le groupe etudiant
     * @return retourne le groupe etudiant
     */
    public Groupe getGroupe() {
        return groupe;
    }
    
    /**
     * retourne les seances etudiant
     * @return retourne les seances etudiant
     */
    public ArrayList<Seance> getSeances() {
        return seances;
    }
    
    /**
     * set le numero etudiant
     * @param numero le numero etudiant
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    /**
     * set le groupe etudiant
     * @param groupe le groupe etudiant
     */
    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }
    
    /**
     * set les seances etudiant
     * @param seances les seances etudiant
     */
    public void setSeances(ArrayList<Seance> seances) {
        this.seances = seances;
    }
}