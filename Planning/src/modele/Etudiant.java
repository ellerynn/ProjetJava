package modele;

/*ETUDIANT (#ID_UTILISATEUR  , NUMERO, #ID_GROUPE …) 
Remarques : l’étudiant est un utilisateur (sa clé primaire est aussi une clé étrangère qui référence la clé primaire de 
la relation UTILISATEUR) et est affecté à un groupe
*/

import java.util.ArrayList;

public class Etudiant extends Utilisateur {
    private int numero;
    private Groupe groupe;
    private ArrayList<Seance> seances;
    
    //Constructeur par défaut
    public Etudiant() {
        super();
        numero = 0;
        groupe = new Groupe();
        seances = new ArrayList();
    }
    
    //Constructeur
    public Etudiant(String email, String password, String nom, String prenom, int numero, Groupe groupe, Promotion promotion) {
        //Un administrateur peut créer un nouvel étudiant
        super(email, password, nom, prenom, 4);
        this.numero = numero;
        this.groupe = groupe;
        seances = new ArrayList();
    }
    
    //Getters
    public int getNumero() {
        return numero;
    }
    
    public Groupe getGroupe() {
        return groupe;
    }
    
    public ArrayList<Seance> getSeances() {
        return seances;
    }
    
    //Setters
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }
    
    public void setSeances(ArrayList<Seance> seances) {
        this.seances = seances;
    }
    
    //Méthodes
    public void ajouterSeance(Seance seance) { //Un admin peut ajouter une séance à un étudiant
        this.seances.add(seance);
    }
}