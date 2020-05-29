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
     * @param email
     * @param password
     * @param nom
     * @param prenom
     * @param numero
     * @param groupe
     * @param promotion
     */
    public Etudiant(String email, String password, String nom, String prenom, int numero, Groupe groupe, Promotion promotion) {
        //Un administrateur peut créer un nouvel étudiant
        super(email, password, nom, prenom, 4);
        this.numero = numero;
        this.groupe = groupe;
        seances = new ArrayList();
    }
    
    /**
     * retourne le numero etudiant
     * @return
     */
    public int getNumero() {
        return numero;
    }
    
    /**
     * retourne le groupe etudiant
     * @return
     */
    public Groupe getGroupe() {
        return groupe;
    }
    
    /**
     * retourne les seances etudiant
     * @return
     */
    public ArrayList<Seance> getSeances() {
        return seances;
    }
    
    /**
     * set le numero etudiant
     * @param numero
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    /**
     * set le groupe etudiant
     * @param groupe
     */
    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }
    
    /**
     * set les seances etudiant
     * @param seances
     */
    public void setSeances(ArrayList<Seance> seances) {
        this.seances = seances;
    }
    
    /**
     * ajouter une seance
     * @param seance
     */
    public void ajouterSeance(Seance seance) { //Un admin peut ajouter une séance à un étudiant
        this.seances.add(seance);
    }
}