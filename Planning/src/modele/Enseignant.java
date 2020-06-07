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
}