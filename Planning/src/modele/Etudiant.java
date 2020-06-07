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
     * constructeur
     * @param email email de l'étudiant
     * @param password mot de passe de l'étudiant
     * @param nom nom de l'étudiant
     * @param prenom prenom de l'étudiant
     * @param numero numero de l'étudiant
     * @param groupe groupe de l'étudiant
     */
    public Etudiant(String email, String password, String nom, String prenom, int numero, Groupe groupe) {
        //Un administrateur peut créer un nouvel étudiant
        super(email, password, nom, prenom, 4);
        this.numero = numero;
        this.groupe = groupe;
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
    
    /**
     * ajouter une seance
     * @param seance la séance à ajouter parmi les séances
     */
    public void ajouterSeance(Seance seance) { //Un admin peut ajouter une séance à un étudiant
        this.seances.add(seance);
    }
}