package modele;

import java.util.ArrayList;

/**
 *
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */
public class Enseignant extends Utilisateur {
    private ArrayList<Cours> cours;
    private ArrayList<Seance> seances;
    
    /**
     * constructeur
     */
    public Enseignant() {
        super();
        cours = new ArrayList<>();
        seances = new ArrayList<>();
    }

    /**
     * constructeur
     * @param email email de l'enseignant
     * @param password mot de passe de l'enseignant
     * @param nom nom de l'enseignant
     * @param prenom prenom de l'enseignant
     */
    public Enseignant(String email, String password, String nom, String prenom) {
        //Un administrateur peut créer un nouvel enseignant
        super(email, password, nom, prenom, 3);
        cours = new ArrayList<>();
        seances = new ArrayList<>();
    }
    
    /**
     * retourne les cours de l'enseignant
     * @return Retourne les cours de l'enseignant
     */
    public ArrayList<Cours> getCours(){
        return cours;
    }
    
    /**
     * retourne les seances de l'enseignants
     * @return Retourne les séances de l'enseignant
     */
    public ArrayList<Seance> getSeances() {
        return seances;
    }
    
    /**
     * set les cours de l'enseignant
     * @param cours l'ensemble des cours de l'enseignant
     */
    public void setCours(ArrayList<Cours> cours){
        this.cours = cours;
    }
    
    /**
     * set les seances de l'enseignant
     * @param seances l'ensemble des séances de l'enseignant
     */
    public void setSeances(ArrayList<Seance> seances){
        this.seances = seances;
    }
    
    /**
     * ajouter un cours
     * @param cours le cours à ajouter dans les cours de l'enseignant
     */
    public void ajouterCours(Cours cours) { //Un admin peut ajouter un cours à un enseignant
        this.cours.add(cours);        
    }
    
    /**
     * ajouter une seance
     * @param seance la séance à ajouter dans les séances de l'enseignant
     */
    public void ajouterSeance(Seance seance) { //Un admin peut ajouter une séance à un enseignant
        this.seances.add(seance); 
    }
}