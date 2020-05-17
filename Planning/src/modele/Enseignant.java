package modele;

/*ENSEIGNANT (#  ID_UTILISATEUR, #ID_COURS  ) 
Remarques : l’enseignant est un utilisateur et peut donner plusieurs cours
*/

import java.util.ArrayList;

public class Enseignant extends Utilisateur {
    private ArrayList<Cours> cours;
    private ArrayList<Seance> seances;
    
    //Constructeur par défaut
    public Enseignant() {
        super();
        cours = new ArrayList<>();
        seances = new ArrayList<>();
    }

    //Constructeur
    public Enseignant(String email, String password, String nom, String prenom) {
        //Un administrateur peut créer un nouvel enseignant
        super(email, password, nom, prenom, 3);
    }
    
    //Getters
    public ArrayList<Cours> getCours(){
        return cours;
    }
    
    public ArrayList<Seance> getSeances() {
        return seances;
    }
    
    //Setters [en cours]
    public void setCours(ArrayList<Cours> cours){
        this.cours = cours;
    }
    
    //Méthodes
    public void ajouterCours(Cours cours) { //Un admin peut ajouter un cours à un enseignant
        this.cours.add(cours);        
    }
    
    public void ajouterSeance(Seance seance) { //Un admin peut ajouter une séance à un enseignant
        this.seances.add(seance); 
    }
}