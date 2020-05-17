package modele;

/*ENSEIGNANT (#  ID_UTILISATEUR, #ID_COURS  ) 
Remarques : l’enseignant est un utilisateur et peut donner plusieurs cours
*/

import java.util.ArrayList;
import modele.*;

public class Enseignant extends Utilisateur {
    private ArrayList<Cours> cours;
    private ArrayList<Seance> seances;
    
    //Constructeur par défaut
    public Enseignant() {
        super();
        cours = new ArrayList<>();
        
        seances = new ArrayList<>();
    }
    //Méthodes
    public void addCours(Cours c){
        cours.add(c);
    }

    public void addSeances(Seance s){
        seances.add(s);
    }
    
    //Getters setters
    public ArrayList<Cours> getCours(){
        return cours;
    }
    public ArrayList<Seance> getSeances() {
        return seances;
    }
    public void setCours(ArrayList<Cours> c){
        cours = c;
    }
}